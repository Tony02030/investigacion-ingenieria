package ucr.ac.cr.investigacion.entity.input

import java.util.Date
data class ClientInput(

    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val birthDate: Date
)