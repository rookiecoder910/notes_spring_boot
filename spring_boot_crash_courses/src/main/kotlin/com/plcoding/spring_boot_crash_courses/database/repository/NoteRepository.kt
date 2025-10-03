package com.plcoding.spring_boot_crash_courses.database.repository

import com.plcoding.spring_boot_crash_courses.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository: MongoRepository<Note, ObjectId> {

}