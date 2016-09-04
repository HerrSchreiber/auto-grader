#!/bin/sh
javac -cp "jar/*" src/*.java -d bin
cd bin
jar -cf ../jar/WatchDir.jar *.class

