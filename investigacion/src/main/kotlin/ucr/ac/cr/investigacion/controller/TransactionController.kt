package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.AccountType
import ucr.ac.cr.investigacion.entity.Transaction
import ucr.ac.cr.investigacion.repository.AccountTypeRepository
import ucr.ac.cr.investigacion.repository.TransactionRepository

@Controller
class TransactionController @Autowired constructor(private val transactionRepository: TransactionRepository ) {

    @QueryMapping
    fun transaction(): Iterable<Transaction> {
        return transactionRepository.findAll()
    }
}