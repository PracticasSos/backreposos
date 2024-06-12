package com.Backend.sos.service

import com.Backend.sos.model.Roles
import com.Backend.sos.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class RoleService {
    @Autowired
    lateinit var roleRespository:RoleRepository

    fun list():List<Roles>?{
        return roleRespository.findAll()
    }

}