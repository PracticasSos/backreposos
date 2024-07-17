package com.Backend.sos.repository

import com.Backend.sos.model.Branch
import org.springframework.data.jpa.repository.JpaRepository

interface BranchRepository: JpaRepository<Branch, Long> {

    fun findByNameBranch(nameBranch: String): Branch?

    fun findById(id: Long?): Branch?

}