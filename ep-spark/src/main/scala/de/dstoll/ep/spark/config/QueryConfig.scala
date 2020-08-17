package de.dstoll.ep.spark.config

import de.dstoll.ep.spark.query._
import pureconfig.generic.auto._

import scala.concurrent.duration.Duration

sealed trait QueryConfig {
  def queryInstance: Query
}

final case class GermanMeetupsConfig(processingTime: Duration) extends QueryConfig {
  override def queryInstance = new GermanMeetups(this)
}

final case class MunichMeetupsConfig(processingTime: Duration) extends QueryConfig {
  override def queryInstance = new MunichMeetups(this)
}

final case class TopKConfig(processingTime: Duration, k: Int) extends QueryConfig {
  override def queryInstance = new TopK(this)
}

final case class HeatmapConfig(processingTime: Duration) extends QueryConfig {
  override def queryInstance = new Heatmap(this)
}

final case class TopGroupsConfig(processingTime: Duration, numberOfGroups: Int) extends QueryConfig {
  override def queryInstance = new TopGroups(this)
}
