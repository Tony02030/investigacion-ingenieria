package ucr.ac.cr.investigacion.repository

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.investigacion.entity.AccountType

@Repository
interface AccountTypeRepository : CrudRepository<AccountType, Int>