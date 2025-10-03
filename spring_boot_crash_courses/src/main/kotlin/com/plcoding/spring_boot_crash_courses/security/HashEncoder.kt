package com.plcoding.spring_boot_crash_courses.security


import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class HashEncoder {
    private val bcrypt= BCryptPasswordEncoder()
    fun encode(raw:String):String=bcrypt.encode(raw)
    fun matches(raw: String,encoded: String): Boolean=bcrypt.matches(raw,encoded)
}