#!/bin/bash -e

PATH=/bin:/usr/bin:$PATH

REZSYSTEMHOME=.

LOGFILE=${REZSYSTEMHOME}/RezSystem.out

# the path to your PID file
PIDFILE=${REZSYSTEMHOME}/rezsystem.pid

PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=ReservationSystem.jar:lib/mysql-connector-java-8.0.17.jar:lib/log4j-1.2.17.jar:lib/mysql-connector-java-5.1.34-bin.jar:lib/commons-lang3-3.3.2.jar:lib/c3p0-0.9.1.1.jar:lib/quartz-2.2.1.jar:lib/quartz-jobs-2.2.1.jar:lib/slf4j-api-1.6.6.jar:lib/slf4j-log4j12-1.6.6.jar

REZSYSCMD="java -javaagent:newrelic/newrelic.jar -classpath $CLASSPATH -DListen.port=6500 com.newrelic.airline.reservations.Main"

function start {
	getStatus
	if [ $RUNNING -eq 1 ]; then
	    echo "$0 $ARG: RezSystem (pid $PID) already running"
	else
		nohup $REZSYSCMD >> $LOGFILE 2>1 &
		if [ "x$!" != "x" ] ; then
		    echo "$!" > $PIDFILE
		    echo "$0 $ARG: RezSystem started"
		else
		    echo "$0 $ARG: RezSystem could not be started"
	    	    ERROR=3
		fi	
	fi
}

function stop {
	getStatus
	if [ $RUNNING -eq 0 ]; then
	    echo "$0 $ARG: $STATUS"
	    continue
	fi
	if kill $PID ; then
	    rm $PIDFILE
	    echo "$0 $ARG: RezSystem stopped"
	else
	    echo "$0 $ARG: RezSystem could not be stopped"
	    ERROR=4
	fi

}

function restart {
	stop
	
	start
}

function getStatus {
     # check for pidfile
    if [ -f $PIDFILE ] ; then
	PID=`cat $PIDFILE`
	if [ "x$PID" != "x" ] && kill -0 $PID 2>/dev/null ; then
	    STATUS="RezSystem (pid $PID) running"
	    RUNNING=1
	else
	    STATUS="RezSystem (pid $PID?) not running"
	    RUNNING=0
	fi
    else
	STATUS="RezSystem (no pid file) not running"
	RUNNING=0
    fi

}

function status {
   getStatus
   echo $STATUS
}

function catchall {
    echo "not yet implemented"
}


cd ${REZSYSTEMHOME}

    case $1 in
    start) start ;;
    stop) stop ;;
	restart) restart ;;
	status) status ;;
	reload)      catchall ;;
	tidy)        catchall ;;
	pre-build)   catchall ;;
	build)       catchall ;;
	deploy)      catchall ;;
	post-deploy) catchall ;;    
	*) exit 0
esac

exit $ERROR


