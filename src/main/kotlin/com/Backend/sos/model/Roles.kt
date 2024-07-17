package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "role" )
class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "role_name")
    var roleName: String? = null
}