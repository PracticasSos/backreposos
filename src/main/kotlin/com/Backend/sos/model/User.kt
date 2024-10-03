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
    @Column(nullable = false, unique = true, length = 20)
    var username: String = ""
    @Column(nullable = false, length = 55)
    var firstname: String = ""
    var lastname: String? = null
    @Column(nullable = false)
    var age: Int = 0
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
    @OneToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable=false, updatable=false)
    var branch: Branch? = null
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable=false, updatable=false)
    var role: Roles? = null
}
