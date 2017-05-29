package br.com.dalcatech.allfoodpay.entity;

/**
 * Created by Gustavo on 5/29/2017.
 */
public class SmsDTO {

    private String phone;
    private String pin;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
