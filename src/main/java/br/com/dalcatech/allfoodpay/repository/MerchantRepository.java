package br.com.dalcatech.allfoodpay.repository;

import br.com.dalcatech.allfoodpay.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Gustavo on 5/17/2017.
 */
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{
}
