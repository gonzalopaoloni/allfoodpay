package br.com.dalcatech.allfoodpay.controller;

import br.com.dalcatech.allfoodpay.entity.Customer;
import br.com.dalcatech.allfoodpay.entity.Response;
import br.com.dalcatech.allfoodpay.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Gustavo on 5/17/2017.
 */

@RestController
@RequestMapping("/customer")
public class UserController {

    private UserSession session;

    @Autowired
    public UserController(UserSession session) {
        this.session = session;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response getUsers() {
        return session.getUsers();
    }

    @RequestMapping("/findCustomer")
    public Response findUser(@RequestBody Customer customer){
        return session.findUser(customer.getId());
    }

    @RequestMapping("/addCustomer")
    public Response addUser(@RequestBody Customer customer) {
        return session.addUser(customer);
    }

    @RequestMapping("/validateCustomer")
    public Response validateUser(@RequestBody Customer customer) {
        return session.validateUser(customer);
    }

    @RequestMapping("/updateCustomer")
    public void updateUser(@RequestBody Customer customer) {
        session.updateUser(customer);
    }

}
