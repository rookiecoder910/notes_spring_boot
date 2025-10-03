package com.plcoding.spring_boot_crash_courses.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "notes")
data class Note(
    val title: String,
    val content: String,
    val color: String,
    val createdAt: Instant,
    @Id val id: ObjectId = ObjectId.get()

    )
