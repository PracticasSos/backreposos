package com.Backend.sos.service

import com.Backend.sos.model.User
import com.Backend.sos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun list():List<User>?{
        return userRepository.findAll()
    }
}