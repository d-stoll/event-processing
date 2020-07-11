#!/bin/bash

BASEDIR=$(dirname "$0")

if [ ! -d "$BASEDIR/bin" ]; then
    unzip kafka.zip
fi

$BASEDIR/bin/kafka-server-start.sh $BASEDIR/config/server.properties