package ucr.ac.cr.investigacion.controller

import java.math.BigDecimal
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.service.AccountService

@Controller
class AccountController @Autowired constructor(
    private val accountService: AccountService,
){

    @QueryMapping
    fun accounts(): List<Account> {
        return accountService.getAccounts()
    }

    @QueryMapping
    fun accountById(@Argument id : String) : Account {
        return accountService.getAccountById(id)
            .orElseThrow { NotFoundException() }
    }

    @MutationMapping
    fun addAccount(
        @Argument("accountNumber") accountNumber: String,
        @Argument("clientId") clientId: Int,
        @Argument("balance") balance: BigDecimal,
        @Argument("accountTypeId") accountTypeId: Int
    ): Account {

        return accountService.addAccount(accountNumber, clientId, balance, accountTypeId)
    }

    @MutationMapping
    fun updateAccount(
        @Argument("accountNumber") accountNumber: String,
        @Argument("balance") balance: BigDecimal,
    ): Boolean {
        return accountService.updateAccount(accountNumber, balance)
    }

    @MutationMapping
    @Transactional
    fun deleteAccount(@Argument accountNumber: String): Boolean {
        accountService.deleteAccount(accountNumber)
        return true
    }

}