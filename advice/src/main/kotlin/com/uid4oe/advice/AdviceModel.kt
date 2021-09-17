package com.uid4oe.advice

import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.Entity

@Entity(name = "advice_kt")
data class AdviceModel(
    @javax.persistence.Id
    val user_id: String,
    var advice: String,
    var created_at: LocalDateTime = LocalDateTime.now(),
) {
    constructor() : this(
        user_id = "",
        advice = "",
        created_at = LocalDateTime.now(),
    )

    companion object {
        fun localDateToTimestamp(date: LocalDateTime): Timestamp {
            val instant: Instant = date.toInstant(ZoneOffset.UTC)
            return Timestamp.newBuilder()
                .setSeconds(instant.epochSecond)
                .setNanos(instant.nano)
                .build()
        }
    }
}