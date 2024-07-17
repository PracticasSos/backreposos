package com.Backend.sos.controller


import com.Backend.sos.model.Patients
import com.Backend.sos.model.RxFinal
import com.Backend.sos.service.RxFinalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/Rx_Final")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT], origins = ["http://localhost:3000"] )

class RxFinalController {

    @Autowired
    lateinit var rxFinalService: RxFinalService

    @GetMapping
    fun login(): ResponseEntity<*> {

        return rxFinalService.list()?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity<Patients>( HttpStatus.NOT_FOUND)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(rxFinalService.listById (id), HttpStatus.OK)

    }

    @PostMapping("/registro")
    fun register (@RequestBody request: RxFinal): ResponseEntity<RxFinal> {
        val registerPatient = rxFinalService.save(request)
        return  ResponseEntity.ok(registerPatient)
    }

    @PutMapping("/{id}")
    fun update (@RequestBody modelo: RxFinal):ResponseEntity<RxFinal>{
        return ResponseEntity(rxFinalService.update(modelo), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return rxFinalService.delete(id)
    }

}