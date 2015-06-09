package com.denver.airport.input.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import com.denver.airport.entities.DepartureFlights;

public class DepartureInputHandler implements InputHandler{

	private ArrayList<String> departureInformation;
	private HashMap<String,DepartureFlights> flightdepartureMap;
	
	
    public DepartureInputHandler() {
		super();
		departureInformation = new ArrayList<String>();
		flightdepartureMap = new HashMap<String,DepartureFlights>();
	}

	@Override
	public void addInformation(String departureInfo)
	{
		departureInformation.add(departureInfo);
	}

	@Override
	public void parseInformation() {
		String[] departureArr;
		for(String line : departureInformation)
		{
			departureArr = line.split(" ");
			String flightId = departureArr[0];
			String flightGate = departureArr[1];
			String destination = departureArr[2];
			String time = departureArr[3];
			
			DepartureFlights flight = new DepartureFlights(flightId,flightGate,destination,time);
			flightdepartureMap.put(flightId, flight);
		}
		
	}
	
	public HashMap<String,DepartureFlights> getDepartureFlightMap()
	{
		return flightdepartureMap;
	}
}
