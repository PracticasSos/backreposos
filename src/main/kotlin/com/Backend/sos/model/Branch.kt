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
}