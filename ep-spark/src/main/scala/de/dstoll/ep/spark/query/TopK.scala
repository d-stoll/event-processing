package de.dstoll.ep.spark.query
import de.dstoll.ep.spark.config.TopKConfig
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

class TopK(config: TopKConfig) extends Query {

  override def topic: String = "topK"

  override def outputMode: OutputMode = OutputMode.Complete()

  override def trigger: Trigger = Trigger.ProcessingTime(config.processingTime)

  override def transform(df: DataFrame): DataFrame = {
    df.where(col("venue.city").isNotNull)
      .groupBy("venue.city")
      .count
      .orderBy(col("count").desc)
      .limit(config.k)
      .withColumn("processingTime", current_timestamp())
  }



}
