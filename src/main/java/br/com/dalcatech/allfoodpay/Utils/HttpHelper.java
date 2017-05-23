package br.com.dalcatech.allfoodpay.Utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Gustavo on 5/17/2017.
 */
public class HttpHelper {
    private final String TAG = "Http";
    public boolean LOG_ON = true;
    private String contentType;
    public int TIMEOUT_MILLIS = 30000;
    public int READ_TIMEOUT_MILLIS = 30000;
    private String charsetToEncode;
    private String basicAuth;


    public String doGet(String url) throws IOException {
        return this.doGet(url, (Map) null, "UTF-8");
    }

    public String doGet(String url, Map<String, String> params, String charset) throws IOException {
        String queryString = this.getQueryString(params);
        if (queryString != null && queryString.trim().length() > 0) {
            url = url + "?" + queryString;
        }

        if (this.LOG_ON) {
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {
            conn = (HttpURLConnection) u.openConnection();
            if (this.contentType != null) {
                conn.setRequestProperty("Content-Type", this.contentType);

            }

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setRequestProperty("accept", "application/json");
            conn.connect();
            InputStream e = null;
            int status = conn.getResponseCode();
            if (status >= 400) {
                e = conn.getErrorStream();
            } else {
                e = conn.getInputStream();
            }

            s = IOUtils.toString(e, charset);
            if (this.LOG_ON) {
            }

            e.close();
        } catch (IOException var13) {
            throw var13;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }

        return s;
    }


    public String getQueryString(Map<String, String> params) throws IOException {
        if (params != null && params.size() != 0) {
            String urlParams = null;
            Iterator var3 = params.keySet().iterator();

            while (var3.hasNext()) {
                String chave = (String) var3.next();
                Object objValor = params.get(chave);
                if (objValor != null) {
                    String valor = objValor.toString();
                    if (this.charsetToEncode != null) {
                        valor = URLEncoder.encode(valor, this.charsetToEncode);
                    }

                    urlParams = urlParams == null ? "" : urlParams + "&";
                    urlParams = urlParams + chave + "=" + valor;
                }
            }

            return urlParams;
        } else {
            return null;
        }
    }

    public String doPost(String url, byte[] params, String charset) throws IOException {

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {
            conn = (HttpURLConnection)u.openConnection();
            if(this.contentType != null) {
                conn.setRequestProperty("Content-Type", this.contentType);
            }

            if (basicAuth != null) {
                String auth = "Basic " + basicAuth;
                conn.setRequestProperty("Authorization", auth);
            }

            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Encoding", "gzip");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(TIMEOUT_MILLIS);
            conn.setReadTimeout(READ_TIMEOUT_MILLIS);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            OutputStream e;
            if(params != null) {
                e = conn.getOutputStream();
                e.write(params);
                e.flush();
                e.close();
            }

            int status = conn.getResponseCode();
            InputStream e1;
            if(status >= 400) {
                e1 = conn.getErrorStream();
            } else {
                e1 = conn.getInputStream();
            }

            s = IOUtils.toString(e1, charset);

            e1.close();
        } catch (IOException var12) {
            throw var12;
        } finally {
            if(conn != null) {
                conn.disconnect();
            }

        }

        return s;
    }



    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
