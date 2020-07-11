tasks.register<Copy>("setup") {
    if(!file("bin").exists()) {
        from(zipTree("kafka.zip"))
        into(projectDir)
    }
}