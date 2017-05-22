package br.com.dalcatech.allfoodpay.repository;

import br.com.dalcatech.allfoodpay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Gustavo on 5/17/2017.
 */

@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c where c.cpf =:cpf")
    Customer findCustomerByCpf(@Param("cpf") String cpf);

}
