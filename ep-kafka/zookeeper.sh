#!/bin/bash

BASEDIR=$(dirname "$0")

if [ ! -d "$BASEDIR/bin" ]; then
    unzip $BASEDIR/kafka.zip -d $BASEDIR
fi

$BASEDIR/bin/zookeeper-server-start.sh $BASEDIR/config/zookeeper.properties