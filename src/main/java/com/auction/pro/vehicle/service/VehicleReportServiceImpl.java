package com.auction.pro.vehicle.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.vehicle.dao.base.VehicleDao;
import com.auction.pro.vehicle.dao.base.VehicleReportDao;
import com.auction.pro.vehicle.dto.VehicleReportDto;
import com.auction.pro.vehicle.model.Vehicle;
import com.auction.pro.vehicle.model.VehicleReport;
import com.auction.pro.vehicle.service.base.VehicleReportService;

@Service
public class VehicleReportServiceImpl extends
		AbstractServiceImpl<VehicleReportDto, VehicleReport> implements
		VehicleReportService {
	@Autowired
	VehicleReportDao reportDao;
	@Autowired
	VehicleDao vehicleDao;

	public VehicleReportDto save(VehicleReportDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(VehicleReportDto entity) {
		// TODO Auto-generated method stub

	}

	public List<VehicleReportDto> findReportsById(String vin) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("vin received " + vin);
		Vehicle vehicle = vehicleDao.findByVIN(vin);
		System.out.println("vehicle returned for vin "+ vehicle);
		System.out.println("fffffffffffffffffff"+reportDao.findReports(null,
				vehicle.getReportgroupIds()).size());
		System.out.println("grp ids from vehicle================="+vehicle.getReportgroupIds());
		return getDTOsForEntities(reportDao.findReports(null,
				vehicle.getReportgroupIds()));
	}

	@Override
	public AbstractDAO<VehicleReport> getDAO() {
		// TODO Auto-generated method stub
		return reportDao;
	}

	@Override
	public VehicleReportDto getDTOForEntity(VehicleReport entity) {
		// TODO Auto-generated method stub
		return new VehicleReportDto(entity);
	}

	@Override
	public Object getEntityFromDTO(VehicleReportDto entity, Class classType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleReportDto> getDTOsForEntities(
			Iterable<VehicleReport> entities) {
		// TODO Auto-generated method stub
		Iterator<VehicleReport> iterator = entities.iterator();
		List<VehicleReportDto> reportDTOs = new ArrayList<VehicleReportDto>();
		while (iterator.hasNext()) {
			reportDTOs.add(getDTOForEntity(iterator.next()));
		}
		return reportDTOs;
	}

	public List<VehicleReportDto> findReportsByIp(String ip) throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(reportDao.findReports(ip, null));
	}

	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
	}

}
