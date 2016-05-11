package com.auction.pro.vehicle.dao.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.vehicle.model.VehicleReport;

public interface VehicleReportDao extends AbstractDAO<VehicleReport> {
	List<VehicleReport> findReports(String ip, Set<String> reportgroupId)
			throws Exception;

	public Map<String, Object> findbyGroupId(String groupid) throws Exception;

	public Page<VehicleReport> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception;
}
