package ucr.ac.cr.investigacion.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ucr.ac.cr.investigacion.entity.Loan
import ucr.ac.cr.investigacion.entity.Transaction
import ucr.ac.cr.investigacion.repository.LoanRepository
import ucr.ac.cr.investigacion.repository.TransactionRepository

@Controller
class LoanController @Autowired constructor(private val loanRepository: LoanRepository) {

    @QueryMapping
    fun loan(): Iterable<Loan> {
        return loanRepository.findAll()
    }
}