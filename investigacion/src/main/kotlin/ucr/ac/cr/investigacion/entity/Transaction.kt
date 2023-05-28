package ucr.ac.cr.investigacion.entity

import java.math.BigDecimal
import jakarta.persistence.*

@Entity
@Table(name = "Transactions")
data class Transaction(
    @Id
    val id: Int,
    @ManyToOne
    @JoinColumn(name = "source_account_number")
    val sourceAccount: Account,
    @ManyToOne
    @JoinColumn(name = "destination_account_number")
    val destinationAccount: Account,
    val dateTime: java.sql.Timestamp,
    val amount: BigDecimal
)
