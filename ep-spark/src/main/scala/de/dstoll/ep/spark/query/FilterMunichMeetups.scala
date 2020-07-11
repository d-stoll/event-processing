package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class FilterMunichMeetups extends Query {

  override def transform(df: DataFrame): DataFrame = df.where(col("venue.city") isin ("München", "Munich"))

}
