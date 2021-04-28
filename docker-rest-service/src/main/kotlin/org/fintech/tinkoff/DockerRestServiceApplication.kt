package org.fintech.tinkoff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerRestServiceApplication

fun main(args: Array<String>) {
    runApplication<DockerRestServiceApplication>(*args)
}
