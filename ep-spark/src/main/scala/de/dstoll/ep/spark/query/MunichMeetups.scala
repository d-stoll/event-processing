package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class MunichMeetups extends Query {

  override def topic: String = "munichMeetups"

  override def transform(df: DataFrame): DataFrame = df.where(col("venue.city") isin ("München", "Munich"))

}
