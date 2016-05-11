package com.auction.pro.common.constants;

public interface CimbleConstants {
	int VEHICLE_ECU_CONTROLLERS_FROM = 3;
	String GLOBAL_PARAMETER_TABLE_NAME = "Global Parameter";
	int GLOBAL_PARAMETER_TABLE_SIZE = 14;
	String VEHICLEECU_TABLE_NAME = "Vehicle ECU";
	int VEHICLEECU_TABLE_SIZE = 22;
	String GLOBALECU_TABLE_NAME = "Global ECU";
	int GLOBALECU_TABLE_SIZE = 8;
	String DEFAULT_CONTROLLER_ID = "ToyotaCameryPCM_01";
	String[] GLOBAL_PARAMTER_TABLE = { "Parameter_Description",
			"Controller_ID", "Message_Type", "IsEnhanced",
			"Byte_Offset (Where 1 is the RSID)",
			"Bit_Possition (Where 7 is the first and 0 is the last bit)",
			"Bit_Width", "Endieness", "Service_ID", "Parameter_ID",
			"Encoding_Type", "Is_Unique_to_ECU", "Trust_Factor",
			"Supported_By_ECU" };
	String[] GLOBAL_ECU_TABLE = { "KEY", "ECU_Description", "ECU RX ID",
			"ECU_TX_ID", "ECU_Extended_ID", "ECU_Worst_Case_Response_Latency",
			"ECU_Broadcast_ID_01", "ECU_Broadcast_ID_02" };
	String[] VEHICLE_ECU_TABLE = { "Vehicle_Make", "Vehicle_Model",
			"Vehicle_Start_Year", "Vehicle_End_Year", "Engine_Controller",
			"Transmission_Controller", "Body_Controller" };
	String MAKE = "Toyota";
	String MODEL = "Camery";
	String YEAR = "2011";
}
