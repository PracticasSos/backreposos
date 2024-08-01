package com.Backend.sos.controller

import com.Backend.sos.dto.FrameLensRequest
import com.Backend.sos.dto.ProductResponse
import com.Backend.sos.model.Frame
import com.Backend.sos.model.Lens
import com.Backend.sos.service.FrameLensService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Inventario")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT], origins = ["http://localhost:3000"] )
class FrameLensController {

    @Autowired
    lateinit var frameLensService: FrameLensService

    @GetMapping
    fun obtenerProductos(): List<Lens> {
        return frameLensService.listLens()
    }



    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(frameLensService.listById (id), HttpStatus.OK)

    }

    @PostMapping("/guardar-armazón")
    fun register (@RequestBody request: Frame): ResponseEntity<Frame> {
        val registerPatient = frameLensService.saveFrame(request)
        return ResponseEntity.ok(registerPatient)
    }
    @PostMapping("/guardar-lentes")
    fun register (@RequestBody request: Lens): ResponseEntity<Lens> {
        val registerPatient = frameLensService.savaLens(request)
        return ResponseEntity.ok(registerPatient)
    }


    @PatchMapping("/update-frame")
    fun updateData(@RequestBody model: Frame): ResponseEntity<Frame>{
        return  ResponseEntity(frameLensService.updateDaes(model),HttpStatus.OK)
    }

    @PatchMapping("/update-lens")
    fun updateDataLens (@RequestBody model: Lens): ResponseEntity<Lens>{
        return  ResponseEntity(frameLensService.updatesLens(model),HttpStatus.OK)
    }

    @DeleteMapping("/delete-armazón/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return frameLensService.delete(id)
    }

    @DeleteMapping("/delete-Lunas/{id}")
    fun delete (@PathVariable("id") id: Long?):Boolean?{
        return frameLensService.deleteLens(id)
    }

}