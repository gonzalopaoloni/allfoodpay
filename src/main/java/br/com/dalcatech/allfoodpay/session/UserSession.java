package br.com.dalcatech.allfoodpay.session;

import br.com.dalcatech.allfoodpay.Utils.Constants;
import br.com.dalcatech.allfoodpay.Utils.MessageFactory;
import br.com.dalcatech.allfoodpay.Utils.StringUtils;
import br.com.dalcatech.allfoodpay.entity.Customer;
import br.com.dalcatech.allfoodpay.entity.Response;
import br.com.dalcatech.allfoodpay.entity.SmsDTO;
import br.com.dalcatech.allfoodpay.repository.UserRepository;
import br.com.dalcatech.allfoodpay.service.FCMService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
                existingCustomer.setFcmToken(customer.getFcmToken());
                existingCustomer.setStatus(customer.getStatus());
                repository.save(existingCustomer);

                Customer response = repository.save(existingCustomer);
                return Response.ok(response);
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

    public Response findUser(Customer customer) {
        try {
            return Response.ok(repository.findCustomerByCpf(customer.getCpf()));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("Usuário não encontrado");
        }
    }

    public Response validateUser(Customer customer) {
        try {
            Customer existingCustomer = repository.findCustomerByCpf(customer.getCpf());
            if (existingCustomer != null) {
                if (existingCustomer.getStatus().equals(Constants.STATUS_VALIDADO)) {
                    if (existingCustomer.getPassword().equals(customer.getPassword())) {
                        return Response.ok(existingCustomer);
                    } else {
                        return Response.error("Senha inválida");
                    }
                } else {
                    return Response.error("Usuário não validado");
                }
            } else {
                return Response.error("Usuário inexistente");
            }
        }catch (Exception e){
            return Response.error("Problemas ao validar usuário");
        }
    }

    public Response sendSms(SmsDTO dto) {
        try {
            MessageFactory.sendSms(dto.getPin(), dto.getPhone());
            return Response.ok("SMS enviado, você receberá o código em instantes");
        } catch (Exception e) {
            return Response.error("Problemas ao enviar PIN");
        }
    }

    public void sendPushReception(Customer customer) {
        FCMService.sendPush(customer, Constants.ACTION_RECEPTION);
    }
}
