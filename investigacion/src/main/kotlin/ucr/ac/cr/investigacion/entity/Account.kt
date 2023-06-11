package ucr.ac.cr.investigacion.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "Accounts")
data class Account(
    @Id
    val accountNumber: String,

    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: Client,

    val balance: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "account_type")
    val accountType: AccountType


)
