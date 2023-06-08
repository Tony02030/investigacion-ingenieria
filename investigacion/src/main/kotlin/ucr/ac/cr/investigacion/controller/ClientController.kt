package ucr.ac.cr.investigacion.controller

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.AccountRepository
import ucr.ac.cr.investigacion.repository.ClientRepository
import java.lang.IllegalArgumentException
import java.util.*

@Controller
class ClientController @Autowired constructor(
    private val clientRepository: ClientRepository,
    private val accountRepository: AccountRepository
) {

    @QueryMapping
    fun clients(): Iterable<Client> {
        return clientRepository.findAll()
    }

    @QueryMapping
    fun clientById(@Argument id: Int) : Client {
        return clientRepository.findById(id)
            .orElseThrow{ NotFoundException() }
    }

    @MutationMapping
    fun addClient(
        @Argument("name") name: String,
        @Argument("address") address: String,
        @Argument("phone") phone: String?,
        @Argument("email") email: String,
        @Argument("birthDate") birthDate: Date?
    ): Client {
        //validate the arguments
        if(name.isBlank() || address.isBlank() || email.isBlank() ) {
            throw IllegalArgumentException("Los campos 'name', 'address' y 'email' son obligatorios")
        }

        val client = Client(
            name = name,
            address = address,
            phone = phone,
            email = email,
            birthDate = birthDate
        )

        return clientRepository.save(client)
    }

    @MutationMapping
    fun updateClient(
        @Argument id: Int,
        @Argument("name") name: String?,
        @Argument("address") address: String?,
        @Argument("phone") phone: String?,
        @Argument("email") email: String?,
        @Argument("birthDate") birthDate: Date?
    ): Client {
        val existingClient = clientRepository.findById(id)
            .orElseThrow { NotFoundException() }

            // Create a new client with updated properties
            val updatedClient = existingClient.copy(
                name = name ?: existingClient.name,
                address = address ?: existingClient.address,
                phone = phone ?: existingClient.phone,
                email = email ?: existingClient.email,
                birthDate = birthDate ?: existingClient.birthDate
            )

            return clientRepository.save(updatedClient)

    }

    @MutationMapping
    @Transactional
    fun deleteClient(@Argument id: Int) : Boolean {
        println(id)
        val existingClient = clientRepository.findById(id)
            .orElseThrow{ NotFoundException() }

        accountRepository.deleteByClient(existingClient)

        clientRepository.delete(existingClient)

        return true
    }


}