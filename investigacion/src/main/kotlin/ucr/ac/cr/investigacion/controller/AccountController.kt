package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.entity.AccountType
import ucr.ac.cr.investigacion.repository.AccountRepository
import ucr.ac.cr.investigacion.repository.ClientRepository

@Controller
class AccountController @Autowired constructor(private val accountRepository: AccountRepository)  {

    @QueryMapping
    fun account(): Iterable<Account> {
        return accountRepository.findAll()
    }
}