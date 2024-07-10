package com.Backend.sos.controller

import com.Backend.sos.model.Frame
import com.Backend.sos.model.Lens
import com.Backend.sos.service.FrameLensService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/Inventario")
class FrameLensController {

    @Autowired
    lateinit var frameLensService: FrameLensService

    @GetMapping
    fun login(): ResponseEntity<*> {

        return frameLensService.list()?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity<Frame>( HttpStatus.NOT_FOUND)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(frameLensService.listById (id), HttpStatus.OK)

    }

    @PostMapping("/armazon")
    fun register (@RequestBody request: Frame): ResponseEntity<Frame> {
        val registerPatient = frameLensService.save(request)
        return  ResponseEntity.ok(registerPatient)
    }

    @PostMapping("/lunas")
    fun registerLens (@RequestBody request: Lens): ResponseEntity<Lens> {
        val registerLens = frameLensService.saveLens(request)
        return  ResponseEntity.ok(registerLens)
    }

    @PutMapping("/armazón/{id}")
    fun update (@RequestBody modelo: Frame): ResponseEntity<Frame> {
        return ResponseEntity(frameLensService.update(modelo), HttpStatus.OK)
    }

    @PutMapping("/luna/{id}")
    fun updateLens (@RequestBody modelo: Lens): ResponseEntity<Lens> {
        return ResponseEntity(frameLensService.updateLens(modelo), HttpStatus.OK)
    }


    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return frameLensService.delete(id)
    }
}