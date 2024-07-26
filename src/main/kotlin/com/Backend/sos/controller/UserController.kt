package com.Backend.sos.controller


import com.Backend.sos.dto.*
import com.Backend.sos.model.User
import com.Backend.sos.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT], origins = ["http://localhost:3000", "http://localhost:5173", "http://localhost:8081"] )
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun list(): ResponseEntity<*> {
        return userService.list()?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity<User>(HttpStatus.NOT_FOUND)
    }



    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<User> {
        val registerUser = userService.register(request)
        return ResponseEntity.ok(registerUser)
    }

    @DeleteMapping("/delete")
    fun delete (@RequestBody request: User): Boolean?{
        return userService.DeleteUser(request)
    }


}
