ARG ARCH="amd64"
FROM ${ARCH}/nginx:1.21.1-alpine
LABEL org.opencontainers.image.authors="gergej123@gmail.com"
RUN rm /etc/nginx/conf.d/default.conf
ADD default.config /etc/nginx/conf.d/default.conf
