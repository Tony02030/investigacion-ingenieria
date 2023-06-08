package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.ClientRepository
import java.util.*

@Controller
class ClientController @Autowired constructor(
    private val clientRepository: ClientRepository
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
    fun addClient(
        @Argument("name") name: String,
        @Argument("address") address: String,
        @Argument("phone") phone: String?,
        @Argument("email") email: String,
        @Argument("birthDate") birthDate: Date?
    ): Client {
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
    fun updateClient(@Argument id: Int, @Argument("name") name: String?,
                     @Argument("address") address: String?,
                     @Argument("phone") phone: String?,
                     @Argument("email") email: String?,
                     @Argument("birthDate") birthDate: Date?) : Optional<Client> {
        val existingClientOptional = clientById(id)
        if (existingClientOptional.isPresent) {
            val existingClient = existingClientOptional.get()

            // Create a new client with updated properties
            val updatedClient = existingClient.copy(
                name = name ?: existingClient.name,
                address = address ?: existingClient.address,
                phone = phone ?: existingClient.phone,
                email = email ?: existingClient.email,
                birthDate = birthDate ?: existingClient.birthDate
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