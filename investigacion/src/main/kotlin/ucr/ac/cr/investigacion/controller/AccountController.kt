package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.repository.AccountRepository

@Controller
class AccountController @Autowired constructor(private val accountRepository: AccountRepository)  {

    @PostMapping("/account")
    @ResponseBody
    fun createAccount(@RequestBody account: Account): Account {
        val createAccount = accountRepository.save(account)
        return createAccount
    }

    @QueryMapping
    fun account(): Iterable<Account> {
        return accountRepository.findAll()
    }
}