package com.Backend.sos.dto

import java.util.Date
class RegisterRequest { //Captura los datos de registro del usuario
    var username: String? = null
    var firstname: String? = null
    var lastname: String? = null
    var age: Int? = null
    var charge: String? = null
    var birthdate: Date? = null
    var check_in_date: Date? = null // Manteniendo el nombre en snake_case
    var ci: String? = null
    var email: String? = null
    var phone_number: String? = null
    var password: String? = null
}