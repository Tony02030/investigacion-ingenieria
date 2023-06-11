package ucr.ac.cr.investigacion.controller

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.service.ClientService
import java.lang.IllegalArgumentException
import java.util.*

@Controller
class ClientController @Autowired constructor(
    private val clientService: ClientService
) {

    @QueryMapping
    fun clients(): Iterable<Client> {
        return clientService.clients()
    }

    @QueryMapping
    fun clientById(@Argument id: Int) : Client {
        return clientService.clientById(id)
            .orElseThrow{ NotFoundException() }
    }

    @MutationMapping
    fun addClient(
        @Argument("name") name: String,
        @Argument("address") address: String,
        @Argument("phone") phone: String?,
        @Argument("email") email: String,
        @Argument("birthDate") birthDate: Date?
    ): Boolean {
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

        return clientService.addClient(client)
    }

    @MutationMapping
    fun updateClient(
        @Argument id: Int,
        @Argument("name") name: String?,
        @Argument("address") address: String?,
        @Argument("phone") phone: String?,
        @Argument("email") email: String?,
        @Argument("birthDate") birthDate: Date?
    ): Boolean {
        val existingClient = clientService.clientById(id)
            .orElseThrow { NotFoundException() }

            // Create a new client with updated properties
            val updatedClient = existingClient.copy(
                name = name ?: existingClient.name,
                address = address ?: existingClient.address,
                phone = phone ?: existingClient.phone,
                email = email ?: existingClient.email,
                birthDate = birthDate ?: existingClient.birthDate
            )

            return clientService.updateClient(updatedClient)

    }

    @MutationMapping
    @Transactional
    fun deleteClient(@Argument id: Int) : Boolean = clientService.deleteClient(id)

}