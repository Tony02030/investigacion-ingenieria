package ucr.ac.cr.investigacion.entity

import jakarta.persistence.*

@Entity
@Table(name = "Account_Type")
data class AccountType(
    @Id
    val id: Int,
    val name: String
)
