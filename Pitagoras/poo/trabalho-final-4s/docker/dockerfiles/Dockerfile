FROM mysql:8.0.16
MAINTAINER Elias Meireles

COPY initial.sql  /docker-entrypoint-initdb.d/

ENV PORT 3306
ENV MYSQL_ROOT_PASSWORD 5hmkC7GCZfv2
ENV MYSQL_USER root
ENV MYSQL_DATABASE isto_e_aquilo
ENV TZ=America/Sao_Paulo
EXPOSE $PORT
