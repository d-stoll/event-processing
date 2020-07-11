package de.dstoll.ep.spark

import de.dstoll.ep.spark.query.{FilterGermanMeetups, FilterMunichMeetups}
import de.dstoll.spark.query.FilterGermanMeetups
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import de.dstoll.ep.spark.source.KafkaSource._
import de.dstoll.ep.spark.util.SchemaProvider._
import de.dstoll.ep.spark.sink.KafkaSink._

object MeetupJob {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Meetup job")
      .master("local[*]")
      .getOrCreate()

    val schema = inferSchema(spark)
    val meetupDF = readFromKafka(spark, "meetups", schema)

    val queries = Map(
      "germanMeetups" -> new FilterGermanMeetups,
      "munichMeetups" -> new FilterMunichMeetups
    )

    queries.foreach { case (topic, query) => writeToKafka(topic, query.transform(meetupDF), col("id")) }

    spark.streams.awaitAnyTermination()
  }

}
