package com.Backend.sos.service

import com.Backend.sos.model.User
import com.Backend.sos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.sql.Date

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun list():List<User>?{
        return userRepository.findAll()
    }

    fun save (model: User): User{
        try{
            return userRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update (model: User): User{
        try {
            userRepository.findById(model.id)
                ?: throw Exception("ID no exists")

            return userRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }



    fun updateRecords(firstname: String, model: User): User {
        val allUsers = userRepository.findAll()
        val user = allUsers.find { it.firstname == firstname }
            ?: throw Exception("Usuario con el nombre $firstname no se registró")

        user.apply {
            if (model.firstname != null) this.firstname = model.firstname
            if (model.lastname != null) this.lastname = model.lastname
            if (model.charge != null) this.charge = model.charge
            if (model.birthdate != null) this.birthdate = model.birthdate
            if (model.checkInDate != null) this.checkInDate = model.checkInDate
            if (model.ci != null) this.ci = model.ci
            if (model.email != null) this.email = model.email
            if (model.phoneNumber != null) this.phoneNumber = model.phoneNumber
            if (model.age != null) this.age = model.age
        }
        return userRepository.save(user)
    }
}