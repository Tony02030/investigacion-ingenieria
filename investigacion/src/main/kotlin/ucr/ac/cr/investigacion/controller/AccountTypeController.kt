package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.AccountType
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.AccountTypeRepository
import ucr.ac.cr.investigacion.repository.ClientRepository

@Controller
class AccountTypeController @Autowired constructor(private val accountTypeRepository: AccountTypeRepository ) {

    @QueryMapping
    fun accountType(): Iterable<AccountType> {
        return accountTypeRepository.findAll()
    }
}