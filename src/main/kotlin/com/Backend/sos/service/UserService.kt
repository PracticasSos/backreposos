package com.Backend.sos.service

import com.Backend.sos.model.User
import com.Backend.sos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException

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

    fun updateRecords (model: User): User{
        try {
            val response = userRepository.findById(model.id)
                ?:throw Exception("id no existe")
            response.apply {
                firstname = model.firstname
                lastname = model.lastname
                age = model.age
                charge = model.charge
                birthdate = model.birthdate
                checkInDate = model. checkInDate
                ci = model.ci
                email = model.email
                phoneNumber = model.phoneNumber
            }
            return userRepository.save(response)

        }catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}