package com.lastminute.dataccess;

import static com.lastminute.dataccess.CsvFiles.readAllRecords;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lastminute.model.Flight;

public class RouteCsvReader implements RouteReader {

	/**
	 * Load from the csv file the flights available for the particular route
	 * @param origin
	 * @param destination
	 * @return
	 */
	public List<Flight> loadFlights(String origin, String destination)
	{
		List<Flight> flights = new ArrayList<Flight>();

		List<List<String>> allRoutes = readAllRecords("resources/flight-routes.csv");
		List<List<String>> allPrices = readAllRecords("resources/flight-prices.csv");

		for (int i = 0; i < allRoutes.size(); i++) {
			if(allRoutes.get(i).get(0).contains(origin) && allRoutes.get(i).get(1).contains(destination))
			{
				String routeCode = allRoutes.get(i).get(2);

				for (int k = 0; k < allPrices.size(); k++) {
					if(allPrices.get(k).get(0).equals(routeCode))
					{
						BigDecimal routePrice = new BigDecimal (allPrices.get(k).get(1));
						Flight fl = new Flight(routeCode, routePrice);
						flights.add(fl);
					}
				}
			}
		}
		return flights;
	}


}
