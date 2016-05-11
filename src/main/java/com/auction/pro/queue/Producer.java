package com.auction.pro.queue;

import com.auction.pro.vehicle.dto.VehicleDto;

public interface Producer {
	int sendMessage(String vin);

	int sendMessage(VehicleDto report);
}
