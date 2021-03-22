class DatabaseInitializer(private val client: DatabaseClient) {
    fun init() {
        client.execute(readText("schema.sql"))
        client.execute(readText("data.sql"))
    }

    fun cleanup() {
        client.execute(readText("cleanup.sql"))
    }

    private fun readText(filename: String) =
        this::class.java.classLoader.getResourceAsStream(filename)
            ?.bufferedReader()
            ?.readText() ?: error("Can't read file: $filename")

}