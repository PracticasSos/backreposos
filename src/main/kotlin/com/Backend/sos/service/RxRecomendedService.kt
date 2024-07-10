package com.Backend.sos.service

import com.Backend.sos.model.RxRecomended
import com.Backend.sos.repository.PatientRepository
import com.Backend.sos.repository.RxRecomendedRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.Optional

@Service
class RxRecomendedService {
    @Autowired
    lateinit var rxRecomendedRepository: RxRecomendedRepository

    @Autowired
    lateinit var patientRepository: PatientRepository

    fun listAll(): List<RxRecomended> {
        return rxRecomendedRepository.findAll()
    }

    fun getById(id: Long): Optional<RxRecomended> {
        return rxRecomendedRepository.findById(id)
    }

    fun save(model: RxRecomended): RxRecomended {
        try {
            patientRepository.findById(model.patient)
                ?:throw Exception("id no encontrada")
            return rxRecomendedRepository.save(model)
        }catch (ex : Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(rxRecomended: RxRecomended): RxRecomended {
        return rxRecomendedRepository.save(rxRecomended)
    }

    fun delete(id: Long) {
        rxRecomendedRepository.deleteById(id)
    }
}