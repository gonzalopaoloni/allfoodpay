package br.com.dalcatech.allfoodpay.Utils;

import br.com.dalcatech.allfoodpay.entity.Customer;
import br.com.dalcatech.allfoodpay.entity.Response;

/**
 * Created by Gustavo on 5/22/2017.
 */
public class StringUtils {
    public static Response validatePassword(Customer customer) {
        String password = customer.getPassword();

        if (password != null) {
            if (password.length() < 6) {
                return Response.error("Senha muito curta");
            } else if (password.length() > 16) {
                return Response.error("Senha muito longa");
            } else {
                return Response.ok("Senha válida");
            }
        } else {
            return Response.error("Senha inválida");
        }
    }

    public static Response validateCpf(Customer customer) {
        String cpf = customer.getCpf();
        if (cpf != null){
            return Response.ok("CPF válido");
        }else {
            return Response.error("CPF inválido");
        }
    }


}
