#!/bin/bash

# Function to install Docker, containerd, and configure Kubernetes prerequisites
install_docker_and_containerd() {
    local node=$1
    multipass exec $node -- bash -c "
        sudo apt-get update && sudo apt-get install -y \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg \
        lsb-release &&
        curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg &&
        echo \"deb [arch=\$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \$(lsb_release -cs) stable\" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null &&
        sudo apt-get update && sudo apt-get install -y docker-ce docker-ce-cli containerd.io &&
        
        # Load necessary kernel modules
        cat <<EOF | sudo tee /etc/modules-load.d/containerd.conf
overlay
br_netfilter
EOF
        sudo modprobe overlay
        sudo modprobe br_netfilter
        
        # Set up sysctl parameters
        cat <<EOF | sudo tee /etc/sysctl.d/99-kubernetes-cri.conf
net.bridge.bridge-nf-call-iptables  = 1
net.ipv4.ip_forward                 = 1
net.bridge.bridge-nf-call-ip6tables = 1
EOF
        sudo sysctl --system
        
        # Configure containerd
        sudo mkdir -p /etc/containerd && containerd config default | sudo tee /etc/containerd/config.toml
        sudo sed -i 's/SystemdCgroup = false/SystemdCgroup = true/g' /etc/containerd/config.toml
        sudo systemctl restart containerd
        
        # Install Kubernetes components
        sudo apt-get update &&
        sudo apt-get install -y apt-transport-https ca-certificates curl &&
        curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.27/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg &&
        echo 'deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.27/deb/ /' | sudo tee /etc/apt/sources.list.d/kubernetes.list &&
        sudo apt-get update &&
        sudo apt-get install -y kubelet kubeadm kubectl &&
        sudo apt-mark hold kubelet kubeadm kubectl
    "
}

# Function to configure HAProxy
configure_haproxy() {
    local haproxy_node=$1
    shift
    local master_ips=("$@")
    
    # Generate HAProxy configuration
    cat <<EOF | multipass exec $haproxy_node -- sudo tee /etc/haproxy/haproxy.cfg > /dev/null
frontend kubernetes
    mode tcp
    bind :6443
    option tcplog
    default_backend k8s-masters

backend k8s-masters
    mode tcp
    balance roundrobin
    option tcp-check
EOF

    for ((i=0; i<${#master_ips[@]}; i++)); do
        echo "    server k8s-master-${i} ${master_ips[i]}:6443 check fall 3 rise 2" | multipass exec $haproxy_node -- sudo tee -a /etc/haproxy/haproxy.cfg > /dev/null
    done

    # Restart HAProxy
    multipass exec $haproxy_node -- sudo systemctl restart haproxy
}

# Request base name, cluster version, number of masters and workers
read -p "Enter base name for the cluster: " BASE_NAME
read -p "Enter Kubernetes cluster version: " CLUSTER_VERSION
read -p "Enter number of master nodes: " NUM_MASTERS
read -p "Enter number of worker nodes: " NUM_WORKERS

# Arrays to hold master and worker node names
MASTER_NODES=()
WORKER_NODES=()

# Create master nodes
for i in $(seq 1 $NUM_MASTERS); do
    MASTER_NAME="${BASE_NAME}-master-${i}-${CLUSTER_VERSION}"
    echo "Creating master node: $MASTER_NAME"
    multipass launch --name $MASTER_NAME --mem 2G --cpus 1 --disk 22G
    MASTER_NODES+=($(multipass info $MASTER_NAME --format=json | jq -r '.info.'$MASTER_NAME'.ipv4[0]'))
    install_docker_and_containerd $MASTER_NAME
done

# Create worker nodes
for i in $(seq 1 $NUM_WORKERS); do
    WORKER_NAME="${BASE_NAME}-worker-${i}-${CLUSTER_VERSION}"
    echo "Creating worker node: $WORKER_NAME"
    multipass launch --name $WORKER_NAME --mem 6G --cpus 2 --disk 44G
    WORKER_NODES+=($(multipass info $WORKER_NAME --format=json | jq -r '.info.'$WORKER_NAME'.ipv4[0]'))
    install_docker_and_containerd $WORKER_NAME
done

# Create HAProxy node
HAPROXY_NAME="${BASE_NAME}-haproxy-${CLUSTER_VERSION}"
echo "Creating HAProxy node: $HAPROXY_NAME"
multipass launch --name $HAPROXY_NAME --mem 2G --cpus 1 --disk 22G

# Install HAProxy
multipass exec $HAPROXY_NAME -- bash -c "
    sudo apt-get update && sudo apt-get install -y haproxy
"

# Configure HAProxy with master node IPs
configure_haproxy $HAPROXY_NAME "${MASTER_NODES[@]}"

echo "Cluster setup is complete."
