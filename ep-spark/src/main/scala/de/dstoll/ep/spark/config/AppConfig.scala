package de.dstoll.ep.spark.config

import pureconfig.generic.auto._

case class AppConfig(queryConfigs: List[QueryConfig])
