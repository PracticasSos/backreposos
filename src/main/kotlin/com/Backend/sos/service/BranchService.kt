package com.Backend.sos.service

import com.Backend.sos.model.Branch
import com.Backend.sos.repository.BranchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BranchService {
    @Autowired
    lateinit var branchRepository: BranchRepository

    fun list(): List<Branch>{
        return branchRepository.findAll()
    }

    fun save(branch: Branch): Branch{
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