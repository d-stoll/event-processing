package de.dstoll.sparkjobs.source

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

object KafkaSource {

  def readFromKafka(spark: SparkSession, topic: String, schema: StructType): DataFrame =
    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "kafka:9092")
      .option("subscribe", topic)
      .load()
      .select(col("value") cast "string")
      .select(from_json(col("value"), schema) as "data")
      .select(col("data.*"))

}
