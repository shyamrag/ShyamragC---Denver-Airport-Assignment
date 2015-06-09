package com.denver.airport.input.handlers;

import java.util.ArrayList;
import java.util.HashSet;

import com.denver.airport.entities.ConveyorPath;

public class ConveyorInputHandler implements InputHandler{

	private ArrayList<String> conveyorInformation;
	private HashSet<String> listOfGate;
	private ArrayList<ConveyorPath> listOfConveyorPath;
	
	public ConveyorInputHandler() {
		super();
		conveyorInformation = new ArrayList<String>();
		listOfGate = new HashSet<String>();
		listOfConveyorPath = new ArrayList<ConveyorPath>();
	}

	@Override
	public void addInformation(String conveyorInfo)
	{
		conveyorInformation.add(conveyorInfo);
	}

	@Override
	public void parseInformation() {
		String[] conveyorArr;
		
		for(String line : conveyorInformation)
		{
			conveyorArr = line.split(" ");
			String source = conveyorArr[0];
			String destination = conveyorArr[1];
			int time = Integer.parseInt(conveyorArr[2]);
			
			listOfGate.add(source);
			listOfGate.add(destination);
			
			ConveyorPath conveyorPath = new ConveyorPath(source,destination,time);
			listOfConveyorPath.add(conveyorPath);
		}		
	}
	
	public HashSet<String> getListOfGates()
	{
		return listOfGate;
	}
	
	public ArrayList<ConveyorPath> getListOfconveyorPath()
	{
		return listOfConveyorPath;
	}
}
