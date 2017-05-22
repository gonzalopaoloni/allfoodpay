package br.com.dalcatech.allfoodpay.session;

import br.com.dalcatech.allfoodpay.entity.Merchant;
import br.com.dalcatech.allfoodpay.entity.Response;
import br.com.dalcatech.allfoodpay.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Gustavo on 5/17/2017.
 */

@Component
public class MerchantSession {

    MerchantRepository repository;

    @Autowired
    public MerchantSession(MerchantRepository repository) {
        this.repository = repository;
    }

    public Response getMerchants() {
        try {
            return Response.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Problemas ao recuperar dados");
        }
    }

    public Response addMerchant(Merchant merchant) {
        try {
            repository.save(merchant);
            return Response.ok("Cliente salvo com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Problemas ao adcionar cliente");

        }
    }

    public Response findMerchant(Long id) {
        try {
            return Response.ok(repository.findOne(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Problemas ao recuperar dados");
        }
    }

    public Response updateMerchant(Merchant merchant) {
        try {
            Merchant existingMerchant = repository.findOne(merchant.getId());
            if (existingMerchant != null) {
                existingMerchant.setDescription(merchant.getDescription());
                existingMerchant.setKey(merchant.getKey());
                existingMerchant.setLatitude(merchant.getLatitude());
                existingMerchant.setLongitude(merchant.getLongitude());
                existingMerchant.setName(merchant.getName());
                existingMerchant.setPicture(merchant.getPicture());
                existingMerchant.setSegment(merchant.getSegment());
                existingMerchant.setMerchantId(merchant.getMerchantId());
                return Response.ok("Usuário atualizado com sucesso");
            } else {
                return Response.error("Usuário não encontrado");
            }
        } catch (Exception e) {
            return Response.error("Usuário atualizado com sucesso");
        }
    }
}
