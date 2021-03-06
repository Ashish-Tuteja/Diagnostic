package com.auction.pro.parameters.service;

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
import com.auction.pro.ecuController.model.EcuController;
import com.auction.pro.parameters.dao.base.ParametersDao;
import com.auction.pro.parameters.dto.ParametersDto;
import com.auction.pro.parameters.model.Parameters;
import com.auction.pro.parameters.service.base.ParametersService;

@Service
public class ParametersServiceImpl extends AbstractServiceImpl<ParametersDto, Parameters>
		implements ParametersService{
	@Autowired
	ParametersDao parametersDao;
	private boolean chekIfExists;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ParametersServiceImpl.class.getName());

	public ParametersDto save(ParametersDto entity) {
		// TODO Auto-generated method stub
		
		
			if (entity.getId() != null) {
			
			System.out.println("updating -------->");
			return getDTOForEntity(parametersDao.save((Parameters) getEntityFromDTO(
					entity, Parameters.class)));
		} else {
			System.out.println("Adding------------>");
			try{
			String paramDescId = parametersDao.getParamDescIdByControllerId(entity.getControllerId());
			LOGGER.info("ParameterDescId returned :::" + paramDescId + "for controller Id :::" + entity.getControllerId());
			int ParamDescId = Integer.parseInt(paramDescId.toString()) + 1;
			  
			entity.setParameterDescId(String.valueOf(ParamDescId));
			
			}catch(Exception e){
				e.printStackTrace();
				LOGGER.error("Parameter desc id not found");
			}
			return getDTOForEntity(parametersDao.save((Parameters) getEntityFromDTO(
					entity, Parameters.class)));
		}
	}

	public void delete(ParametersDto entity) {
		
		parametersDao.delete(populateModel(entity));
	}


	private Parameters populateModel(ParametersDto entity) {
		Parameters parameters = new Parameters();
		parameters.setControllerId(entity.getControllerId());
		return parameters;
	}

	@Override
	public ParametersDto getDTOForEntity(Parameters entity) {
		// TODO Auto-generated method stub
		return new ParametersDto(entity);
	}

	@Override
	public Object getEntityFromDTO(ParametersDto entity,
			@SuppressWarnings("rawtypes") Class classType) {
		if (classType.getSimpleName().equals("Parameters")) {
			return new Parameters(entity);
		}
		return null;

	}


	public List<ParametersDto> findBySerachterm(String contId,String searchterm,
			Serializable accountId) throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(parametersDao.findBySerachterm(contId,searchterm,
				accountId));
	}


	public List<ParametersDto> getparameters() throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(parametersDao.getParameters());
	}

	public Page<ParametersDto> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception {
		// TODO Auto-generated method stub
		return convertEntityPageToDTOPage(
				parametersDao.findAllPage(pageable, parentAccountId), pageable);
	}
	
	public Page<ParametersDto> findAllPageParameters(String controllerId , Pageable pageable, String parentAccountId)
			throws Exception {
		// TODO Auto-generated method stub
		return convertEntityPageToDTOPage(
				parametersDao.findAllPageParameters(controllerId, pageable, parentAccountId), pageable);
	}

	public ParametersDto findByParametersIp(String ip) {
		// TODO Auto-generated method stub
		return getDTOForEntity(parametersDao.findByParametersIp(ip));
	}

	@Override
	public List<ParametersDto> getDTOsForEntities(Iterable<Parameters> entities) {
		// TODO Auto-generated method stub
		Iterator<Parameters> iteratedevice = entities.iterator();
		List<ParametersDto> parametersDtos = new ArrayList<ParametersDto>();
		while (iteratedevice.hasNext()) {
			parametersDtos.add(getDTOForEntity(iteratedevice.next()));
		}
		return parametersDtos;
	}

	@Override
	public AbstractDAO<Parameters> getDAO() {
		return parametersDao;
	}

	public void delete(String id) {
		Parameters parameters= new Parameters();
		parameters.setId(id);
		parametersDao.delete(parameters);
		
	}
	
	public void deleteParams(String id) {
		Parameters parameters= new Parameters();
		parameters.setControllerId(id);
		parametersDao.deleteParams(parameters);
		
	}
	public List<ParametersDto> getParameters() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
