package ucr.ac.cr.investigacion.entity

import java.math.BigDecimal
import jakarta.persistence.*

@Entity
@Table(name = "Loans")
data class Loan(
    @Id
    val id: Int,
    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: Client,
    val amount: BigDecimal,
    val interestRate: Float,
    val termMonths: Int
)
