#!/bin/bash

./shutdown.sh

echo Ens-Proxy Application Start background...
nohup java -jar -Dport=31011 -Droute_url=https://docs-gw.kakaopay.com ../webapps/proxy-post-0.0.1-SNAPSHOT.jar 1> /dev/null 2>&1 &

