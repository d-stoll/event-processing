package de.dstoll.ep.spark.query

import de.dstoll.ep.spark.config.TopGroupsConfig
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, current_timestamp}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

class TopGroups(config: TopGroupsConfig) extends Query {

  override def topic: String = "topGroups"

  override def outputMode: OutputMode = OutputMode.Complete()

  override def trigger: Trigger = Trigger.ProcessingTime(config.processingTime)

  override def transform(df: DataFrame): DataFrame = {
    df.where(col("group.id").isNotNull)
      .groupBy("group.id", "group.name")
      .count
      .orderBy(col("count").desc)
      .limit(config.numberOfGroups)
      .withColumn("processingTime", current_timestamp())
  }

}
