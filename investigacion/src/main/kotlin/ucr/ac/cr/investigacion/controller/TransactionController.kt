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
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.entity.Transaction
import ucr.ac.cr.investigacion.service.TransactionService
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime

@Controller
class TransactionController @Autowired constructor(
    private val transactionService: TransactionService
) {

    @QueryMapping
    fun transactions(): List<Transaction> {
        return transactionService.getAllTransactions()
    }

    @QueryMapping
    fun transactionById(@Argument id: Int): Transaction {
        return transactionService.getTransactionById(id)
            .orElseThrow { NotFoundException() }
    }

    @MutationMapping
    fun addTransaction(
        @Argument("sourceAccount") sourceAccount: Account,
        @Argument("destinationAccount") destinationAccount: Account,
        @Argument("amount") amount: Float
    ): Transaction {
        // Validate the arguments
        if (sourceAccount.accountNumber.isBlank() || destinationAccount.accountNumber.isBlank() || amount <= 0) {
            throw IllegalArgumentException("Invalid transaction data")
        }

        val transaction = Transaction(
            id = 0,
            sourceAccount = sourceAccount,
            destinationAccount = destinationAccount,
            amount = amount,
            dateTime = LocalDateTime.now()
        )

        return transactionService.addTransaction(transaction)
    }

    @MutationMapping
    fun updateTransaction(
        @Argument("id") id: Int,
        @Argument("sourceAccount") sourceAccount: Account?,
        @Argument("destinationAccount") destinationAccount: Account?,
        @Argument("amount") amount: Float?,
        @Argument("dateTime") dateTime: LocalDateTime?
    ): Transaction {
        val existingTransaction = transactionService.getTransactionById(id)
            .orElseThrow { NotFoundException() }

        val updatedTransaction = existingTransaction.copy(
            sourceAccount = sourceAccount ?: existingTransaction.sourceAccount,
            destinationAccount = destinationAccount ?: existingTransaction.destinationAccount,
            amount = amount ?: existingTransaction.amount,
            dateTime = dateTime ?: existingTransaction.dateTime
        )

        return transactionService.updateTransaction(updatedTransaction)
    }

    @MutationMapping
    fun deleteTransaction(@Argument("id") id: Int): Boolean {
        transactionService.deleteTransaction(id)
        return true
    }
}