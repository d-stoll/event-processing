package de.dstoll.ep.spark.query
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.functions._

class Heatmap extends Query {

  override def topic: String = "heatmap"

  override def outputMode: OutputMode = OutputMode.Append()

  override def transform(df: DataFrame): DataFrame = df.where(col("venue").isNotNull)

}
