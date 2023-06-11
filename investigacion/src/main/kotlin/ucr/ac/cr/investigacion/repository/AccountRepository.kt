package ucr.ac.cr.investigacion.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository;
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.entity.Client
import java.util.Optional

@Repository
interface AccountRepository : JpaRepository<Account, String> {
    fun findByAccountNumber(accountNumber: String): Optional<Account>
    fun getAccountsByClient(client: Optional<Client>): List<Account>
}