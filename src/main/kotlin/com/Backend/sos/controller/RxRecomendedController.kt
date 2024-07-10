package com.Backend.sos.controller

import com.Backend.sos.model.RxRecomended
import com.Backend.sos.service.RxRecomendedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/rx-recomended")
class RxRecomendedController {
    @Autowired
    lateinit var rxRecomendedService: RxRecomendedService

    @GetMapping
    fun listAll(): ResponseEntity<List<RxRecomended>> {
        return ResponseEntity.ok(rxRecomendedService.listAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<RxRecomended> {
        return rxRecomendedService.getById(id).map { rxRecomended ->
            ResponseEntity.ok(rxRecomended)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/angie")
    fun save(@RequestBody rxRecomended: RxRecomended): ResponseEntity<RxRecomended> {
        return ResponseEntity.status(HttpStatus.CREATED).body(rxRecomendedService.save(rxRecomended))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody rxRecomended: RxRecomended): ResponseEntity<RxRecomended> {
        return if (rxRecomendedService.getById(id).isPresent) {
            ResponseEntity.ok(rxRecomendedService.update(rxRecomended))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (rxRecomendedService.getById(id).isPresent) {
            rxRecomendedService.delete(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}