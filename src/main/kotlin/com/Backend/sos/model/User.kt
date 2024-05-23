package com.Backend.sos.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date


@Entity
@Table(name = "users")
class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var firstname: String? = null
    var lastname: String? = null
    var age: Long? = null
    var charge: String? = null
    var birthdate: Date? = null
    @Column(name = "check_in_date")
    var checkInDate: Date? = null
    var ci: String? = null
    var email: String? = null
    @Column(name = "phone_number")
    var phoneNumber: String? = null
}

