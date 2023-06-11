package ucr.ac.cr.investigacion.entity

import java.math.BigDecimal
import jakarta.persistence.*

@Entity
@Table(name = "Loans")
data class Loan(
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: Client,

    @Column(precision = 10, scale = 2)
    val amount: Float?,

    @Column(precision = 5, scale = 2)
    val interestRate: Float?,

    val termMonths: Int?
)
