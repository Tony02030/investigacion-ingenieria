package ucr.ac.cr.investigacion.service

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import ucr.ac.cr.investigacion.entity.AccountType
import ucr.ac.cr.investigacion.repository.AccountTypeRepository
import java.util.*

@Service
class AccountTypeService(
    private val accountTypeRepository: AccountTypeRepository
) {

    fun getAccountTypes(): List<AccountType> {
        return accountTypeRepository.findAll().toList()
    }

    fun addAccountType(accountType: AccountType): Boolean {
        val savedAccountType = accountTypeRepository.save(accountType)
        return savedAccountType != null
    }

    fun updateAccountType(id: Int, name: String?): Boolean {
        val existingAccountType = accountTypeRepository.findById(id)
            .orElseThrow { NotFoundException() }

        val updatedAccountType = existingAccountType.copy(
            name = name ?: existingAccountType.name
        )

        val savedAccountType = accountTypeRepository.save(updatedAccountType)
        return savedAccountType != null
    }

    fun getAccountTypeById(id: Int): Optional<AccountType> {
        return accountTypeRepository.findById(id)
    }

    fun deleteAccountType(id: Int) {
        accountTypeRepository.deleteById(id)
    }
}