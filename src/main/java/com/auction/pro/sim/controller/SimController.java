package com.auction.pro.sim.controller;


import net.wyless.www.GetNetworkConnectionOnlineStatusResponse;
import net.wyless.www.SingleActivateSIMResponse;
import net.wyless.www.SingleCancelDeviceLocationResponse;
import net.wyless.www.SingleDeActivateSIMResponse;
import net.wyless.www.SingleQueryHLRResponse;
import net.wyless.www.SingleRestoreSIMResponse;
import net.wyless.www.SingleSIMInquiryResponse;
import net.wyless.www.SingleSuspendSIMResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auction.pro.common.controller.AbstractController;
import com.auction.pro.sim.dto.SimDTO;
import com.auction.pro.sim.service.base.SimService;
import com.auction.pro.simclient.SimClient;
@Controller
@RequestMapping("/sim")
public class SimController extends AbstractController{
	@Autowired
	SimService simService;
	@Autowired
	SimClient simClient;

	@RequestMapping(value="/getByIdOrMsisdnOrsimNo",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody SimDTO getSimBySimNoOrMsisdnOrDeviceId(@RequestParam String id){
		
		SimDTO simDto=null;
		simDto=simService.findBySimNoOrMsisdnOrId(id);
		return simDto;
	}
	
	
	@RequestMapping(value="/queryHLR",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody SingleQueryHLRResponse getQueryHLR(@RequestParam String msisdn,@RequestParam String sim){
		SingleQueryHLRResponse response=null;
		try{
			response=simClient.queryHlr(sim, msisdn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	} 
	
	@RequestMapping(value="/inquireSim",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody SingleSIMInquiryResponse inquireSim(@RequestParam(value="msisdn",required=true) String msisdn ,@RequestParam(value="sim",required=false) String sim){
		SingleSIMInquiryResponse response=null;
		
		
		try{
			response=simClient.inquireSim(msisdn,sim);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	} 
	
	@RequestMapping(value="/getNetworkStatus",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody GetNetworkConnectionOnlineStatusResponse getNetworkStatus(@RequestParam(value="msisdn",required=true) String msisdn,@RequestParam String sim){
		GetNetworkConnectionOnlineStatusResponse response=null;
		try{
			response=simClient.getNetworkStatus(msisdn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	} 
	
	@RequestMapping(value="/cancelLocation",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody SingleCancelDeviceLocationResponse cancelLocation(@RequestParam(value="msisdn",required=true) String msisdn,@RequestParam(value="sim",required=true) String sim){
		SingleCancelDeviceLocationResponse response=null;
		try{
			response=simClient.cancelDeviceLocation(sim, msisdn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	} 
	
	@RequestMapping(value="/activateSim",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody SingleActivateSIMResponse activateSim(@RequestParam(value="msisdn",required=true) String msisdn,@RequestParam(value="sim",required=true) String sim){
		SingleActivateSIMResponse response=null;
		try{
			response=simClient.activateSubscriber(sim);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	} 
	
	@RequestMapping(value="/suspendSim",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody SingleSuspendSIMResponse suspendSim(@RequestParam(value="msisdn",required=true) String msisdn,@RequestParam(value="sim",required=true) String sim){
		SingleSuspendSIMResponse response=null;
		try{
			response=simClient.suspendSubscriber(msisdn,sim);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/resumeSim",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody SingleRestoreSIMResponse resumeSim(@RequestParam(value="msisdn",required=true) String msisdn,@RequestParam(value="sim",required=true) String sim){
		SingleRestoreSIMResponse response=null;
		try{
			response=simClient.restoreSubscriber(msisdn,sim);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
}
