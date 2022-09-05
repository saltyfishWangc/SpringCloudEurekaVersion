#!/bin/bash
# 跳到指定目录的上级目录
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
SERVER_NAME=xd-service-gateway
LOGS_FILE=$DEPLOY_DIR/logs

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME="hostname"
fi

PIDS=`ps -ef | grep java | grep "${SERVER_NAME}" | awk '{print $2}'`
if [ -n "${PIDS}" ]; then
    echo "ERROR: The ${SERVER_NAME} already started!"
    echo "PIDS: ${PIDS}"
    exit 1
fi

LOGS_DIR=""
if [ -z "${LOGS_FILE}" ]; then
    LOGS_DIR=`dirname ${LOGS_FILE}`
else
    LOGS_DIR=${DEPLOY_DIR}/logs
fi

echo "LOGS_DIR=${LOGS_DIR}"

if [ ! -d ${LOGS_DIR} ]; then
    mkdir ${LOGS_DIR}
    echo "CREATE ${LOGS_DIR} SUCCESS"
fi
STDOUT_FILE="${LOGS_DIR}/${SERVER_NAME}.log"

if [ ! -e ${STDOUT_FILE} ]; then
    touch ${STDOUT_FILE}
    chmod 777 ${STDOUT_FILE}
fi

LIB_DIR=${DEPLOY_DIR}/lib
LIB_JARS=`ls ${LIB_DIR} | grep .jar | awk '{print "'${LIB_DIR}'/"$0}' | tr "\n" ":"`

JAVA_OPTS=" ${JAVA_OPTS} -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
#if [ "$1"=="debug" ]; then
#    JAVA_DEBUG_OPTS=" -Xdebug -xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
#fi
JAVA_JMS_OPTS=""
#if [ "$1" = "jms" ]; then
#    JAVA_JMS_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
#fi
JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
#if [ -n "$BITS" ]; then
#    JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupanyOnly -XX:CMSInitiatingOccupancyFraction=70 "
#else
#    JAVA_MEM_OPTS=" -server -Xms1g -Xmx1g -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
#fi

echo -e "Starting the ${SERVER_NAME} ...\c"
t1=`date '+%Y-%m-%d'`
echo "STDOUT: $STDOUT_FILE.${t1}"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMS_OPTS -classpath $CONF_DIR:$LIB_JARS com.wangc.springcloud.gateway.SpringCloudGatewayWebEureka9101 2>&1 >> $STDOUT_FILE &

echo "OK!"
PIDS=`ps -ef | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo "PID: $PIDS"
