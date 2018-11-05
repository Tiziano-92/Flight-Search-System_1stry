package com.lastminute.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.lastminute.exception.LastMinuteException;
import com.lastminute.exception.NotFoundException;
import com.lastminute.model.Route;
import com.lastminute.services.FlightSearchingSystem;

/**
 * Test class with Junit test for the searching flight system
 * @author Tiziano
 *
 */
public class TicketPriceRoutesTest {

	@Test
	public void getRoutesAmsToFra() throws LastMinuteException
	{
		Route route = new Route("AMS", "FRA");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
        fss.searchFlights(1, getDepartureDate(31));

        Map<String, BigDecimal> expectedResult = new LinkedHashMap<>();

        expectedResult.put("TK2372", new BigDecimal("157.6"));
        expectedResult.put("TK2659", new BigDecimal("198.4"));
        expectedResult.put("LH5909", new BigDecimal("90.4"));
	}

	@Test
	public void getRoutesLhrToIst() throws LastMinuteException
	{
		Route route = new Route("LHR", "IST");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		fss.searchFlights(3, getDepartureDate(14));

		Map<String, BigDecimal> expectedResult = new LinkedHashMap<>();
		expectedResult.put("TK8891", new BigDecimal("900.0"));
		expectedResult.put("LH1085", new BigDecimal("532.8"));

		System.out.println(fss.getFlightResult());

		assertEquals(fss.getFlightResult(),expectedResult);
	}

	@Test
	public void getRoutesBcnToMad() throws LastMinuteException
	{
		Route route = new Route("BCN", "MAD");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		fss.searchFlights(2, getDepartureDate(1));

		Map<String, BigDecimal> expectedResult = new LinkedHashMap<>();
		expectedResult.put("IB2171", new BigDecimal("777.0"));
		expectedResult.put("LH5496", new BigDecimal("879.0"));

		assertEquals(fss.getFlightResult(),expectedResult);
	}

	@Test(expected = NotFoundException.class)
	public void getRoutesCdgToFra() throws NotFoundException, LastMinuteException
	{
		Route route = new Route("CDG", "FRA");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		fss.getFlightResult();
	}

	@Test(expected = LastMinuteException.class)
	public void getRouteWithNegativePassengers() throws LastMinuteException
	{
		Route route = new Route("BCN", "MAD");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		fss.searchFlights(-2, getDepartureDate(1));
	}

	@Test(expected = LastMinuteException.class)
	public void getRouteWithEmptyOrigin() throws LastMinuteException
	{
		Route route = new Route("","MAD");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		fss.searchFlights(2, getDepartureDate(1));
	}

	@Test(expected = LastMinuteException.class)
	public void getRouteWithEmptyDestination() throws LastMinuteException
	{
		Route route = new Route("BCN","");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		fss.searchFlights(2, getDepartureDate(1));
	}

	@Test(expected = LastMinuteException.class)
	public void getRoutesWithFlightAheadTheCurrentDate() throws LastMinuteException
	{
		Route route = new Route("BCN", "MAD");
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		fss.searchFlights(2, getDepartureDate(-2));
	}

	/**
	 * Get the departure date set based on the days provided
	 * @param dayOfDeparture
	 * @return
	 */
	public Date getDepartureDate (int dayOfDeparture) {
		Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, (dayOfDeparture + 1));
        return c.getTime();
    }
}
