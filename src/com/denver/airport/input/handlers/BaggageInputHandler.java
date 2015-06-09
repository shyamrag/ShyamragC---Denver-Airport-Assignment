package com.denver.airport.input.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import com.denver.airport.entities.Baggage;

public class BaggageInputHandler implements InputHandler{

	private ArrayList<String> baggageInformation;
	private ArrayList<String> baggageIdList;
	private HashMap<String,Baggage> bagIdMap;
	
	
	public BaggageInputHandler() {
		super();
		baggageInformation = new ArrayList<String>();
		baggageIdList = new ArrayList<String>();
		bagIdMap = new HashMap<String,Baggage>();
	}

	@Override
	public void addInformation(String baggageInfo)
	{
		baggageInformation.add(baggageInfo);
	}

	@Override
	public void parseInformation() {
		
		String[] bagArr;
		for(String line : baggageInformation)
		{
			bagArr = line.split(" ");
			String bagId = bagArr[0];
			String entryPoint = bagArr[1];
			String destination = bagArr[2];
			
			Baggage baggage = new Baggage(bagId,entryPoint,destination);
			bagIdMap.put(bagId,baggage);
			baggageIdList.add(bagId);
		}
	}
	
	public ArrayList<String> getBaggageList()
	{
		return baggageIdList;
	}
	
	public HashMap<String,Baggage> getBaggageMap()
	{
		return bagIdMap;
	}
}
