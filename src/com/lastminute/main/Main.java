package com.lastminute.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.lastminute.exception.LastMinuteException;
import com.lastminute.exception.NotFoundException;
import com.lastminute.model.Route;
import com.lastminute.services.FlightSearchingSystem;

public class Main {
	public static void main(String[] args) throws ParseException, IOException, LastMinuteException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Airport of origin: ");
		String origin = br.readLine();
		System.out.print("Airport of destination: ");
		String destination = br.readLine();
		System.out.print("Date of departure (MM/dd/yyyy): ");
		String departureDateString = br.readLine();
		System.out.print("Number of passengers: ");
		int passengers = Integer.parseInt(br.readLine());
		Route route = new Route(origin, destination);
		FlightSearchingSystem fss = new FlightSearchingSystem(route);
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			Date departureDate = sdf.parse(departureDateString);
			fss.searchFlights(passengers, departureDate);
		}
		catch (LastMinuteException ex)
		{
			System.out.println(ex);
		}

		try
		{
			fss.getFlightResult();
		}
		catch (NotFoundException exc)
		{
			System.out.println(exc);
		}

		System.out.println(fss);
	}
}
