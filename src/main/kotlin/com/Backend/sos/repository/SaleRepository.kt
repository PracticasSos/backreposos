package com.Backend.sos.repository

import com.Backend.sos.model.Sale

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface SaleRepository: JpaRepository< Sale, Long>{

    fun findById(id: Long?): Sale?

    @Query(nativeQuery = true)
    fun calculateTotalPrice(@Param("saleId") saleId: Long?): Double

    @Query(nativeQuery = true)
    fun calculateTotalBalance(@Param("saleId") saleId: Long?): Double

    @Query(nativeQuery = true)
    fun listBalance(): List<Sale>

}