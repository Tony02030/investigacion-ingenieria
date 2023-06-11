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

    @Column
    val amount: Float?,

    @Column
    val interestRate: Float?,

    @Column
    val termMonths: Int?
)
