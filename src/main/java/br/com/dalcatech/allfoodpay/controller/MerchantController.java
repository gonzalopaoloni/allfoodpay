package br.com.dalcatech.allfoodpay.controller;

import br.com.dalcatech.allfoodpay.entity.Merchant;
import br.com.dalcatech.allfoodpay.entity.Response;
import br.com.dalcatech.allfoodpay.session.MerchantSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Gustavo on 5/17/2017.
 */

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    MerchantSession session;

    @Autowired
    public MerchantController(MerchantSession session) {
        this.session = session;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response getMerchants() {
        return session.getMerchants();
    }

    @RequestMapping("/addMerchant")
    public Response addUser(@RequestBody Merchant merchant) {
        return session.addMerchant(merchant);
    }

    @RequestMapping("/updateMerchant")
    public Response updateUser(@RequestBody Merchant merchant) {
        return session.updateMerchant(merchant);
    }

    @RequestMapping("/findMerchant")
    public Response findMerchant(@RequestBody Merchant merchant) {
        return session.findMerchant(merchant.getId());
    }
}
