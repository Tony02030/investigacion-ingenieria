package ucr.ac.cr.investigacion.controller

import ucr.ac.cr.investigacion.repository.AccountTypeRepository
import ucr.ac.cr.investigacion.repository.ClientRepository
import java.math.BigDecimal
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.repository.AccountRepository

@Controller
class AccountController @Autowired constructor(private val accountRepository: AccountRepository, private val clientRepository: ClientRepository, private val accountTypeRepository: AccountTypeRepository)  {

    @QueryMapping
    fun accounts(): Iterable<Account> {
        return accountRepository.findAll()
    }

    @QueryMapping
    fun accountById(@Argument id : String) : Optional<Account> {
        return accountRepository.findById(id);
    }


    @MutationMapping
    fun addAccount(
        @Argument("accountNumber") accountNumber: String,
        @Argument("clientId") clientId: Int,
        @Argument("balance") balance: BigDecimal,
        @Argument("accountTypeId") accountTypeId: Int
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

}