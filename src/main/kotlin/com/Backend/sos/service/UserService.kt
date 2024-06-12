package com.Backend.sos.service

import com.Backend.sos.dto.LoginRequest
import com.Backend.sos.dto.RegisterRequest
import com.Backend.sos.dto.TokenDto
import com.Backend.sos.model.Roles
import com.Backend.sos.model.User
import com.Backend.sos.repository.RoleRepository

import com.Backend.sos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalArgumentException


@Service

class UserService: UserDetailsService{
    @Autowired
    lateinit var userRepository: UserRepository


    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var roleRepository: RoleRepository


    @Autowired
    lateinit var passwordEncoder: PasswordEncoder


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


    fun register(request: RegisterRequest): TokenDto {
        if (userRepository.findByUsername(request.username) != null) {
            throw IllegalArgumentException("Este nombre de usuario ya está en uso.")
        }

        val user = User().apply {
            username = request.username
            firstname = request.firstname
            lastname = request.lastname
            age = request.age
            charge = request.charge
            birthdate = request.birthdate
            checkInDate = request.check_in_date
            ci = request.ci
            email = request.email
            phoneNumber = request.phone_number
            password = passwordEncoder.encode(request.password)
            locked = false
            disabled = false
        }

        // Asignar un rol por defecto
        val defaultRole = roleRepository.findByRoleName("Vendedor")
            ?: throw IllegalArgumentException("Role 'Vendedor' not found")
        user.role = setOf(defaultRole)

        val savedUser = userRepository.save(user)
        val userDetails = loadUserByUsername(savedUser.username!!)// se encarga de cargar los detalles del usuario desde la base de datos utilizando el nombre de usuario proporcionado.
        val token = jwtService.generateToken(userDetails)

        return TokenDto().apply {
            jwt = token
        }
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

    @Override
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
    }
}