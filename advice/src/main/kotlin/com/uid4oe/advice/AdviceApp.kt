package com.uid4oe.advice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class AdviceApp()

fun main(args: Array<String>) {
    runApplication<AdviceApp>(*args)
}


fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)