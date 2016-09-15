package com.auction.pro.simclient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.auction.pro.simclient.SimClient;

@Configuration
public class SimConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("net.wyless.www");
		return marshaller;
	}

	@Bean
	public SimClient simClient(Jaxb2Marshaller marshaller) {
		SimClient client = new SimClient();
		client.setDefaultUri("https://www.wyless.net/SingleSIMInquiry");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
