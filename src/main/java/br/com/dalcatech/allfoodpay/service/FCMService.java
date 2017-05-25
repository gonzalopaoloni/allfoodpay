package br.com.dalcatech.allfoodpay.service;

import br.com.dalcatech.allfoodpay.Utils.Constants;
import br.com.dalcatech.allfoodpay.Utils.IOUtils;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.dalcatech.allfoodpay.entity.Customer;
import org.json.JSONObject;

/**
 * Created by Gustavo on 5/25/2017.
 */
public class FCMService {

    private Class clazzThis = this.getClass();
    private static Class clazz;

    @PostConstruct
    private void getSender(){
        //Inicia a classe
        clazz = clazzThis;
    }

    public static void sendPush(Customer customer, String action) {
        try {

            JSONObject jGcmData = new JSONObject();
            JSONObject jData = new JSONObject();

            jData.put("message", action);

            jGcmData.put("to", customer.getFcmToken());

            jGcmData.put("data", jData);

            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + Constants.SERVER_API_KEY_GOOGLE);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            System.out.println(resp);
            System.out.println("push sent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
