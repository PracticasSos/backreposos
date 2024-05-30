package com.Backend.sos.service

import com.Backend.sos.model.UserView
import com.Backend.sos.repository.UserViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserViewService {
    @Autowired
    lateinit var userViewRepository: UserViewRepository
    fun lisUserView (): List<UserView>{
        return userViewRepository.findAll()
    }

}