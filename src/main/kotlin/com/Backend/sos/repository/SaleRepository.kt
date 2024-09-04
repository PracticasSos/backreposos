package com.Backend.sos.repository

import com.Backend.sos.model.Sale

import org.springframework.data.jpa.repository.JpaRepository

interface SaleRepository: JpaRepository< Sale, Long>{

    fun findById(id: Long?): Sale?

}