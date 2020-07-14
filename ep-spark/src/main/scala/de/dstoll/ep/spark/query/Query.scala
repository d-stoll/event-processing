package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame

trait Query {

  def topic: String

  def transform(df: DataFrame): DataFrame

}
