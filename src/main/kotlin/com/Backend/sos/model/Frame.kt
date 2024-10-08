package com.Backend.sos.model

import jakarta.persistence.*


@Entity
@Table(name = "frame")
class Frame {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var brand: String? = null //marca
    var reference: String? = null
    var size: Int? = null
    var bridge: String? = null
    var model: String? = null
    var color: String? = null
    var rod: String? = null
    var price: Double? = null
    @Column(name = "frame_stock")
    var frameStock: Int? = null
}