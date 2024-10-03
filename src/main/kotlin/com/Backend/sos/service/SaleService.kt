package com.Backend.sos.service

import com.Backend.sos.model.Frame
import com.Backend.sos.model.Sale
import com.Backend.sos.repository.*
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.swing.plaf.SliderUI

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



    fun balalnceList(): List<Sale>{
        return saleRepository.listBalance()
    }

    @Transactional
    fun sales (model: Sale): Sale{
        rxUseRepository.findById(model.clinicalId)
        patientRepository.findById(model.patientId)
        userRepository.findById(model.userId)
        lensRepository.findById(model.lensId)

        val response = saleRepository.save(model)
        val responseFrame: Frame = frameRepository.findById(response.frameId)
        responseFrame.apply {
            frameStock = frameStock?.minus(model.quantity!!)
        }
        frameRepository.save(responseFrame)
        calculateAndUpdateTotal(response)
        calculateAndUpdateTotalBalance(response)

        return  response
    }
    fun calculateAndUpdateTotal(sale: Sale){
        val totalCalculated: Double = saleRepository.calculateTotalPrice(sale.id)
        val saleResponse = saleRepository.findById(sale.id!!).orElse(null)
        if(saleResponse != null){
            saleResponse.totalPrice = totalCalculated

            saleRepository.save(saleResponse)
        }else{
            throw Exception("Venta no encontrada conx")
        }
    }
    fun calculateAndUpdateTotalBalance(sale: Sale){
        val totalBalance: Double = saleRepository.calculateTotalBalance(sale.id)
        val saleBalance = saleRepository.findById(sale.id)
        saleBalance!!.balance = totalBalance

        saleRepository.save(saleBalance)
    }

}