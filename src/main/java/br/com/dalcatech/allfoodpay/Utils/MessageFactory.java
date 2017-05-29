package br.com.dalcatech.allfoodpay.Utils;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Created by Gustavo on 5/29/2017.
 */
public class MessageFactory {
    private static PhoneNumber from = new PhoneNumber(Constants.SMS_PHONE_NUMBER);

    /**
     * @param text O texto que vai ser enviado ao usuário
     * @param phone O número de telefone
     */
    public static void sendSms(String text, String phone){
        Message message = Message.creator(new PhoneNumber(phone), from,
                text).create();
        System.out.println(message.getSid());
    }
}
