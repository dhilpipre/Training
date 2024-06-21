#!/bin/bash -e

PATH=/bin:/usr/bin:$PATH

REZSYSTEMHOME=.

LOGFILE=${REZSYSTEMHOME}/RezSystem.out

# the path to your PID file
PIDFILE=${REZSYSTEMHOME}/rezsystem.pid

PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=ReservationSystem.jar:lib/log4j-1.2.17.jar:lib/mysql-connector-java-5.1.34-bin.jar:lib/commons-lang3-3.3.2.jar:lib/c3p0-0.9.1.1.jar:lib/quartz-2.2.1.jar:lib/quartz-jobs-2.2.1.jar:lib/slf4j-api-1.6.6.jar:lib/slf4j-log4j12-1.6.6.jar

/docker-java-home/jre/bin/java -javaagent:newrelic/newrelic.jar -classpath $CLASSPATH -DListen.port=6500 com.newrelic.airline.reservations.Main

