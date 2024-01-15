cd `dirname $0`/../target
target_dir=`pwd`

pid=`ps ax | grep -i 'esbconsole.esbconsole' | grep ${target_dir} | grep java | grep -v grep | awk '{print $1}'`
if [ -z "$pid" ] ; then
        echo "ESBCONSOLE:No esbconsole running."
        exit -1;
fi

echo "ESBCONSOLE:The esbconsole(${pid}) is running..."

kill ${pid}

echo "ESBCONSOLE:Send shutdown request to esbconsole(${pid}) OK"