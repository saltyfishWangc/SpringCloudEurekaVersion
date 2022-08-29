#!/bin/bash

APP_NAME="spring-cloud-gateway"
APP="spring-cloud-gateway.jar"
LOG_DIR="/app/xindai/logs/spring-cloud-gateway"
`mkdir -p $LOG_DIR`

IP=`ifconfig eth0 | grep "inet addr" | awk '{print $2}' | awk -F ":" '{print $2}'`
JVM_ARG="${JAVA_OPTS} -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -Xms2048m -Xmx2048m -Xmn768m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=10 -XX:+PrintGCDetails "

echo "应用名称:${APP_NAME}"
echo "应用:${APP}"
echo "服务器ip:${IP}"
echo "日志路径:${LOG_DIR}"
echo "JVM参数:${JVM_ARG}"

PIDS=`ps -ef | grep java | grep "$APP" | awk '{print $2}'`
PID="${PIDS}"

echo "${APP_NAME}PID=${PID}"

if [ -z ${PID} ]; then
    echo "${APP_NAME}没有运行"
else
    echo "${APP_NAME}运行中PID=${PIDS} 执行kill命令"
    kill -9 ${PID}
fi

sleep 1s
nohup java -jar ${JVM_ARG} ${APP} >> $LOG_DIR/application.$APPL_NAME.log 2>&1 &

sleep 2s
NPIDS=`ps -ef | grep java | grep "${APP}" | awk '{print $2}'`
if [ ! -n "${NPIDS}" ]
then
    echo "${APP}启动失败!!!"
else
    echo "${APP}启动成功PID=${NPIDS}"
fi