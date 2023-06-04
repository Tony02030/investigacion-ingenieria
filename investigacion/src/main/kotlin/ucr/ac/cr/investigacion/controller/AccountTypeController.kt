package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import ucr.ac.cr.investigacion.entity.AccountType
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.AccountTypeRepository
import ucr.ac.cr.investigacion.repository.ClientRepository

@Controller
class AccountTypeController @Autowired constructor(private val accountTypeRepository: AccountTypeRepository ) {

    @PostMapping("/account-type")
    @ResponseBody
    fun createAccount(@RequestBody accountType: AccountType): AccountType {
        val createAccountType = accountTypeRepository.save(accountType)
        return createAccountType
    }

    @QueryMapping
    fun accountType(): Iterable<AccountType> {
        return accountTypeRepository.findAll()
    }
}