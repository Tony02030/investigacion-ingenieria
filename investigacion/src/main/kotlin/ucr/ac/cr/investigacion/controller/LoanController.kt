package ucr.ac.cr.investigacion.controller

import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.RuntimeWiring
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Loan
import ucr.ac.cr.investigacion.service.ClientService
import ucr.ac.cr.investigacion.service.LoanService
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.NoSuchElementException

@Controller
class LoanController @Autowired constructor(
    private val loanService: LoanService,
    private val clientService: ClientService
) {
    @QueryMapping
    fun loans(): List<Loan> {
        return loanService.getAllLoans()
    }
    @QueryMapping
    fun loanById(@Argument id: Int): Loan {
        return loanService.getLoanById(id)
            .orElseThrow { NotFoundException() }
    }

    @MutationMapping
    fun addLoan(
        @Argument("clientId") clientId: Int,
        @Argument("amount") amount: Float,
        @Argument("interestRate") interestRate: Float,
        @Argument("termMonths") termMonths: Int
    ): Boolean {
        // Validate the arguments
        if (clientId <= 0 || amount <= 0 || interestRate <= 0 || termMonths <= 0) {
            throw IllegalArgumentException("Invalid loan data")
        }

        val client = clientService.clientById(clientId)
            .orElseThrow { NoSuchElementException("Client not found!") }

        val loan = Loan(
            client = client,
            amount = amount,
            interestRate = interestRate,
            termMonths = termMonths
        )

        return loanService.addLoan(loan, clientId)
    }

    @MutationMapping
    fun updateLoan(
        @Argument("loanId") loanId: Int,
        @Argument("clientId") clientId: Int,
        @Argument("amount") amount: Float?,
        @Argument("interestRate") interestRate: Float?,
        @Argument("termMonths") termMonths: Int?
    ): Boolean {
        val client = clientService.clientById(clientId)
            .orElseThrow { NoSuchElementException("Client not found!") }

        val loan = Loan(
            id = loanId,
            client = client, // Assign the client based on the client ID
            amount = amount,
            interestRate = interestRate,
            termMonths = termMonths ?: 0
        )

        return loanService.updateLoan(loan, clientId)
    }

    @MutationMapping
    fun deleteLoan(@Argument id: Int): Boolean {
        return loanService.deleteLoan(id)
    }
}