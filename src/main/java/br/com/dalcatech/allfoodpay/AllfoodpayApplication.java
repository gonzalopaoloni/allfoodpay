package br.com.dalcatech.allfoodpay;

import br.com.dalcatech.allfoodpay.Utils.Constants;
import com.twilio.Twilio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AllfoodpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllfoodpayApplication.class, args);
		Twilio.init(Constants.SMS_USERNAME, Constants.SMS_PASSWORD);

	}
}
