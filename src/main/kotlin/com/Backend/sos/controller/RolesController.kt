package com.Backend.sos.controller

import com.Backend.sos.model.User
import com.Backend.sos.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/roles")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT], origins = ["http://localhost:3000"] )

class RolesController {

    @Autowired
    lateinit var roleService: RoleService

    @GetMapping
    fun login():ResponseEntity<*>{

        return roleService.list()?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity<User>( HttpStatus.NOT_FOUND)
    }

}