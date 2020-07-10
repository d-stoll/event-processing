package de.dstoll.sparkjobs.source

import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.{Column, DataFrame}

class KafkaSink {

  def writeToKafka(topic: String, df: DataFrame, id: Column): StreamingQuery =
    df.selectExpr(id cast "string" alias "key", col("*") cast "string" alias "value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "kafka:9092")
      .option("topic", topic)
      .start()

}
