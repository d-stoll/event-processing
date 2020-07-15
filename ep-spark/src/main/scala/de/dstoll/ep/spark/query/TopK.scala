package de.dstoll.ep.spark.query
import java.util.UUID

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.OutputMode

class TopK extends Query {

  override def topic: String = "topK"

  override def outputMode: OutputMode = OutputMode.Complete()

  override def transform(df: DataFrame): DataFrame = {
    df.groupBy("venue.city")
      .count
      .where(col("city").isNotNull)
      .orderBy(col("count").desc)
      .limit(10)
      .withColumn("processingTime", current_timestamp())
  }



}
