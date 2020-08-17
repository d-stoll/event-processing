plugins {
    java
    scala
    application
}

java.sourceCompatibility = JavaVersion.VERSION_14

dependencies {
    implementation("org.scala-lang:scala-library:2.12.11")
    implementation("org.apache.spark:spark-core_2.12:3.0.0")
    implementation("org.apache.spark:spark-sql_2.12:3.0.0")
    implementation("org.apache.spark:spark-sql-kafka-0-10_2.12:3.0.0")
    implementation("com.github.pureconfig:pureconfig_2.12:0.13.0")
}

application {
    mainClass.set("de.dstoll.ep.spark.MeetupJob")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "de.dstoll.ep.spark.MeetupJob"
    }

    from(configurations.runtime.get().files.map { if (it.isDirectory) it else zipTree(it) })
}

