package com.plcoding.spring_boot_crash_courses.database.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    val email: String,
    val hashedPassword: String,
    val id: ObjectId=ObjectId()

)
