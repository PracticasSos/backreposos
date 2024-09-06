package com.Backend.sos.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "Sale")
class Sale {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "patient_id")
    var patientId: Long? = null
    @Column(name = "clinical_history_id")
    var clinicalId: Long? = null
    @Column(name = "lens_id")
    var lensId: Long? = null
    @Column(name = "frame_id")
    var frameId: Long? = null
    @Column(name = "user_id")
    var userId: Long? = null
    var quantity: Int? = null
    @Column(name = "lead_time")
    var leadTime: Date? = null
    var discount: Double? = null
    @Column(name = "total_price")
    var totalPrice: Double? = null
    @Column(name = "advance_payment")
    var advancePayment: Double? = null
    var balance: Double? = null
}