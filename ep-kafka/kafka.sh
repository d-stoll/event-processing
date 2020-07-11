#!/bin/bash

BASEDIR=$(dirname "$0")

$BASEDIR/bin/kafka-server-start.sh $BASEDIR/config/server.properties