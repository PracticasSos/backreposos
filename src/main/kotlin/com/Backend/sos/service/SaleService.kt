package com.Backend.sos.service

import com.Backend.sos.model.Sale
import com.Backend.sos.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SaleService {
    @Autowired
    lateinit var saleRepository: SaleRepository

    @Autowired
    lateinit var patientRepository: PatientRepository

    @Autowired
    lateinit var rxUseRepository: RxUseRepository

    @Autowired
    lateinit var lensRepository: LensRepository

    @Autowired
    lateinit var frameRepository: FrameRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun lis(): List<Sale>{
        return saleRepository.findAll()
    }

    fun save(model: Sale): Sale{

        try {
            patientRepository.findById(model.patientId)
                ?: throw Exception("Id del paciente no encontrada")
            rxUseRepository.findById(model.clinicalId)
                ?:throw Exception("Id de la historia clinica no existe")
            lensRepository.findById(model.lensId)
                ?:throw Exception("Id de lente no encontrado o no existe")
            frameRepository.findById(model.frameId)
                ?:throw Exception("Id del armazon no existe")
            userRepository.findById(model.userId)
            return saleRepository.save(model)
        }catch (ex : Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
}