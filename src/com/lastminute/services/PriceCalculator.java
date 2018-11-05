package com.lastminute.services;

import java.math.BigDecimal;

/**
 * Class that compute operations for the flights' prices
 * @author Tiziano
 *
 */
public class PriceCalculator {

	static final String BOOKED_PRIOR_30_DAYS = "0.8";
	static final String BOOKED_PRIOR_16_BETWEEN_30  = "1";
	static final String BOOKED_PRIOR_3_TO_15_DAYS  = "1.2";
	static final String BOOKED_PRIOR_3_DAYS = "1.5";

	/**
	 * Compute the price of the ticket based on the number of passenger and the days prior departure
	 * @param passengers
	 * @param daysPriorDeparture
	 * @param price
	 * @return
	 */
	public static BigDecimal getDiscountedPrice(int passengers, long daysPriorDeparture, BigDecimal price)
	{
		BigDecimal daysFactor = null;
		if (daysPriorDeparture > 30) {
			daysFactor = new BigDecimal(BOOKED_PRIOR_30_DAYS);
		} else if (daysPriorDeparture >= 16 && daysPriorDeparture <= 30) {
			daysFactor = new BigDecimal(BOOKED_PRIOR_16_BETWEEN_30);
		} else if (daysPriorDeparture >= 3 && daysPriorDeparture <= 15) {
			daysFactor = new BigDecimal(BOOKED_PRIOR_3_TO_15_DAYS);
		} else if (daysPriorDeparture < 3) {
			daysFactor = new BigDecimal(BOOKED_PRIOR_3_DAYS);
		}

		return daysFactor.multiply(price).multiply(new BigDecimal(passengers));
	}
}
