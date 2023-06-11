package ucr.ac.cr.investigacion.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository;
import ucr.ac.cr.investigacion.entity.Client

@Repository
interface ClientRepository : JpaRepository<Client, Int>
