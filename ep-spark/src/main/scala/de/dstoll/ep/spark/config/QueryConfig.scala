package de.dstoll.ep.spark.config

import pureconfig.generic.auto._

import scala.concurrent.duration.Duration

sealed trait QueryConfig
final case class GermanMeetupsConfig(processingTime: Duration) extends QueryConfig
final case class MunichMeetupsConfig(processingTime: Duration) extends QueryConfig
final case class TopKConfig(processingTime: Duration) extends QueryConfig
final case class HeatmapConfig(processingTime: Duration) extends QueryConfig
