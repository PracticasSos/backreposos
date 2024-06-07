package com.Backend.sos.model

import jakarta.persistence.*


import java.util.Date


@Entity
@Table(name = "users")
class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var username: String? = null
    var firstname: String? = null
    var lastname: String? = null
    var age: Int? = null
    var charge: String? = null
    var birthdate: Date? = null
    @Column(name = "check_in_date")
    var checkInDate: Date? = null
    var ci: String? = null
    @Column(length = 50)
    var email: String? = null
    @Column(name = "phone_number")
    var phoneNumber: String? = null
    @Column(nullable = false, length = 200)
    var password: String? = null
    @Column(nullable = false)
    var locked: Boolean? = null
    var disabled: Boolean? = null
    @ManyToOne
    @JoinColumn(name = "branch_id")
    lateinit var branch: Branch
    @ManyToOne
    @JoinColumn(name = "role_id")
    lateinit var role: Roles
}

