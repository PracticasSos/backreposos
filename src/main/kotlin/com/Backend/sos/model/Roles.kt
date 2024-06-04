package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "role" )
class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "role_name")
    var roleName: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User? = null

}