package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.entity.input.ClientInput
import ucr.ac.cr.investigacion.repository.ClientRepository
import graphql.kic

@Component
class ClientResolver @Autowired constructor(private val clientRepository: ClientRepository) : GraphQLQueryResolver,
    GraphQLMutationResolver {

    fun clients(): Iterable<Client> {
        return clientRepository.findAll()
    }

    fun addClient(input: ClientInput): Client {
        val newClient = Client(
            name = input.name,
            address = input.address,
            phone = input.phone,
            email = input.email,
            birthDate = input.birthDate
        )
        return clientRepository.save(newClient)
    }

}