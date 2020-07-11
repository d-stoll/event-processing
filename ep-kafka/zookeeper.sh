#!/bin/bash

BASEDIR=$(dirname "$0")

$BASEDIR/bin/zookeeper-server-start.sh $BASEDIR/config/zookeeper.properties