package ucr.ac.cr.investigacion.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "Clients")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val birthDate: Date
)
