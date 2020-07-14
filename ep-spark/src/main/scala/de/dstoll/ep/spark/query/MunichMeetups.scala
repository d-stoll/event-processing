package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.OutputMode

class MunichMeetups extends Query {

  override def topic: String = "munichMeetups"

  override def outputMode: OutputMode = OutputMode.Append()

  override def transform(df: DataFrame): DataFrame = df.where(col("venue.city") isin ("München", "Munich"))

}
