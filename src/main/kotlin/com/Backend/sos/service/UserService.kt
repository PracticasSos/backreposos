package com.Backend.sos.service

import com.Backend.sos.dto.LoginRequest
import com.Backend.sos.dto.RegisterRequest
import com.Backend.sos.dto.TokenDto
import com.Backend.sos.model.Roles
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
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var branchRepository: BranchRepository



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
            password = request.password
            role = roleUser
            locked = request.locked
            disabled = request.disable
            branch = branchUser
        }




        return userRepository.save(user)
    }


//    @Override
//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(username: String): UserDetails {
//        val userEntity = userRepository.findByUsername(username)
//            ?: throw
//            UsernameNotFoundException(
//                "User $username not found."
//            )
//
//        return org.springframework.security.core.userdetails.User.builder()
//            .username(userEntity.username)
//            .password(userEntity.password)
//            .roles(userEntity.role.roleName)
//            .accountLocked(userEntity.locked!!)
//            .disabled(userEntity.disabled!!)
//            .build()
//    }
}