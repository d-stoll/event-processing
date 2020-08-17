package de.dstoll.ep.spark.source

import de.dstoll.ep.spark.config.KafkaConfig
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

class KafkaSource(kafkaConfig: KafkaConfig) {

  def read(topic: String, schema: StructType): DataFrame =
    SparkSession.getActiveSession.get
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaConfig.bootstrapServers)
      .option("subscribe", topic)
      .option("startingOffsets", kafkaConfig.startingOffset)
      .load()
      .select(col("value") cast "string")
      .select(from_json(col("value"), schema) as "data")
      .select(col("data.*"))

}
