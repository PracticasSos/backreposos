package com.Backend.sos.service


import com.Backend.sos.dto.RegisterRequest
import com.Backend.sos.dto.deleteUser
import com.Backend.sos.model.User
import com.Backend.sos.repository.BranchRepository
import com.Backend.sos.repository.RoleRepository
import com.Backend.sos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
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

    fun save (model: User): User{
        try{
            return userRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun register(request: RegisterRequest): User {
        if (userRepository.findByUsername(request.username) != null) {
            throw IllegalArgumentException("Este nombre de usuario ya está en uso.")
        }

        val roleName = request.roleNam ?: throw IllegalArgumentException("El nombre del rol es obligatorio.")
        val roleUser = roleRepository.findByRoleName(roleName)

        if (roleUser == null) {
            throw IllegalArgumentException("El rol especificado no existe: $roleName")
        }

        val brachRegister = request.nameBr ?: throw IllegalArgumentException("La sucursal es obligatioria.")
        val branchUser = branchRepository.findByNameBranch(brachRegister)
        if (branchUser == null){
            throw IllegalArgumentException("La rama asignada no existe $brachRegister")
        }


        val user = User().apply {
            username = request.username?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("El Nombre de usuario no debe ser vacio")
            firstname = request.firstname ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un Nombre")
            lastname = request.lastname ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un Apellido")
            age = request.age!!.takeIf { it > 0 } ?: throw IllegalArgumentException("La edad es obligatoria y debe ser mayor que cero")
            charge = request.charge ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un cargo")
            birthdate = request.birthdate
            checkInDate = request.check_in_date
            ci = request.ci ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un número de cédula")
            email = request.email ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un email")
            phoneNumber = request.phone_number ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener un número de telefono")
            password = request.password ?.takeIf { it.trim().isNotEmpty() } ?: throw Exception("Debe tener una contraseña")
            role = roleUser
            locked = request.locked
            disabled = request.disable
            branch = branchUser
        }
        return userRepository.save(user)
    }

    fun DeleteUser (request: deleteUser): Boolean? {
         val respose = userRepository.findByUsername(request.username)?: throw Exception("El usuario no existe")
        userRepository.delete(respose)
        return true
    }

    fun updateRecords(firstname: String, model: User): User {
        val allUsers = userRepository.findAll()
        val user = allUsers.find { it.firstname == firstname }
            ?: throw Exception("Usuario con el nombre $firstname no se registró")

        user.apply {
            if (model.firstname != null) this.firstname = model.firstname
            if (model.lastname != null) this.lastname = model.lastname
            if (model.charge != null) this.charge = model.charge
            if (model.birthdate != null) this.birthdate = model.birthdate
            if (model.checkInDate != null) this.checkInDate = model.checkInDate
            if (model.ci != null) this.ci = model.ci
            if (model.email != null) this.email = model.email
            if (model.phoneNumber != null) this.phoneNumber = model.phoneNumber
            if (model.age != null) this.age = model.age
        }
        return userRepository.save(user)
    }

 /*   @Override
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User $username not found.")

        val role = roleRepository.findByRoleName(roleName = "Vendedor")
            ?: throw UsernameNotFoundException("Role 'Vendedor' not found.")

        return org.springframework.security.core.userdetails.User.builder()
            .username(userEntity.username)
            .password(userEntity.password)
            .roles(role.roleName) // Aquí solo asignamos el nombre del rol
            .accountLocked(userEntity.locked!!)
            .disabled(userEntity.disabled!!)
            .build()
    }*/
}