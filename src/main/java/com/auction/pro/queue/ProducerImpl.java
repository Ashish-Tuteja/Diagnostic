package com.auction.pro.queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.auction.pro.vehicle.dto.VehicleDto;

@Component
public class ProducerImpl implements Producer {
	static JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		ProducerImpl.jmsTemplate = jmsTemplate;
	}

	public int sendMessage(String vin) {
		// TODO Auto-generated method stub
		try {
			jmsTemplate.convertAndSend(vin.toString());
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	public int sendMessage(VehicleDto report) {
		// TODO Auto-generated method stub
		jmsTemplate.convertAndSend(report);
		return 1;

	}

}
