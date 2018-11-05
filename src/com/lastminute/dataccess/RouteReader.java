package com.lastminute.dataccess;

import java.util.List;

import com.lastminute.model.Flight;

public interface RouteReader {
	public List<Flight> loadFlights(String origin, String destination);

}
