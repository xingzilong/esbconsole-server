@echo off

if not exist "%JAVA_HOME%\bin\java.exe" echo ESBCONSOLE:Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
set "JAVA=%JAVA_HOME%\bin\java.exe"

setlocal enabledelayedexpansion

set BASE_DIR=%~dp0
set ESB_DIR="%BASE_DIR:~0,-16%"
rem added double quotation marks to avoid the issue caused by the folder names containing spaces.
rem removed the last 5 chars(which means \bin\) to get the base DIR.
set BASE_DIR="%BASE_DIR:~0,-5%"

rem 判断esb目录下是否存在services文件夹，没有则创建
if not exist "%ESB_DIR%\services" (
    mkdir "%ESB_DIR%\services"
)

set SPRINGBOOT_CONF=file:%BASE_DIR%/conf/esbconsole.yml
set LOG_DIR=file:%BASE_DIR%/log/

set SERVER=esbconsole-server-start

set "ESBCONSOLE_JVM_OPTS=%ESBCONSOLE_JVM_OPTS% -server -Xms256m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m"
set "ESBCONSOLE_JVM_OPTS=%ESBCONSOLE_JVM_OPTS% -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=%BASE_DIR%\log\java_heapdump.hprof"
set "ESBCONSOLE_JVM_OPTS=%ESBCONSOLE_JVM_OPTS% -XX:-UseLargePages"

rem set esbconsole options
set "ESBCONSOLE_OPTS= -Dloader.path=%BASE_DIR%/lib/"
set "ESBCONSOLE_OPTS=%ESBCONSOLE_OPTS% -Desbconsole.home=%BASE_DIR%"
set "ESBCONSOLE_OPTS=%ESBCONSOLE_OPTS% -Desb.home=%ESB_DIR%"
set "ESBCONSOLE_OPTS=%ESBCONSOLE_OPTS% -jar %BASE_DIR%\target\%SERVER%.jar"

rem set esbconsole spring config location
set "ESBCONSOLE_CONFIG_OPTS=--spring.config.additional-location=%SPRINGBOOT_CONF%"

rem set esbconsole log file location
set "ESBCONSOLE_LOG4J_OPTS=--logging.config=%BASE_DIR%/conf/esbconsole-logback.xml"

rem 前台启动方式
set COMMAND="%JAVA%" %ESBCONSOLE_JVM_OPTS% %ESBCONSOLE_OPTS% %ESBCONSOLE_CONFIG_OPTS% %ESBCONSOLE_LOG4J_OPTS% esbconsole.esbconsole %*

rem start esbconsole command
%COMMAND%

