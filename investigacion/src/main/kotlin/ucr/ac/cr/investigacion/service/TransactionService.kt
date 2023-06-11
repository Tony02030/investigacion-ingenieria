package ucr.ac.cr.investigacion.service

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import ucr.ac.cr.investigacion.entity.Transaction
import ucr.ac.cr.investigacion.repository.TransactionRepository
import java.lang.IllegalStateException
import java.util.*

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    fun getAllTransactions(): List<Transaction> {
        return transactionRepository.findAll() as List<Transaction>
    }

    fun getTransactionById(id: Int): Optional<Transaction> {
        return transactionRepository.findById(id)
    }

    fun addTransaction(transaction: Transaction): Transaction {
        validateTransactionData(transaction)

        val savedTransaction = transactionRepository.save(transaction)
        return savedTransaction
    }

    private fun validateTransactionData(transaction: Transaction) {
        if(transaction.sourceAccount.accountNumber.isBlank()) {
            throw IllegalArgumentException("Source account number is required")
        }
        if(transaction.destinationAccount.accountNumber.isBlank()) {
            throw IllegalArgumentException("Destination account number is required")
        }
        if(transaction.amount < 0) {
            throw IllegalArgumentException("Amount must be greater than zero")
        }
    }

    fun updateTransaction(updatedTransaction: Transaction): Boolean {
        val existingTransaction = transactionRepository.findById(updatedTransaction.id)
            .orElseThrow { NotFoundException() }

        val updatedSourceAccount = updatedTransaction.sourceAccount ?: existingTransaction.sourceAccount
        val updatedDestinationAccount = updatedTransaction.destinationAccount ?: existingTransaction.destinationAccount
        val updatedAmount = updatedTransaction.amount ?: existingTransaction.amount
        val updatedDateTime = updatedTransaction.dateTime ?: existingTransaction.dateTime

        val updatedTransaction = existingTransaction.copy(
            sourceAccount = updatedSourceAccount,
            destinationAccount = updatedDestinationAccount,
            amount = updatedAmount,
            dateTime = updatedDateTime
        )

        transactionRepository.save(updatedTransaction)
        return true
    }

    fun deleteTransaction(id: Int) {
        val existingTransaction = transactionRepository.findById(id)
            .orElseThrow { NotFoundException() }

        transactionRepository.deleteById(id)
    }
}