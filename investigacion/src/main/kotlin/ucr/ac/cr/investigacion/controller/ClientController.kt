package ucr.ac.cr.investigacion.controller

import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.entity.input.ClientInput
import ucr.ac.cr.investigacion.repository.AccountRepository
import ucr.ac.cr.investigacion.repository.ClientRepository
import java.util.*

@Controller
class ClientController @Autowired constructor(
    private val clientRepository: ClientRepository,
    private val accountController: AccountController
) {

    @QueryMapping
    fun clients() : Iterable<Client> {
        return clientRepository.findAll()
    }

    @QueryMapping
    fun clientById(@Argument id : Int) : Optional<Client> {
        return clientRepository.findById(id);
    }

    @MutationMapping
    fun addClient(@Argument clientInput : ClientInput) : Client {
        val client = Client(
            name = clientInput.name,
            address = clientInput.address,
            phone = clientInput.phone,
            email = clientInput.email,
            birthDate = clientInput.birthDate
        )

        return clientRepository.save(client);
    }

    @MutationMapping
    fun updateClient(@Argument id: Int, @Argument clientInput: ClientInput) : Optional<Client> {
        val existingClientOptional = clientById(id)
        if (existingClientOptional.isPresent) {
            val existingClient = existingClientOptional.get()

            // Create a new client with updated properties
            val updatedClient = existingClient.copy(
                name = clientInput.name ?: existingClient.name,
                address = clientInput.address ?: existingClient.address,
                phone = clientInput.phone ?: existingClient.phone,
                email = clientInput.email ?: existingClient.email,
                birthDate = clientInput.birthDate ?: existingClient.birthDate
            )

            val savedClient = clientRepository.save(updatedClient)
            return Optional.of(savedClient)
        } else {
            return Optional.empty()
        }

    }

    @MutationMapping
    fun deleteClient(@Argument clientId: Int) : Boolean {

        clientRepository.deleteById(clientId)

        return true
    }


}