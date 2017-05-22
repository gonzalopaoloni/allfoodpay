package br.com.dalcatech.allfoodpay.service;

import br.com.dalcatech.allfoodpay.Utils.Constants;
import br.com.dalcatech.allfoodpay.Utils.HttpHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gustavo on 5/17/2017.
 */
public class Service {

    public static String doGet(String ws, String stringJson) {
        try {
            Map<String, String> map = new Gson().fromJson(stringJson, new TypeToken<HashMap<String, String>>() {
            }.getType());

            String url = Constants.WS_URL_BASE + ws;
            HttpHelper httpHelper = getHttpHelper();
            return httpHelper.doGet(url, map, Constants.UTF8);
        } catch (Exception e) {
            System.out.print(e);
            return Constants.WARNING_SERVER;
        }
    }

    protected static HttpHelper getHttpHelper() {
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.setContentType(Constants.CONTENT_TYPE_JSON_UTF8);
        return httpHelper;
    }



}
