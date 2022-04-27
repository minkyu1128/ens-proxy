@echo off

rem Started...
rem java -jar "-Dport=31011 -Droute_uri=https://docs-gw.kakaopay.com" ../webapps/proxy-post-0.0.1-SNAPSHOT.jar

rem Started background...
@START /b java -jar "-Dport=31011 -Droute_uri=https://docs-gw.kakaopay.com" ../webapps/proxy-post-0.0.1-SNAPSHOT.jar