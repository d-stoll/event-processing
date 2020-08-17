package de.dstoll.ep.spark.query

import de.dstoll.ep.spark.config.GermanMeetupsConfig
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

class GermanMeetups(config: GermanMeetupsConfig) extends Query {

  override def topic: String = "germanMeetups"

  override def outputMode: OutputMode = OutputMode.Append()

  override def trigger: Trigger = Trigger.ProcessingTime(config.processingTime)

  override def transform(df: DataFrame): DataFrame = df.where(lower(col("venue.country")) === "de")

}
