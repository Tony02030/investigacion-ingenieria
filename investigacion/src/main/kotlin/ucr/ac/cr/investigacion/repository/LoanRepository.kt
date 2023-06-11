package ucr.ac.cr.investigacion.repository

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.investigacion.entity.Client
import ucr.ac.cr.investigacion.entity.Loan
import java.util.*

@Repository
interface LoanRepository : CrudRepository<Loan, Int> {
    fun deleteLoansByClient(client: Optional<Client>)
}