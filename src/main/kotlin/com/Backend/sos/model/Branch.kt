package com.Backend.sos.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "branch")
class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(name = "name_branch")
    var nameBranch: String? = null
    @Column(name = "address_branch", nullable = false)
    var addressBranch: String = ""
    @Column(nullable = false, unique = true)
    var email: String = ""
    var ruc: String? = null
    var tlf: String? = null
}