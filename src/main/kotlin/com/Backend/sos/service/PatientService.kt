package com.Backend.sos.service

import com.Backend.sos.dto.RegisterPatients
import com.Backend.sos.model.Patients
import com.Backend.sos.repository.PatientRepository
import com.Backend.sos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalArgumentException

@Service
class PatientService {
    @Autowired
    lateinit var patientRepository: PatientRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun list():List<Patients>?{
        return patientRepository.findAll()
    }



    fun update (model: Patients): Patients{
        try {
            patientRepository.findByPtFirstname(model.ptFirstname)
                ?: throw Exception("estos nombres no existen")
            return patientRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun registerPatient (request: RegisterPatients): Patients{
       val registerpt = userRepository.findByUsername(request.usuario)
           ?:throw  IllegalArgumentException("El usuario con el nombre de usuario '${request.usuario}' no existe")

        val patient = Patients().apply {
            user = registerpt
            ptFirstname = request.name
            ptLastname = request.apellido
            ptOccupation = request.ocupación
            ptAddress = request.dirección
            ptPhone = request.número
            ptAge = request.edad
            ptCi = request.cedula
            ptCity = request.ciudad
            ptEmail = request.email
            ptConsultationReason = request.motivo
            ptRecommendations = request.recomendación
        }
        return  patientRepository.save(patient)
    }

   /* fun updatePatient(ptFirstname: String, model: Patients): Patients{
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
    }*/
}