package com.Backend.sos.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "rx_recomended")
class RxRecomended {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "sphere_right")
    var sphereRight: String? = null

    @Column(name = "cylinder_right")
    var cylinderRight: String? = null

    @Column(name = "axis_right")
    var axisRight: String? = null

    @Column(name = "prism_right")
    var prismRight: String? = null

    @Column(name = "add_right")
    var addRight: String? = null

    @Column(name = "av_vl_right")
    var avVlRight: String? = null

    @Column(name = "dnp_right")
    var dnpRight: String? = null

    @Column(name = "alt_right")
    var altRight: String? = null

    @Column(name = "sphere_left")
    var sphereLeft: String? = null

    @Column(name = "cylinder_left")
    var cylinderLeft: String? = null

    @Column(name = "axis_left")
    var axisLeft: String? = null

    @Column(name = "prism_left")
    var prismLeft: String? = null

    @Column(name = "add_left")
    var addLeft: String? = null

    @Column(name = "av_vl_left")
    var avVlLeft: String? = null

    @Column(name = "dnp_left")
    var dnpLeft: String? = null

    @Column(name = "alt_left")
    var altLeft: String? = null

    @ManyToOne
    @JoinColumn(name = "patient_id")
    var patient: Patients? = null
}