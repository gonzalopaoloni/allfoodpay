package br.com.dalcatech.allfoodpay.entity;

import br.com.dalcatech.allfoodpay.Utils.Constants;

/**
 * Created by Gustavo on 5/18/2017.
 */
public class Response {

    public String status;
    public String message;
    public Object data;

    private static Response response;


    public static Response error(String erro) {
        Response r = getInstance();
        r.setStatus(Constants.STATUS_OK);
        r.setMessage(erro);
        return r;
    }

    public static Response ok(Object o) {
        Response r = getInstance();
        r.setStatus(Constants.STATUS_NOK);
        r.setData(o);
        return r;
    }


    public static Response getInstance(){
        if(response == null){
            response = new Response();
        }
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
