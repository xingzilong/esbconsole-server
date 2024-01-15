#!/bin/bash

cygwin=false
darwin=false
os400=false
case "`uname`" in
CYGWIN*) cygwin=true;;
Darwin*) darwin=true;;
OS400*) os400=true;;
esac
error_exit ()
{
    echo "ESBCONSOLE:ERROR: $1 !!"
    exit 1
}
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=$HOME/jdk/java
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/java
[ ! -e "$JAVA_HOME/bin/java" ] && unset JAVA_HOME

if [ -z "$JAVA_HOME" ]; then
  if $darwin; then

    if [ -x '/usr/libexec/java_home' ] ; then
      export JAVA_HOME=`/usr/libexec/java_home`

    elif [ -d "/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Home" ]; then
      export JAVA_HOME="/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Home"
    fi
  else
    JAVA_PATH=`dirname $(readlink -f $(which java))`
    if [ "x$JAVA_PATH" != "x" ]; then
      export JAVA_HOME=`dirname $JAVA_PATH 2>/dev/null`
    fi
  fi
  if [ -z "$JAVA_HOME" ]; then
        error_exit "ESBCONSOLE:Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better!"
  fi
fi

export JAVA_HOME
export JAVA="$JAVA_HOME/bin/java"
export BASE_DIR=`cd $(dirname $0)/..; pwd`
export ESB_DIR=$(dirname $BASE_DIR)
export SPRINGBOOT_CONF=file:${BASE_DIR}/conf/esbconsole.yml
export LOG_DIR=file:${BASE_DIR}/log/

export SERVER="esbconsole-server-start"

#===========================================================================================
# JVM Configuration
#===========================================================================================

JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m"
JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/log/java_heapdump.hprof"
JAVA_OPT="${JAVA_OPT} -XX:-UseLargePages"

JAVA_MAJOR_VERSION=$($JAVA -version 2>&1 | sed -E -n 's/.* version "([0-9]*).*$/\1/p')
if [[ "$JAVA_MAJOR_VERSION" -ge "9" ]] ; then
  JAVA_OPT="${JAVA_OPT} -Xlog:gc*:file=${BASE_DIR}/log/esbconsole_gc.log:time,tags:filecount=10,filesize=100m"
else
  JAVA_OPT_EXT_FIX="-Djava.ext.dirs=${JAVA_HOME}/jre/lib/ext:${JAVA_HOME}/lib/ext"
  JAVA_OPT="${JAVA_OPT} -Xloggc:${BASE_DIR}/log/esbconsole_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
fi

JAVA_OPT="${JAVA_OPT} -Dloader.path=${BASE_DIR}/lib/"
JAVA_OPT="${JAVA_OPT} -Desbconsole.home=${BASE_DIR}"
JAVA_OPT="${JAVA_OPT} -Desb.home=${ESB_DIR}"
JAVA_OPT="${JAVA_OPT} -jar ${BASE_DIR}/target/${SERVER}.jar"
JAVA_OPT="${JAVA_OPT} ${JAVA_OPT_EXT}"
JAVA_OPT="${JAVA_OPT} --spring.config.additional-location=${SPRINGBOOT_CONF}"
JAVA_OPT="${JAVA_OPT} --logging.config=${BASE_DIR}/conf/esbconsole-logback.xml"
JAVA_OPT="${JAVA_OPT} --server.max-http-header-size=524288"

if [ ! -d "${BASE_DIR}/log" ]; then
  mkdir ${BASE_DIR}/log
fi

if [ ! -d "${ESB_DIR}/services" ]; then
  mkdir ${ESB_DIR}/services
fi

echo "$JAVA $JAVA_OPT_EXT_FIX ${JAVA_OPT}"


# check the start.out log output file
if [ ! -f "${BASE_DIR}/log/start.out" ]; then
  touch "${BASE_DIR}/log/start.out"
fi
# start
echo "$JAVA $JAVA_OPT_EXT_FIX ${JAVA_OPT}" > ${BASE_DIR}/log/start.out 2>&1 &

#输出控制台日志，不建议输出，此日志会和程序定向日志高度重合
#if [[ "$JAVA_OPT_EXT_FIX" == "" ]]; then
#  nohup "$JAVA" ${JAVA_OPT} esbconsole.esbconsole >> ${BASE_DIR}/log/start.out 2>&1 &
#else
#  nohup "$JAVA" "$JAVA_OPT_EXT_FIX" ${JAVA_OPT} esbconsole.esbconsole >> ${BASE_DIR}/log/start.out 2>&1 &
#fi

if [[ "$JAVA_OPT_EXT_FIX" == "" ]]; then
  nohup "$JAVA" ${JAVA_OPT} esbconsole.esbconsole &
else
  nohup "$JAVA" "$JAVA_OPT_EXT_FIX" ${JAVA_OPT} esbconsole.esbconsole &
fi

echo "ESBCONSOLE:esbconsole is starting，you can check the ${BASE_DIR}/log/start.out"
