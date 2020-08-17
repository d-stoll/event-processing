package de.dstoll.ep.spark

import de.dstoll.ep.spark.config.{AppConfig, GermanMeetupsConfig, HeatmapConfig, MunichMeetupsConfig, TopKConfig}
import de.dstoll.ep.spark.query._
import de.dstoll.ep.spark.sink.KafkaSink._
import de.dstoll.ep.spark.source.KafkaSource._
import de.dstoll.ep.spark.util.SchemaProvider._
import org.apache.spark.sql.SparkSession
import pureconfig.ConfigSource
import pureconfig.generic.auto._


object MeetupJob {

  def main(args: Array[String]): Unit = {

    val appConfig = ConfigSource.default.loadOrThrow[AppConfig]

    val spark = SparkSession.builder()
      .appName("Meetup job")
      .master("local[*]")
      .getOrCreate()
    val schema = inferSchema(spark)
    val meetupDF = readFromKafka("meetups", schema)

    appConfig.queryConfigs.foreach(qc => {
      val query = qc match {
        case gmc: GermanMeetupsConfig => new GermanMeetups(gmc)
        case mmc: MunichMeetupsConfig => new MunichMeetups(mmc)
        case tkc: TopKConfig => new TopK(tkc)
        case hmc: HeatmapConfig => new Heatmap(hmc)
      }
      writeToKafka(query.topic, query.transform(meetupDF), query.outputMode, query.trigger)
    })

    spark.streams.awaitAnyTermination()
  }
}
