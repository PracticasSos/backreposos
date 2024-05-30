package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "roles" )
class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "role_name")
    var roleName: String? = null
}