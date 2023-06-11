package ucr.ac.cr.investigacion.service

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.AccountRepository
import ucr.ac.cr.investigacion.repository.ClientRepository
import ucr.ac.cr.investigacion.repository.LoanRepository
import ucr.ac.cr.investigacion.repository.TransactionRepository
import java.util.*

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val loanRepository: LoanRepository
) {


    fun clients(): List<Client> {
        return clientRepository.findAll() as List<Client>
    }

    fun addClient(client: Client): Boolean {
        val client: Client = clientRepository.save(client)
        return client != null
    }

    fun updateClient(updatedClient: Client): Boolean {
        val existingClient = clientRepository.findById(updatedClient.id)
        if (existingClient.isPresent) {
            clientRepository.save(updatedClient)
            return true
        }
        return false
    }

    fun clientById(id: Int): Optional<Client> {
        return clientRepository.findById(id)
    }

    fun deleteClient(id: Int): Boolean {

            val existingClient = clientRepository.findById(id)

            val accounts = accountRepository.getAccountsByClient(existingClient)
            accounts.forEach { account ->
                transactionRepository.deleteTransactionsBySourceAccountOrDestinationAccount(account, account)
                accountRepository.delete(account)
            }
        if(existingClient.isEmpty) {
            return false
        } else {

            loanRepository.deleteLoansByClient(existingClient)

            clientRepository.deleteById(id)
            return true
        }

    }
}