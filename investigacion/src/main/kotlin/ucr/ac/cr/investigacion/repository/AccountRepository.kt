package ucr.ac.cr.investigacion.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository;
import ucr.ac.cr.investigacion.entity.Account
import ucr.ac.cr.investigacion.entity.Client

@Repository
interface AccountRepository : CrudRepository<Account, String>