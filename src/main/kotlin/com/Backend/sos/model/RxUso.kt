package com.Backend.sos.model

import jakarta.persistence.*

@Entity
@Table(name = "rx_use")
class RxUso {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "sphere_right")
    var sphereR: String? = null
    @Column(name = "cylinder_right")
    var cylinderR: String? = null
    @Column(name = "axis_right")
    var axisR: String? = null
    @Column(name = "prism_right")
    var prismR: String? = null
    @Column(name = "add_right")
    var addR: String? = null
    @Column(name = "av_vl_right")
    var avVlR: String? = null
    @Column(name = "dnp_right")
    var dnpR: Int? = null
    @Column(name = "alt_right")
    var altR: String? = null
    @Column(name = "sphere_left")
    var sphereL: String? = null
    @Column(name = "cylinder_left")
    var cylinderL: String? = null
    @Column(name = "axis_left")
    var axisL: String? = null
    @Column(name = "prism_left")
    var prismL: String? = null
    @Column(name = "add_left")
    var addL: String? = null
    @Column(name = "av_vl_left")
    var avVlL: String? = null
    @Column(name = "dnp_left")
    var dnpL: String? = null
    @Column(name = "alt_left")
    var altL: String? = null
    @Column(name = "id_user")
    var idUser: Long? = null
}