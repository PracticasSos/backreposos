package com.Backend.sos.controller

import com.Backend.sos.dto.RegisterPatients
import com.Backend.sos.dto.deleteUser
import com.Backend.sos.model.Patients
import com.Backend.sos.model.User
import com.Backend.sos.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT], origins = ["http://localhost:3000"] )
class PatientController {
    @Autowired
    lateinit var patientService: PatientService

    @GetMapping
    fun login(): ResponseEntity<*> {

        return patientService.list()?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity<Patients>( HttpStatus.NOT_FOUND)
    }

    @PostMapping("/registro")
    fun register (@RequestBody request: RegisterPatients):ResponseEntity<Patients>{
        val registerPatient = patientService.registerPatient(request)
        return  ResponseEntity.ok(registerPatient)
    }

    @DeleteMapping("/eliminar")
    fun delete (@RequestBody request: deleteUser): Boolean?{
        return patientService.DeletePatient(request)
    }

    @PutMapping
    fun update(@RequestBody model: Patients): ResponseEntity<Patients>{
        return  ResponseEntity(patientService.update(model),HttpStatus.OK)
    }

  /*  @PatchMapping
    fun  updateRecords (@PathVariable ptfirstname: String, @RequestBody model: Patients):ResponseEntity<Patients>{
        return try {
            val updatedUser = patientService.updatePatient(ptfirstname, model)
            ResponseEntity.ok(updatedUser)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }*/
}