package ucr.ac.cr.investigacion.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.Date

@Entity
@Table(name = "Transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "source_account_number")
    val sourceAccount: Account,
    @ManyToOne
    @JoinColumn(name = "destination_account_number")
    val destinationAccount: Account,
    val dateTime: LocalDateTime,
    val amount: Float
)
