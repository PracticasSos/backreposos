package com.Backend.sos.controller


import com.Backend.sos.model.Patients
import com.Backend.sos.model.RxUso
import com.Backend.sos.service.RxUseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class RxUseController {

    @Autowired
    lateinit var rxUseService: RxUseService

    @GetMapping
    fun login(): ResponseEntity<*> {

        return rxUseService.list()?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity<Patients>( HttpStatus.NOT_FOUND)
    }

    @PostMapping("/rx en uso")
    fun register (@RequestBody request: RxUso): ResponseEntity<RxUso> {
        val registerPatient = rxUseService.save(request)
        return  ResponseEntity.ok(registerPatient)
    }

}