package com.auction.pro.ecuController.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.ecuController.dao.base.EcuControllerAccountMapDao;
import com.auction.pro.ecuController.dao.base.EcuControllerDao;
import com.auction.pro.ecuController.dto.EcuControllerDto;
import com.auction.pro.ecuController.model.EcuController;
import com.auction.pro.ecuController.service.base.EcuControllerService;

@Service
public class EcuControllerServiceImpl extends AbstractServiceImpl<EcuControllerDto, EcuController>
		implements EcuControllerService {
	@Autowired
	EcuControllerDao ecuControllerDao;
	@Autowired
	EcuControllerAccountMapDao accountMapDao;
	private boolean chekIfExists;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EcuControllerServiceImpl.class.getName());

	public EcuControllerDto save(EcuControllerDto entity) {
		// TODO Auto-generated method stub
		int count=0;
		String[] controllerIds = entity.getControllerId().split(",");
		String[] years = entity.getYear().split(",");
		EcuControllerDto finalEntity = null;
		for(String controllerId : controllerIds){
			
			entity.setControllerId(controllerId);
			entity.setYear(years[count]);
			count++;
			
		
		
		if (entity.getId() != null) {
			chekIfExists = ecuControllerDao.exists((EcuController) getEntityFromDTO(entity,
					EcuController.class));
			finalEntity = getDTOForEntity(ecuControllerDao.save((EcuController) getEntityFromDTO(
					entity, EcuController.class)));
		} else {
			chekIfExists = ecuControllerDao.exists((EcuController) getEntityFromDTO(entity,
					EcuController.class));
			finalEntity = !chekIfExists ? getDTOForEntity(ecuControllerDao
					.save((EcuController) getEntityFromDTO(entity, EcuController.class)))
					: null;
		}
	}
		return finalEntity;
	}

	public void delete(EcuControllerDto entity) {
		
		ecuControllerDao.delete(populateModel(entity));
	}


	private EcuController populateModel(EcuControllerDto entity) {
		EcuController controller = new EcuController();
		controller.setControllerId(entity.getControllerId());
		return controller;
	}

	@Override
	public EcuControllerDto getDTOForEntity(EcuController entity) {
		// TODO Auto-generated method stub
		return new EcuControllerDto(entity);
	}

	@Override
	public Object getEntityFromDTO(EcuControllerDto entity,
			@SuppressWarnings("rawtypes") Class classType) {
		if (classType.getSimpleName().equals("EcuController")) {
			return new EcuController(entity);
		}
		return null;

	}

	public List<EcuControllerDto> findBySerachterm(String searchterm,
			Serializable accountId) throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(ecuControllerDao.findBySerachterm(searchterm,
				accountId));
	}



	public List<EcuControllerDto> getEcuControllers() throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(ecuControllerDao.getEcuControllers());
	}
	public Page<EcuControllerDto> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception {
		// TODO Auto-generated method stub
		return convertEntityPageToDTOPage(
				ecuControllerDao.findAllPage(pageable, parentAccountId), pageable);
	}

	public EcuControllerDto findByEcuControllerIp(String ip) {
		// TODO Auto-generated method stub
		return getDTOForEntity(ecuControllerDao.findByEcuControllerIp(ip));
	}

	@Override
	public List<EcuControllerDto> getDTOsForEntities(Iterable<EcuController> entities) {
		// TODO Auto-generated method stub
		Iterator<EcuController> iteratedevice = entities.iterator();
		List<EcuControllerDto> ecuControllerDTOs = new ArrayList<EcuControllerDto>();
		while (iteratedevice.hasNext()) {
			ecuControllerDTOs.add(getDTOForEntity(iteratedevice.next()));
		}
		return ecuControllerDTOs;
	}

	@Override
	public AbstractDAO<EcuController> getDAO() {
		return ecuControllerDao;
	}

	public void delete(String id) {
		EcuController controller = new EcuController();
		controller.setId(id);
		ecuControllerDao.delete(controller);
		
	}

}
