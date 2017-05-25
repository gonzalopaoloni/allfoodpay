package br.com.dalcatech.allfoodpay.session;

import br.com.dalcatech.allfoodpay.Utils.Constants;
import br.com.dalcatech.allfoodpay.Utils.StringUtils;
import br.com.dalcatech.allfoodpay.entity.Customer;
import br.com.dalcatech.allfoodpay.entity.Response;
import br.com.dalcatech.allfoodpay.repository.UserRepository;
import br.com.dalcatech.allfoodpay.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Gustavo on 5/17/2017.
 */
@Component
public class UserSession {

    private UserRepository repository;

    @Autowired
    public UserSession(UserRepository repository) {
        this.repository = repository;
    }

    public Response addUser(Customer customer) {
        try {
            Response responseCPF = StringUtils.validateCpf(customer);
            Response responsePassword = StringUtils.validatePassword(customer);
            if ((responseCPF.getStatus()).equals(Constants.STATUS_NOK)) {
                return responseCPF;
            } else {
                if (responsePassword.getStatus().equals(Constants.STATUS_NOK)) {
                    return responsePassword;
                } else {
                    if (repository.findCustomerByCpf(customer.getCpf()) != null) {
                        return Response.error("Usuário já cadastrado");
                    } else {
                        return Response.ok(repository.save(customer));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Problemas ao salvar usuário");
        }
    }

    public Response updateUser(Customer customer) {
        try {
            Customer existingCustomer = repository.findCustomerByCpf(customer.getCpf());
            if (existingCustomer != null) {
                existingCustomer.setCpf(customer.getCpf());
                existingCustomer.setDataNascimento(customer.getDataNascimento());
                existingCustomer.setEmail(customer.getEmail());
                existingCustomer.setName(customer.getName());
                existingCustomer.setGender(customer.getGender());
                existingCustomer.setRg(customer.getRg());
                repository.save(existingCustomer);

                Customer customer1 = repository.save(existingCustomer);
                return Response.ok(customer1);
            } else {
                return Response.error("Usuário não encontrado");
            }
        } catch (Exception e) {
            return Response.error("Problemas ao atualizar usuário");
        }
    }

    public Response getUsers() {
        try {
            return Response.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Problemas ao localizar usuários");
        }
    }

    public Response findUser(Long id) {
        try {
            return Response.ok(repository.findOne(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Problemas ao localizar usuário");
        }
    }

    public Response validateUser(Customer customer) {
        Customer existingCustomer = repository.findCustomerByCpf(customer.getCpf());
        if (existingCustomer != null) {
            if (existingCustomer.getPassword().equals(customer.getPassword())) {
                return Response.ok(existingCustomer);
            } else {
                return Response.error("Senha inválida");
            }
        } else {
            return Response.error("Usuário inexistente");
        }
    }

    public void sendPushReception(Customer customer){
        FCMService.sendPush(customer, Constants.ACTION_RECEPTION);
    }
}
