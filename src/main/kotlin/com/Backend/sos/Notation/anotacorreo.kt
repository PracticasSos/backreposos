package com.Backend.sos.Notation

import com.Backend.sos.Validator.GmailValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [GmailValidator::class])
annotation class anotacorreo(
    val message: String = "El correo debe ser de Gmail",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
