package com.Backend.sos.controller

import com.Backend.sos.model.Branch
import com.Backend.sos.service.BranchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/Sucursal")
class BranchController {

    @Autowired
    lateinit var branchService: BranchService

    @GetMapping
    fun list ():ResponseEntity<*>{
        return ResponseEntity(branchService.list(), HttpStatus.OK)
    }

    @PostMapping("Agregar")
    fun save (@RequestBody modelo:Branch): ResponseEntity<Branch> {
        return ResponseEntity(branchService.save(modelo), HttpStatus.OK)
    }

    @PutMapping("Actualizar")
    fun update(@RequestBody modelo: Branch): ResponseEntity<Branch>{
        return ResponseEntity(branchService.update(modelo), HttpStatus.OK)
    }

    @PatchMapping("Modificar")
    fun updateDate(@RequestBody branch: Branch): ResponseEntity<Branch>{
        return  ResponseEntity(branchService.updateDate(branch),HttpStatus.OK)
    }
}