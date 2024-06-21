package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "user_view_pat_register")
class UserPatView {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var username: String? = null
    @Column(name = "pt_firstname")
    var ptFirstname: String? = null
    @Column(name = "pt_lastname")
    var ptLastname: String? = null
    @Column(name = "pt_occupation")
    var ptOccupation: String? = null
    @Column(name = "pt_address")
    var ptAddress: String? = null
    @Column(name = "pt_phone")
    var ptPhone: String? = null
    @Column(name = "pt_age")
    var ptAge: Int? = null
    @Column(name = "pt_ci")
    var ptCi: String? = null
    @Column(name = "pt_city")
    var ptCity: String? = null
    @Column(name = "pt_email")
    var ptEmail: String? = null
    @Column(name = "pt_consultation_reason")
    var ptConsultationReason: String? = null
    @Column(name = "pt_recommendations")
    var ptRecommendations: String? = null
}