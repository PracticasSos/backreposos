package com.Backend.sos.controller

import com.Backend.sos.model.UserView
import com.Backend.sos.service.UserViewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT], origins = ["http://localhost:3000"] )
class   UseViewController {

    @Autowired
    lateinit var userViewService: UserViewService
    @GetMapping("/users")
    fun listUserView ():List<UserView>{
        return  userViewService.lisUserView ()
    }
}