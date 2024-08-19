package com.Backend.sos.repository

import com.Backend.sos.model.Sales
import org.springframework.data.jpa.repository.JpaRepository

interface SaleRepository: JpaRepository< Sales, Long>{

    fun findById(id: Long?): Sales?

}