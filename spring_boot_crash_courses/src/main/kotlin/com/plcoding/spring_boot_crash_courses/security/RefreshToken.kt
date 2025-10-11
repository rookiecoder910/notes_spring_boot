package com.plcoding.spring_boot_crash_courses.security

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("refresh_tokens")
data class RefreshToken(
    val userId: ObjectId,

    @Indexed(expireAfter = "0s")
    val expiresAt: Instant,
    val createdAt: Instant=Instant.now(),
   val hashedToken:String

)
