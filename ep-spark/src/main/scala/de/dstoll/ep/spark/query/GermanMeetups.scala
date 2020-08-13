package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import scala.concurrent.duration._

class GermanMeetups extends Query {

  override def topic: String = "germanMeetups"

  override def outputMode: OutputMode = OutputMode.Append()

  override def trigger: Trigger = Trigger.ProcessingTime(2.seconds)

  override def transform(df: DataFrame): DataFrame = df.where(lower(col("venue.country")) === "de")

}
