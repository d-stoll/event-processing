package de.dstoll.ep.spark.config

import pureconfig.generic.auto._

case class KafkaConfig(bootstrapServers: String, startingOffset: String)
