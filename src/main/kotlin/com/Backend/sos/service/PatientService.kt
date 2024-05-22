package com.Backend.sos.service

import com.Backend.sos.model.Patients
import com.Backend.sos.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PatientService {
    @Autowired
    lateinit var patientRepository: PatientRepository

    fun list():List<Patients>?{
        return patientRepository.findAll()
    }

    fun save (model: Patients): Patients{
        try {
            return patientRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update (model: Patients): Patients{
        try {
            patientRepository.findById(model.id)
                ?: throw Exception("ID no exists")

            return patientRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
        }

        fun updatePatient(ptFirstname: String, model: Patients): Patients{
            val allPatients = patientRepository.findAll()
            val patient = allPatients.find {it.ptFirstname == ptFirstname}
                ?:throw  Exception("Usuaro con el nombre $ptFirstname no se encuentra")

            patient.apply {
                if (model.ptFirstname != null) this.ptFirstname = model.ptFirstname
                if (model.ptLastname != null) this.ptLastname = model.ptLastname
                if (model.ptOccupation != null) this.ptOccupation = model.ptOccupation
                if (model.ptAddress != null) this.ptAddress = model.ptAddress
                if (model.ptPhone != null) this.ptPhone = model.ptPhone
                if (model.ptAge != null) this.ptAge = model.ptAge
                if (model.ptCi != null) this.ptCi = model.ptCi
                if (model.ptCity != null) this.ptCity = model.ptCity
                if (model.ptEmail != null) this.ptEmail = model.ptEmail
                if (model.ptConsultationReason != null) this.ptConsultationReason = model.ptConsultationReason
                if (model.ptRecommendations != null) this.ptRecommendations = model.ptRecommendations
            }
            return patientRepository.save(patient)
        }
}