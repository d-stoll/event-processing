package de.dstoll.ep.spark.query
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.functions._
import scala.concurrent.duration._

class Heatmap extends Query {

  override def topic: String = "heatmap"

  override def outputMode: OutputMode = OutputMode.Append()

  override def trigger: Trigger = Trigger.ProcessingTime(2.seconds)

  override def transform(df: DataFrame): DataFrame = df.where(col("venue").isNotNull)

}
