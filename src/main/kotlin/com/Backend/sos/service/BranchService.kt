package com.Backend.sos.service

import com.Backend.sos.model.Branch
import com.Backend.sos.repository.BranchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalArgumentException

@Service
class BranchService {
    @Autowired
    lateinit var branchRepository: BranchRepository

    fun list(): List<Branch>{
        return branchRepository.findAll()
    }

    fun listById(id: Long?): Branch?{
        return branchRepository.findById(id)
    }

    fun save(branch: Branch): Branch{
        val validateGmail = branch.email?.takeIf { it.trim().isNotEmpty() }
            ?: throw IllegalArgumentException("Debe tener un correo.")

        if (!validateGmail.endsWith("@gmail.com")){
            throw IllegalArgumentException("El correo debe ser del dominio de gmail.")
        }
        branch.email = validateGmail
        try{
            return branchRepository.save(branch)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update (branch: Branch):Branch{
        try {
            branchRepository.findById(branch.id)
                ?: throw Exception("EL usuairo no existe")

            return branchRepository.save(branch)

        }catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateDate(branch: Branch): Branch{
        try {
            val responseBranch = branchRepository.findById(branch.id)
                ?: throw Exception("Id no existe")
            responseBranch.apply {
                nameBranch = branch.nameBranch
                addressBranch = branch.addressBranch
                email = branch.email
                ruc = branch.ruc
                tlf = branch.tlf
            }
            return branchRepository.save(branch)
        }catch (ex: Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }



}