import org.apache.tools.ant.taskdefs.condition.Os

tasks.register<Copy>("unzip-kafka") {
    if(!file("bin").exists()) {
        from(zipTree("kafka.zip"))
        into(projectDir)
    }
}

tasks.register("start-zookeeper") {
    doLast {
        val executable = if (Os.isFamily(Os.FAMILY_WINDOWS))
            "bin/windows/zookeeper-server-start.bat" else "bin/zookeeper-server-start.sh"

        val zookeeperPb = ProcessBuilder(executable, "config/zookeeper.properties")
        zookeeperPb.directory(projectDir)

        println("Starting zookeeper...")
        val zookeeper = zookeeperPb.start()
        println("Zookeeper server started ... ${zookeeper.pid()}")
    }
}

tasks.register("stop-zookeeper") {
    doLast {
        val executable = if (Os.isFamily(Os.FAMILY_WINDOWS))
            "bin/windows/zookeeper-server-stop.bat" else "bin/zookeeper-server-stop.sh"

        val zookeeperKillerPb = ProcessBuilder(executable)
        zookeeperKillerPb.directory(projectDir)

        println("Stopping zookeeper...")
        val zookeeperKiller = zookeeperKillerPb.start()
        zookeeperKiller.waitFor()
    }
}

tasks.register("start-kafka") {
    doLast {
        val executable = if (Os.isFamily(Os.FAMILY_WINDOWS))
            "bin/windows/kafka-server-start.bat" else "bin/kafka-server-start.sh"

        val kafkaPb = ProcessBuilder(executable, "config/server.properties")
        kafkaPb.directory(projectDir)

        println("Starting kafka...")
        val kafka = kafkaPb.start()
        println("Kafka server started ... ${kafka.pid()}")
    }
}

tasks.register("stop-kafka") {
    doLast {
        val executable = if (Os.isFamily(Os.FAMILY_WINDOWS))
            "bin/windows/kafka-server-stop.bat" else "bin/kafka-server-stop.sh"

        val kafkaKillerPb = ProcessBuilder(executable)
        kafkaKillerPb.directory(projectDir)

        println("Stopping kafka...")
        val kafkaKiller = kafkaKillerPb.start()
        kafkaKiller.waitFor()
    }
}

tasks["start-zookeeper"].dependsOn("unzip-kafka")
tasks["stop-zookeeper"].dependsOn("unzip-kafka")
tasks["start-kafka"].dependsOn("start-zookeeper")
tasks["stop-kafka"].dependsOn("stop-zookeeper")