package de.dstoll.ep.spark.query
import org.apache.spark.sql.DataFrame

class TopKMeetups extends Query {

  override def topic: String = "topK"

  override def transform(df: DataFrame): DataFrame = ???

}
