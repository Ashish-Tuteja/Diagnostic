dashboard.factory('applyScale', function() {
	return {

		getFuelType: function(value) {
			switch(value) {
				case 0:
					 return "Not available";
				case 1:
					 return "Gasoline";
				case 2:
					return "Methanol";
				case 3:
					return "Ethanol";
				case 4:
					return "Diesel";
				case 5:
					return "LPG";
				case 6:
					return "CNG";
				case 7:
					return "Propane";
				case 8:
					return "Electric";
				case 9:
					return "Bifuel running Gasoline";
				case 10:
					return "Bifuel running Methanol";
				case 11:
					return "Bifuel running Ethanol";
				case 12:
					return "Bifuel running LPG";
				case 13:
					return "Bifuel running CNG";
				case 14:
					return "Bifuel running Propane";
				case 15:
					return "Bifuel running Electricity";
				case 16:
					return "Bifuel running electric and combustion engine";
				case 17:
					return "Hybrid Gasoline";
				case 18:
					return "Hybrid Ethanol";
				case 19:
					return "Hybrid Diesel";
				case 20:
					return "Hybrid Electric";
				case 21:
					return "Hybrid running electric and combustion engine";
				case 22:
					return "Hybrid Regenerative";
				case 23:
					return "Bifuel running diesel";
			}
    },
		getFuelStatus: function(value) {
			switch(value) {
				case 1:
					 return "Open loop due to insufficient engine temperature";
 				case 2:
 					 return "Closed loop, using oxygen sensor feedback to determine fuel mix";
 				case 4:
 					 return "Open loop due to engine load OR fuel cut due to deceleration";
 				case 8:
 					 return "Open loop due to system failure";
 				case 16:
 					 return "Closed loop, using at least one oxygen sensor but there is a fault in the feedback system";
				default:
					return "";
			}
		},
		getSecondaryAirStatus: function(value) {
			switch(value) {
				case 1:
					return "Upstream";
				case 2:
					return "Downstream of catalytic converter";
				case 4:
					return "From the outside atmosphere or off";
				case 8:
					return "Pump commanded on for diagnostic";
				default:
					return "";
			}
		},
    	scaleForMode1 : function(rawValue,parameterId) {

    		var initialValue = rawValue;
			var rawValueSize = rawValue.toString().split('').length;
			switch(rawValueSize)
			{
			case 1:
				 rawValue = '000'+rawValue.toString()
				break;
			case 2:
				 rawValue = '00'+rawValue.toString()
				break;
			case 3:
				 rawValue = '0'+rawValue.toString()
				break;
			}

//			console.log("rawValueSize: "+rawValue)

			var A = rawValue & 0xff;
			var B = (rawValue >> 8) & 0xff;
			var C = (rawValue >> 16) & 0xff;
			var D = (rawValue >> 24)  & 0xff;

//			console.log("A: "+A+" B :"+B+" C: "+C+" D: "+D)

			switch (parameterId) {
			/**
			case '0':
				// PIDs supported [01 - 20]
        return ;
        break;
			**/
			case '1':
				// Monitor status since DTCs cleared.
	      return (rawValue >> 31) & 0x0001;
        break;
			case '2':
				// Freeze DTC
				return (A+B);
				break;
			case '3':
				// Fuel system status
				return this.getFuelStatus(A);
				break;
			case '4':
				// Calculated engine load
				return (A/2.55).toFixed(2);
				break;
			case 5:
				// Engine coolant temperature
				return (A - 40);
				break;
			case '6':
				// Short term fuel trim—Bank 1
			case '7':
				// Long term fuel trim—Bank 1
			case '8':
				// Short term fuel trim—Bank 2
			case '9':
				// Long term fuel trim—Bank 2
				return ((A /1.28) - 100).toFixed(2);
				break;
			case '10':
				// Fuel pressure
				return (A * 3);
				break;
			case '11':
				// Intake manifold absolute pressure
				return A;
				break;
			case '12':
				// Engine RPM
				return (rawValue/4).toFixed(2);
				break;
			case '13':
				// Vehicle speed
				return A;
				break;
			case '14':
				// 	Timing advance
				return ((A/2) - 64).toFixed(2);
				break;
			case '15':
				// Intake air temperature
				return (A - 40);
				break;
			case '16':
				// MAF air flow rate
				return (rawValue/100).toFixed(2);
				break;
			case '17':
				// Throttle position
				return (A/2.55).toFixed(2);
				break;
			case '18':
				// Commanded secondary air status
			  	return this.getSecondaryAirStatus(A);
        		break;
			case '19':
				// Oxygen sensors present (in 2 banks)
        		return '';
        		break;
			case '20':
				// Oxygen Sensor 1 (Short term fuel trim)
      		case '21':
				// Oxygen Sensor 2 (Short term fuel trim)
	    	case '22':
				// Oxygen Sensor 3 (Short term fuel trim)
		  	case '23':
				// Oxygen Sensor 4 (Short term fuel trim)
		    case '24':
				// Oxygen Sensor 5 (Short term fuel trim)
		    case '25':
				// Oxygen Sensor 6 (Short term fuel trim)
		    case '26':
				// Oxygen Sensor 7 (Short term fuel trim)
		    case '27':
				// Oxygen Sensor 8 (Short term fuel trim)
				return ((1.28*B)-100).toFixed(2);
				break;
			/**
			case '28':
				// 	OBD standards this vehicle conforms to
			 case '29':
			 	// Oxygen sensors present (in 4 banks)
        		return ;
        		break;
			case '30':
				// Oxygen sensors present (in 4 banks)
        		return ;
        		break;
			**/
			case '31':
				// Run time since engine start
				return initialValue;
 				break;
				/*case '32':
					// PIDs supported [21 - 40]
		        return  ;
		        break;*/
			case '33':
				// Distance traveled with MIL on
				return (initialValue);
				break;
			case '34':
				// Fuel Rail Pressure
				return (0.079 * rawValue).toFixed(2);
				break;
			case '35':
				// Fuel Rail Gauge Pressure
				return (10 * rawValue);
				break;
			case '36':
				// Oxygen Sensor 1 (voltage)
			case '37':
				// Oxygen Sensor 2 (voltage)
			case '38':
				// Oxygen Sensor 3 (voltage)
			case '39':
				// Oxygen Sensor 4 (voltage)
			case '40':
				// Oxygen Sensor 5 (voltage)
			case '41':
				// Oxygen Sensor 6 (voltage)
			case '42':
				// Oxygen Sensor 7 (voltage)
			case '43':
				// Oxygen Sensor 8 (voltage)
        return ( (8/65536) * ((256*C)+D) ).toFixed(2);
        break;
			case '44':
				// Commanded EGR
				return (A/2.55).toFixed(2);
				break;
			case '45':
				// EGR Error
				return ((A * 1.28) - 100).toFixed(2) ;
				break;
			case '46':
				// Commanded evaporative purge
				return (A/2.55).toFixed(2);
				break;
			case '47':
				// Fuel Tank Level Input
				return (A/2.55).toFixed(2);
				break;
			case '48':
				// Warm-ups since codes cleared
				return A;
				break;
			case '49':
				// Distance traveled since codes cleared
				return initialValue;
				break;
			case '50':
				// Evap. System Vapor Pressure
				return (rawValue/4).toFixed(2);
				break;
			case '51':
				// Absolute Barometric Pressure
				return A;
				break;
			case '52':
				// Oxygen Sensor 1 (Current)
		  case '53':
				// Oxygen Sensor 2 (Current)
		  case '54':
				// Oxygen Sensor 3 (Current)
		  case '55':
				// Oxygen Sensor 4 (Current)
		  case '56':
				// Oxygen Sensor 5 (Current)
		  case '57':
				// Oxygen Sensor 6 (Current)
		  case '58':
				// Oxygen Sensor 7 (Current)
		  case '59':
				// Oxygen Sensor 8 (Current)
	      return ((C + (D/256)) - 128).toFixed(2);
	      break;
			case '60':
				// Catalyst Temperature: Bank 1, Sensor 1
			case '61':
				// 	Catalyst Temperature: Bank 2, Sensor 1
			case '62':
				// Catalyst Temperature: Bank 1, Sensor 2
			case '63':
				// Catalyst Temperature: Bank 2, Sensor 2
				return ((rawValue/10) - 40).toFixed(2);
				break;
			/*case '64':
				// PIDs supported [41 - 60]
        return null ;
        break;
			case '65':
				// Monitor status this drive cycle
        return null ;
        break;*/
			case '66':
				// Control module voltage
				// return (rawValue/1000);
				return Number(((rawValue)/1000)).toFixed(2);
			case '67':
				// Absolute load value
				return (rawValue * 2.55).toFixed(2);
				break;
			case '68':
				// 	Fuel–Air commanded equivalence ratio
				return ((2/65536)*rawValue).toFixed(2);
				break;
			case '69':
				// Relative throttle position
				return (A/2.55).toFixed(2);
			case '70':
				// Ambient air temperature
				return (A-40);
			case '71':
				// 	Absolute throttle position B
			case '72':
				// Absolute throttle position C
			case '73':
				// Accelerator pedal position D
			case '74':
				// Accelerator pedal position E
			case '75':
				// Accelerator pedal position F
			case '76':
				// 	Commanded throttle actuator
				return (A/2.55).toFixed(2);
				break;
			case '77':
				// Time run with MIL on
				return initialValue;
				break;
			case '78':
				// Time since trouble codes cleared
				return initialValue;
				break;
			case '81':
				// Fuel Type
				return this.getFuelType(A);
				break;
			case '82':
				// Ethanol fuel %
				return Number((A/2.55)).toFixed(2);
				//return (A/2.55) ;
				break;
			case '83':
				// Absolute Evap system Vapor Pressure
				return (rawValue/200).toFixed(2);
				break;
			case '84':
				//Evap system vapor pressure
				return 	(rawValue-32767);
			case '85':
				//Short term secondary oxygen sensor trim, A: bank 1
				return ((1.28*B)-100).toFixed(2);
			case '86':
				// Long term secondary oxygen sensor trim, A: bank 1
				return ((1.28*B)-100).toFixed(2);
			case '87':
				// 	Short term secondary oxygen sensor trim, A: bank 2
				return ((1.28*B)-100).toFixed(2);
			case '88':
				// Long term secondary oxygen sensor trim, A: bank 2
				return ((1.28*B)-100).toFixed(2);
			case '89':
				// Fuel rail absolute pressure
				return ((1.28*B)-100).toFixed(2);
			case '90':
				// Relative accelerator pedal position
				return ((1.28*B)-100).toFixed(2);
			case '91':
				// Hybrid battery pack remaining life
				return ((1.28*B)-100).toFixed(2);
			case '92':
				// Hybrid battery pack remaining life
				return ((1.28*B)-100).toFixed(2);
			case '93':
				// Engine oil temperature
				return ((rawValue/128)-210).toFixed(2);
			case '94':
				// Fuel injection timing
				return (rawValue/20).toFixed(2);
			case '97':
				// Driver's demand engine - percent torque
			case '98':
				// Actual engine - percent torque
				return (A-125);
			case '99':
				// Engine reference torque
				return (initialValue);

			default:
				return initialValue;
			}
		}

	}
})
