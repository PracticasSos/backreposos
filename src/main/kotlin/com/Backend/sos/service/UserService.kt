package com.Backend.sos.service


import com.Backend.sos.dto.RegisterRequest
import com.Backend.sos.model.User
import com.Backend.sos.repository.BranchRepository
import com.Backend.sos.repository.RoleRepository
import com.Backend.sos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException


@Service

class UserService{
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var branchRepository: BranchRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    fun list():List<User>?{
        return userRepository.findAll()
    }



    fun register(request: RegisterRequest): User {
        if (userRepository.findByUsername(request.username) != null) {
            throw IllegalArgumentException("Este nombre de usuario ya está en uso.")
        }

        val roleName = request.roleNam ?: throw IllegalArgumentException("El nombre del rol es obligatorio.")
        val roleUser = roleRepository.findById(roleName)
            .orElseThrow { IllegalArgumentException("El rol especificado no existe: $roleName") }

        if (roleUser == null) {
            throw IllegalArgumentException("El rol especificado no existe: $roleName")
        }

        val brachRegister = request.nameBr ?: throw IllegalArgumentException("La sucursal es obligatioria.")
        val branchUser = branchRepository.findById(brachRegister)
            .orElseThrow { IllegalArgumentException("La rama asignada no existe: $brachRegister") }
        if (branchUser == null){
            throw IllegalArgumentException("La rama asignada no existe $brachRegister")
        }

        val email = request.email?.takeIf { it.trim().isNotEmpty() }
            ?: throw IllegalArgumentException("Debe tener un email.")

        if (!email.endsWith("@gmail.com")){
            throw IllegalArgumentException("El correo debe ser del dominio @gmail.com")
        }

        val user = User().apply {
            username = request.username?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("El Nombre de usuario no debe ser vacio")
            firstname = request.firstname ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un Nombre")
            lastname = request.lastname ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un Apellido")
            age = request.age!!.takeIf { it > 0 } ?: throw IllegalArgumentException("La edad es obligatoria y no puede ser 0")
            charge = request.charge ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un cargo")
            birthdate = request.birthdate
            checkInDate = request.check_in_date
            ci = request.ci ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un número de cédula")
            this.email = email
            phoneNumber = request.phone_number ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un número de telefono")
            password = request.password ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener una contraseña")
            role = roleUser
            locked = request.locked
            disabled = request.disable
            branch = branchUser
        }
        return userRepository.save(user)
    }



    fun DeleteUser (request:   User): Boolean? {
         val respose = userRepository.findById(request.id)?: throw Exception("El usuario no existe")
        userRepository.delete(respose)
        return true
    }



}