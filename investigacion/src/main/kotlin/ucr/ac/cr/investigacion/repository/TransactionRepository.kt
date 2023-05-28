package ucr.ac.cr.investigacion.repository

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.investigacion.entity.Transaction

@Repository
interface TransactionRepository : CrudRepository<Transaction, Int>