FROM postgres:12.5
RUN sed -i 's/^# *\(en_US.UTF-8\|de_DE.UTF-8\)/\1/' /etc/locale.gen && \
        locale-gen
ENV LANG en_US.UTF-8

ENV POSTGRES_DB postgres

MAINTAINER Elias Meireles
ENV PORT 5432
ENV POSTGRES_USER golangAlura
ENV POSTGRES_PASSWORD golangAlura
ENV PGDATA: /var/lib/postgresql/data/
COPY init.sql /docker-entrypoint-initdb.d/
ENV TZ=UTC
EXPOSE $PORT
