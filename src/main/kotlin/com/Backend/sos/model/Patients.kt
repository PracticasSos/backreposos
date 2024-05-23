package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "patients")
class    Patients {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "user_id")
    var userId: Long? = null
    var ptFirstname: String? = null
    var ptLastname: String? = null
    var ptOccupation: String? = null
    var ptAddress: String? = null
    var ptPhone: String? = null
    var ptAge: Long? = null
    var ptCi: String? = null
    var ptCity: String? = null
    var ptEmail: String? = null
    var ptConsultationReason: String? = null
    var ptRecommendations: String? = null

}