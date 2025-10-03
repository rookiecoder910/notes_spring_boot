package com.plcoding.spring_boot_crash_courses.database.repository

import com.plcoding.spring_boot_crash_courses.database.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
}