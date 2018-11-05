package com.lastminute.utility;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class which contains specific functions which can be helpful in order to make a flight research
 * @author Tiziano
 *
 */
public class SearchFlightHelper {

	/**
	 * Get the number of days prior to the departure of the flight
	 * @param departureDate
	 * @return
	 */
	public static long getDaysPriorDeparture (Date departureDate)
	{
		Date currentDate = new Date();
		long diffInMillies = departureDate.getTime() - currentDate.getTime();
	    long daysPriorDeparture = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    return daysPriorDeparture;
	}
}
