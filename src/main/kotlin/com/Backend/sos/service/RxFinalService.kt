package com.Backend.sos.service

import com.Backend.sos.model.RxFinal
import com.Backend.sos.repository.PatientRepository
import com.Backend.sos.repository.RxFinalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class RxFinalService {
    @Autowired
    lateinit var rxFinalRepository: RxFinalRepository

    @Autowired
    lateinit var patientRepository: PatientRepository

    fun list():List<RxFinal>?{
        return rxFinalRepository.findAll()
    }

    fun listById (id:Long?):RxFinal?{
        return rxFinalRepository.findById(id)
    }

    fun save(model: RxFinal): RxFinal{

        try {
            patientRepository.findById(model.patientId)
                ?: throw Exception("Id del cliente no encontrada")
            return rxFinalRepository.save(model)
        }catch (ex : Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(model: RxFinal): RxFinal{
        try {
            patientRepository.findById(model.id)
                ?: throw Exception("ID no existe")

            return rxFinalRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = rxFinalRepository.findById(id)
                ?: throw Exception("ID no existe")
            rxFinalRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


}