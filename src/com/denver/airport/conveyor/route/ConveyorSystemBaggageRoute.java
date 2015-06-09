package com.denver.airport.conveyor.route;

import java.util.HashMap;
import java.util.Stack;

import com.denver.airport.entities.ConveyorSystemStructure;

/**
*
* @author shyamrag
*/
public class ConveyorSystemBaggageRoute {

	public static String getOptimizedPathForBaggage(ConveyorSystemStructure conveyorSystem,int source,int destination, int totalNumOfGates)
	{
		int[] travel_time_from_source = new int[totalNumOfGates];
		int[] path = new int[totalNumOfGates];
		
		for(int i=0; i < totalNumOfGates;i++)
		{
			travel_time_from_source[i] = -1;
		}
		
 		PriorityQueue pq = new PriorityQueue(totalNumOfGates);
 		Integer[][] adjacentGateMatrix = conveyorSystem.getGateMatrix();
		
		travel_time_from_source[source] = 0;
		
		pq.insert(source,travel_time_from_source[source]);
		while(!pq.isEmpty())
		{
			int minGateIndex = pq.removeMin();
			//Get all adjacent Gates for this gate
			for(int i =0 ;i < totalNumOfGates;i++)
			{
				int timeFromSource = adjacentGateMatrix[minGateIndex][i];
				if(timeFromSource != Integer.MAX_VALUE)
				{
					if(travel_time_from_source[i] == -1)
					{
						travel_time_from_source[i] = travel_time_from_source[minGateIndex] + timeFromSource;
						path[i] = minGateIndex;
						pq.insert(i, travel_time_from_source[i]);
					}
					else
					{
						int newTime = travel_time_from_source[minGateIndex] + timeFromSource;
						if(newTime < travel_time_from_source[i])
						{
							travel_time_from_source[i] = newTime;
							path[i] = minGateIndex;
							pq.update(i, travel_time_from_source[i]);
						}
					}
				}
			}
		}
		
		return getPath(path,travel_time_from_source,source,destination,conveyorSystem.getIndexToNameMapping());
	}
	
	public static String getPath(int[] path,int[] travelTime,int source,int destination,HashMap<Integer,String> indexToString)
	{
		Stack<String> stack = new Stack<String>();
		int newPathIndex = destination;
		while(source != newPathIndex)
		{
			stack.add(indexToString.get(newPathIndex) + "  ");
			newPathIndex = path[newPathIndex];
		}

		stack.add(indexToString.get(source)+ " ");
		
		StringBuffer optimizedPath = new StringBuffer();
		while(!stack.isEmpty())
		{
			optimizedPath.append(stack.pop());
		}
		optimizedPath.append(": "+travelTime[destination]);
		return optimizedPath.toString();
	}
}
