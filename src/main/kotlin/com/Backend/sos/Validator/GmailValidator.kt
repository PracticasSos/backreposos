package com.Backend.sos.Validator

import com.Backend.sos.Notation.anotacorreo
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class GmailValidator : ConstraintValidator<anotacorreo, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        // Si el valor es nulo o está vacío, no es válido
        if (value.isNullOrEmpty()) {
            return false
        }
        // Verifica si el correo termina con @gmail.com
        return value.endsWith("@gmail.com")
    }
}