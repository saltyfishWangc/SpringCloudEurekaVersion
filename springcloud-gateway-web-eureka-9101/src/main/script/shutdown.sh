#!/bin/bash

APP_NAME=spring-cloud-gateway
PID=$(ps -ef | grep java | grep ${APPNAME} | awk '{print $2}')

if [! ${PID}]
then
    echo "${APP_NAME}没有运行"
else
    echo "${APP_NAME}运行中，执行kill命令"
    kill -9 ${PID}
fi