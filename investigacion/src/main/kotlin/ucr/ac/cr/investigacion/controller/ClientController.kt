package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.ClientRepository

@Controller
class ClientController@Autowired constructor(private val clientRepository: ClientRepository) {

    @QueryMapping
    fun clients(): Iterable<Client> {
        return clientRepository.findAll()
    }

}