package com.plcoding.spring_boot_crash_courses.database.repository

import com.plcoding.spring_boot_crash_courses.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
// it is a bridge or interface which connects the user mobile with the database by adding simple query in form of methods
interface NoteRepository: MongoRepository<Note, ObjectId> {
fun findByOwnerId(ownerId: ObjectId): List<Note>
}