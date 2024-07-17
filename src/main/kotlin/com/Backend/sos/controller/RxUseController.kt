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
@RequestMapping("/rx-en-uso")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT], origins = ["http://localhost:3000"] )

class RxUseController {

    @Autowired
    lateinit var rxUseService: RxUseService

    @GetMapping
    fun login(): ResponseEntity<*> {

        return rxUseService.list()?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity<Patients>( HttpStatus.NOT_FOUND)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(rxUseService.listById (id), HttpStatus.OK)

    }

    @PostMapping("/registro")
    fun register (@RequestBody request: RxUso): ResponseEntity<RxUso> {
        val registerPatient = rxUseService.save(request)
        return  ResponseEntity.ok(registerPatient)
    }

    @PutMapping("/{id}")
    fun update (@RequestBody modelo: RxUso):ResponseEntity<RxUso>{
        return ResponseEntity(rxUseService.update(modelo), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return rxUseService.delete(id)
    }

}