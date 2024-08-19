package com.Backend.sos.service

import com.Backend.sos.model.Sales
import com.Backend.sos.repository.PatientRepository
import com.Backend.sos.repository.SaleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SaleService {
    @Autowired
    lateinit var saleRepository: SaleRepository

    @Autowired
    lateinit var patientRepository: PatientRepository

    fun save(model: Sales): Sales{
        model.patientId
    }
}