package com.denver.airport.conveyor.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.denver.airport.conveyor.route.ConveyorSystemBaggageRoute;
import com.denver.airport.entities.Baggage;
import com.denver.airport.entities.Constants;
import com.denver.airport.entities.ConveyorPath;
import com.denver.airport.entities.ConveyorSystemStructure;
import com.denver.airport.entities.DepartureFlights;
import com.denver.airport.input.handlers.BaggageInputHandler;
import com.denver.airport.input.handlers.ConveyorInputHandler;
import com.denver.airport.input.handlers.DepartureInputHandler;
import com.denver.airport.input.handlers.InputHandler;

/**
*
* @author shyamrag
*/
public class ConveyorSystem {

	private static ArrayList<InputHandler> handlers = new ArrayList<InputHandler>();
	private static ArrayList<String> baggageList;
	private static ArrayList<ConveyorPath> listOfConveyorPath;
	private static HashMap<String,DepartureFlights> departureFlightsMap;
	private static HashMap<String,Baggage> baggageIdMap;
	private static HashSet<String> listOfGate;
	private static HashMap<String,Integer> gateNameToIndexMapping;
	
	public static String inputPath = "Input/Input.txt";
	public static String outputPath = "Output/Output.txt";
	public static String outFileName = "Output.txt";
	
	public static void main(String[] args)
	{
	
		File file = new File(inputPath);
		if(!file.exists())
		{
			System.out.println("File doesn't exist, Please provide correct path to file");
			System.exit(0);
		}
		
		readInputFile(file);
		parseData();
		findShortestPath();
		
	}

	private static void findShortestPath() {
		
		ConveyorSystemStructure conveyorSystemStructure = new ConveyorSystemStructure(listOfGate.size());
		
		gateNameToIndexMapping= new HashMap<String,Integer>();
		int count = 0;
		//Create mapping for Gate name to its index
		for(String gateName : listOfGate)
		{
			gateNameToIndexMapping.put(gateName, count++);
		}
		
		for(ConveyorPath path :listOfConveyorPath)
		{
			Integer srcIndex = gateNameToIndexMapping.get(path.getSource());
			Integer destIndex = gateNameToIndexMapping.get(path.getDestination());
			
			conveyorSystemStructure.createIndexToNameMapping(srcIndex, path.getSource());
			conveyorSystemStructure.createIndexToNameMapping(destIndex, path.getDestination());
			conveyorSystemStructure.createPathBetweenGates(srcIndex, destIndex, path.getTravel_time());
		}
		

		BufferedWriter output = null;
		try {
			File file = new File(outputPath);
			file.createNewFile();
            output = new BufferedWriter(new FileWriter(file)); 

			for(String bagId : baggageList)
		{
			Baggage baggage = baggageIdMap.get(bagId);
			String entryPoint = baggage.getFlightId();
			
			DepartureFlights departureFlight = departureFlightsMap.get(entryPoint);
			
			Integer srcIndex = gateNameToIndexMapping.get(baggage.getEntryPoint());
			Integer destIndex;
			if(entryPoint.equals(Constants.ARRIVAL))
			{
				destIndex = gateNameToIndexMapping.get(Constants.BAGGAGE_CLAIM);
			}
			else
			{
				destIndex = gateNameToIndexMapping.get(departureFlight.getGate_no());
			}
			
			String path  = ConveyorSystemBaggageRoute.getOptimizedPathForBaggage(conveyorSystemStructure,srcIndex, 
					destIndex, listOfGate.size());
			
			System.out.println(bagId+" "+ path);
			output.write(bagId+" "+path +"\n");
		}
	      } catch (IOException e) {
				System.out.println("Error in Creating or writing output file.");
			}
		finally{
			 if ( output != null )
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private static void parseData() {
		
		for(InputHandler handler : handlers)
		{
			handler.parseInformation();	
			if(handler instanceof BaggageInputHandler)
			{
				baggageList = ((BaggageInputHandler)handler).getBaggageList();
				baggageIdMap = ((BaggageInputHandler)handler).getBaggageMap();
			}
			else if(handler instanceof DepartureInputHandler)
			{
				departureFlightsMap = ((DepartureInputHandler)handler).getDepartureFlightMap();
			}
			else if(handler instanceof ConveyorInputHandler)
			{
				listOfGate = ((ConveyorInputHandler)handler).getListOfGates();
				listOfConveyorPath = ((ConveyorInputHandler)handler).getListOfconveyorPath();
			}
		}
	}

	private static void readInputFile(File file) {
		BufferedReader br = null;
		InputHandler handler = null;
		try {
			
			br = new BufferedReader(new FileReader(file));
			String sCurrentLine = br.readLine();
			while (sCurrentLine != null)
			{
				while(sCurrentLine.isEmpty() || sCurrentLine.trim().equals("") || sCurrentLine.trim().equals("\n"))
				{
					sCurrentLine = br.readLine();
			    }
			    
				if(sCurrentLine.startsWith(Constants.SECTION))
				{
					if(sCurrentLine.endsWith(Constants.CONVEYOR_SYSTEM))
					{
						handler = new ConveyorInputHandler();
						handlers.add(handler);
						while(!(sCurrentLine = br.readLine()).startsWith(Constants.SECTION))
						{
							if(sCurrentLine.isEmpty() || sCurrentLine.trim().equals("") || sCurrentLine.trim().equals("\n"))
							{
								continue;
						    }
							handler.addInformation(sCurrentLine);
						}
					}
					else if(sCurrentLine.endsWith(Constants.DEPARTURES))
					{
						handler = new DepartureInputHandler();
						handlers.add(handler);
						while(!(sCurrentLine = br.readLine()).startsWith(Constants.SECTION))
						{
							if(sCurrentLine.isEmpty() || sCurrentLine.trim().equals("") || sCurrentLine.trim().equals("\n"))
							{
								continue;
						    }
							handler.addInformation(sCurrentLine);
						}
					}		
					else if(sCurrentLine.endsWith(Constants.BAGS))
					{
						handler = new BaggageInputHandler();
						handlers.add(handler);
						while((sCurrentLine = br.readLine()) != null)
						{
							if(sCurrentLine.isEmpty() || sCurrentLine.trim().equals("") || sCurrentLine.trim().equals("\n"))
							{
								continue;
						    }
							handler.addInformation(sCurrentLine);
						}
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error in reading the file.");
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				
			}
		}
		
	}
}
