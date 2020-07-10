package de.dstoll.sparkjobs.sink

import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.{Column, DataFrame}

object KafkaSink {

  def writeToKafka(topic: String, df: DataFrame, id: Column): StreamingQuery =
    df.select(id cast "string" alias "key", col("*") cast "string" alias "value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "kafka:9092")
      .option("topic", topic)
      .start()

}
