package de.dstoll.ep.spark.source

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

object KafkaSource {

  def readFromKafka(topic: String, schema: StructType): DataFrame =
    SparkSession.getActiveSession.get
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", topic)
      .option("startingOffsets", "latest")
      .load()
      .select(col("value") cast "string")
      .select(from_json(col("value"), schema) as "data")
      .select(col("data.*"))

}
