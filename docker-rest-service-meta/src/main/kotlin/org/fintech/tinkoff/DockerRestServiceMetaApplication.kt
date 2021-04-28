package org.fintech.tinkoff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerRestServiceMetaApplication

fun main(args: Array<String>) {
    runApplication<DockerRestServiceMetaApplication>(*args)
}
