package ucr.ac.cr.investigacion.service

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.entity.AccountType
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.AccountRepository
import ucr.ac.cr.investigacion.repository.AccountTypeRepository
import ucr.ac.cr.investigacion.repository.ClientRepository
import java.math.BigDecimal
import java.util.*

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val clientRepository: ClientRepository,
    private val accountTypeRepository: AccountTypeRepository
) {
    fun getAccounts(): List<Account> {
        return accountRepository.findAll()
    }

    fun getAccountById(accountNumber: String): Optional<Account> {
        return accountRepository.findById(accountNumber)
    }

    fun addAccount(accountNumber: String,
                   clientId: Int,
                   balance: BigDecimal,
                   accountTypeId: Int
    ): Account {
        val client = clientRepository.findById(clientId)
            .orElseThrow { NotFoundException() }

        val accountType = accountTypeRepository.findById(accountTypeId)
            .orElseThrow { NotFoundException() }

        val account = Account(
            accountNumber = accountNumber,
            client = client,
            balance = balance,
            accountType = accountType
        )
        return accountRepository.save(account)
    }

    fun updateAccount(
        accountNumber: String,
        newBalance: BigDecimal
    ): Boolean {
        val account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow { NotFoundException() }

        val updatedAccount = account.copy(balance = newBalance)

        accountRepository.save(updatedAccount)
        return true
    }

    fun deleteAccount(accountNumber: String): Boolean {
        val account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow { NotFoundException() }

        accountRepository.delete(account)
        return true
    }
}