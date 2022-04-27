#!/bin/bash

echo Ens-Proxy Application Shutdown...
pid=`cat ./proxyens.pid`
kill -9 $pid
