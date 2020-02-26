package com.programozzteis.cardealer.cardealer.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CarDealerLogger {

	private static final Logger logger = LogManager.getLogger(CarDealerLogger.class);
	
	private CarDealerLogger()
	{
		/** Not available externally */
	}
	
	public static Logger getLogger()
	{
		return (logger);
	}
}
