package com.uid4oe.user

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class UserApp()

fun main(args: Array<String>) {
    runApplication<UserApp>(*args)
}


fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)