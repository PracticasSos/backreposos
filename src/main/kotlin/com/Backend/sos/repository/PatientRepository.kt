package com.Backend.sos.repository

import com.Backend.sos.model.Patients
import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository: JpaRepository<Patients, Long> {
    fun findById (id: Long?): Patients?

    fun findByPtFirstname(ptFirstname: String?): Patients?
}