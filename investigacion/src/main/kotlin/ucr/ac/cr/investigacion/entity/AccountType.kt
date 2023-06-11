package ucr.ac.cr.investigacion.entity

import jakarta.persistence.*

@Entity
@Table(name = "Account_Type")
data class AccountType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String
)
