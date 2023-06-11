package ucr.ac.cr.investigacion.service

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import ucr.ac.cr.investigacion.entity.Loan
import ucr.ac.cr.investigacion.repository.ClientRepository
import ucr.ac.cr.investigacion.repository.LoanRepository
import java.util.*

@Service
class LoanService(
    private val loanRepository: LoanRepository,
    private val clientRepository: ClientRepository
) {
    fun getAllLoans(): List<Loan> {
        return loanRepository.findAll() as List<Loan>
    }

    fun getLoanById(id: Int): Optional<Loan> {
        return loanRepository.findById(id)
    }

    fun addLoan(loan: Loan, clientId: Int): Boolean {
        // Retrieve the client based on the clientId
        var client = clientRepository.findById(clientId)
            .orElseThrow { NotFoundException() }

        val loanWithClient = Loan(
            id = loan.id,
            amount = loan.amount,
            interestRate = loan.interestRate,
            termMonths = loan.termMonths,
            client = client
        )

        val savedLoan = loanRepository.save(loanWithClient)
        return savedLoan != null
    }

    fun updateLoan(loan: Loan, clientId: Int?): Boolean {
        clientId?.let { id ->
            val client = clientRepository.findById(id)
                .orElseThrow { NotFoundException() }

            val updatedLoan = Loan(
                id = loan.id,
                client = client,
                amount = loan.amount,
                interestRate = loan.interestRate,
                termMonths = loan.termMonths
            )

            val savedLoan = loanRepository.save(updatedLoan)
            return savedLoan != null
        }

        val savedLoan = loanRepository.save(loan)
        return savedLoan != null
    }


    fun deleteLoan(id: Int): Boolean {
        val existingLoan = loanRepository.findById(id)
            .orElseThrow { NotFoundException() }

        loanRepository.delete(existingLoan)
        return true
    }

}