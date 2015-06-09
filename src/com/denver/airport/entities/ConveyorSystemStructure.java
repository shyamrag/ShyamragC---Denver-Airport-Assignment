package com.denver.airport.entities;

import java.util.HashMap;

/**
*
* @author shyamrag
*/
public class ConveyorSystemStructure {

	private Integer[][] adjacentGates;
	HashMap<Integer,String> gateIdToNameMapping;
	
	public ConveyorSystemStructure(){	}
	
	public ConveyorSystemStructure(int numOfGates)
	{
		adjacentGates = new Integer[numOfGates][numOfGates];
		gateIdToNameMapping = new HashMap<Integer,String>();
		
		for(int i = 0; i < numOfGates;i++)
			for(int j = 0;j < numOfGates;j++)
			{
				adjacentGates[i][j] = Integer.MAX_VALUE;
			}
	}
	
	public Integer[][] getGateMatrix()
	{
		return adjacentGates;
	}
 	
	public void createIndexToNameMapping(int indexOfGate,String nameOfGate)
	{
		gateIdToNameMapping.put(indexOfGate, nameOfGate);
	}
	
	public void createPathBetweenGates(int indexOfGate1, int indexOfGate2, int travel_time)
	{
		if(indexOfGate1 != indexOfGate2)
		{
			adjacentGates[indexOfGate1][indexOfGate2] = travel_time;
			adjacentGates[indexOfGate2][indexOfGate1] = travel_time;
		}
	}

	public HashMap<Integer, String> getIndexToNameMapping() {

		return gateIdToNameMapping;
	}
}
