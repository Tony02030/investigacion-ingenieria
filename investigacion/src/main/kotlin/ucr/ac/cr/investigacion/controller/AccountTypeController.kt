package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import ucr.ac.cr.investigacion.entity.AccountType
import ucr.ac.cr.investigacion.service.AccountTypeService

@Controller
class AccountTypeController @Autowired constructor(
    private val accountTypeService: AccountTypeService
) {

    @QueryMapping
    fun accountTypes(): List<AccountType> {
        return accountTypeService.getAccountTypes()
    }

    @QueryMapping
    fun accountTypeById(@Argument id: Int): AccountType {
        return accountTypeService.getAccountTypeById(id)
            .orElseThrow { NotFoundException() }
    }

    @MutationMapping
    fun addAccountType(
        @Argument("name") name: String
    ): Boolean {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name is required")
        }

        val accountType = AccountType(
            name = name
        )

        return accountTypeService.addAccountType(accountType)
    }

    @MutationMapping
    fun updateAccountType(
        @Argument id: Int,
        @Argument("name") name: String?
    ): Boolean {
        return accountTypeService.updateAccountType(id, name)
    }

    @MutationMapping
    @Transactional
    fun deleteAccountType(@Argument id: Int): Boolean {
        accountTypeService.deleteAccountType(id)
        return true
    }
}