package de.dstoll.sparkjobs.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class FilterMunichMeetups {

  def filter(df: DataFrame): DataFrame = df.where(col("venue.city") isin  ("München", "Munich"))

}
