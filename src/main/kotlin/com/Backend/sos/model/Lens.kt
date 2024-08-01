package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table (name = "lens")
class Lens {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "lens_type")
    var lensType: String? = null
    @Column(name = "lens_material")
    var lensMaterial: String? = null
    @Column(name = "lens_description")
    var lensDescription: String? = null
    @Column(name = "lens_color")
    var lensColor: String? = null
    @Column(name = "lens_price")
    var lensPrice: Double? = null
}