package com.Backend.sos.controller

import com.Backend.sos.model.Sale
import com.Backend.sos.service.SaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/Venta")
class SalesController {
    @Autowired
    lateinit var saleService: SaleService

    @GetMapping
    fun list(): ResponseEntity<*>{
        return ResponseEntity(saleService.lis(), HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody model: Sale): ResponseEntity<Sale>{
        return ResponseEntity(saleService.save(model),HttpStatus.OK)
    }

}