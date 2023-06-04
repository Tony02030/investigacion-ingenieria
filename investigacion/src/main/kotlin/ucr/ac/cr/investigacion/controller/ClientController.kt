package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.repository.ClientRepository

@Controller
class ClientController@Autowired constructor(private val clientRepository: ClientRepository) {

    @PostMapping("/client")
    @ResponseBody
    fun createClient(@RequestBody client: Client): Client {
        val createClient = clientRepository.save(client)
        return createClient
    }

    @QueryMapping
    fun clients(): Iterable<Client> {
        return clientRepository.findAll()
    }

}