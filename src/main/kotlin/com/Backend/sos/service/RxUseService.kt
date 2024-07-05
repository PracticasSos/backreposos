package com.Backend.sos.service

import com.Backend.sos.model.RxUso
import com.Backend.sos.repository.PatientRepository
import com.Backend.sos.repository.RxUseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class RxUseService {
    @Autowired
    lateinit var rxUseRepository: RxUseRepository

    @Autowired
    lateinit var patientRepository: PatientRepository

    fun list():List<RxUso>?{
        return rxUseRepository.findAll()
    }

    fun save(model: RxUso): RxUso{

        try {
            patientRepository.findById(model.idUser)
                ?: throw Exception("Id del cliente no encontrada")
            return rxUseRepository.save(model)
        }catch (ex : Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }


}