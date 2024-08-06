package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "branch")
class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name_branch")
    var nameBranch: String? = null
    @Column(name = "address_branch")
    var addressBranch: String? = null
    var email: String? = null
    var ruc: String? = null
    var tlf: String? = null
}