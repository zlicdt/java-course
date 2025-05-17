#!/bin/zsh
javac -cp .:lib/sqlite-jdbc-3.42.0.0.jar -d . main/Main.java db/*.java exceptions/*.java gui/*.java

java -cp .:lib/sqlite-jdbc-3.42.0.0.jar main.Main

rm -f gui/*.class db/*.class main/*.class exceptions/*.class
