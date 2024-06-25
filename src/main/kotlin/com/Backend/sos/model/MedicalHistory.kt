package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "rx_use")
class MedicalHistory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "sphere_r")
    var sphereR: String? = null
    @Column(name = "cylinder_r")
    var cylinderR: String? = null
    @Column(name = "axis_r")
    var axisR: String? = null
    @Column(name = "prism_r")
    var prismR: String? = null
    @Column(name = "add_r")
    var addR: String? = null
    @Column(name = "av_vl_r")
    var avVlR: String? = null
    @Column(name = "dnp_r")
    var dnpR: Int? = null
    @Column(name = "alt_r")
    var altR: String? = null
    @Column(name = "sphere_l")
    var sphereL: String? = null
    @Column(name = "cylinder_l")
    var cylinderL: String? = null
    @Column(name = "axis_l")
    var axisL: String? = null
    @Column(name = "prism_l")
    var prismL: String? = null
    @Column(name = "add_l")
    var addL: String? = null
    @Column(name = "av_vl_l")
    var avVlL: String? = null
    @Column(name = "dnp_l")
    var dnpL: String? = null
    @Column(name = "alt_l")
    var altL: String? = null
}