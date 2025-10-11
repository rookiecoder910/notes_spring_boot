package com.plcoding.spring_boot_crash_courses.controllers

import com.plcoding.spring_boot_crash_courses.database.model.Note
import com.plcoding.spring_boot_crash_courses.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.boot.ssl.pem.PemContent
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Double.toHexString
import java.time.Instant

import org.springframework.web.bind.annotation.*

//only when user hit specific api endpoint the function will be called
//eg: https://notes.com/notes/123(post)
//eg:https://notes.com/notes?ownerId=123(get)
@RestController
@RequestMapping("/notes")
// In the controller make function which gets called when the mobile app sends requests by hitting api endpoints by retrofit
class NoteController(
    private val repository: NoteRepository
    ){
    //user send the request to save notes by calling the below function
    // in this data class only thing that user can change or edit
    data class NoteRequest(
        val id:String?,
        val title: String,
        val content: String,
        val color: Long,

    )
    //the response send from backend server to frontend
    data class NoteResponse(
        val id:String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant



    )
    @PostMapping

    fun save(@RequestBody
        body: NoteRequest):NoteResponse {
        val ownerId = SecurityContextHolder.getContext().authentication.principal.toString()
       val note= repository.save(
            Note(
                id=body.id?.let{ObjectId(it)} ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId(ownerId)

            )
        )
        return note.toResponse()




    }
    @GetMapping
    fun findbyownerId(
        @RequestParam(required = true) ownerId: String
    ): List<NoteResponse> {
        val ownerId = SecurityContextHolder.getContext().authentication.principal.toString()
        return repository.findByOwnerId(ObjectId(ownerId)).map {
         it.toResponse()
        }

    }
    @DeleteMapping(path =["/{id}"] )
    fun deleteById( @PathVariable id:String) {
        val note=repository.findById(ObjectId(id)).orElseThrow {
            IllegalArgumentException("Note not found ")
        }
        val ownerId = SecurityContextHolder.getContext().authentication.principal.toString()
        if (note.ownerId.toHexString()== ownerId){

        }
        repository.deleteById(ObjectId(id))
    }

}


private fun Note.toResponse(): NoteController.NoteResponse {
    return NoteController.NoteResponse(
        id = id.toHexString(),
        title = title,
        content = content,
        color = color,
        createdAt = createdAt

    )
}