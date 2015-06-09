package com.denver.airport.entities;

/**
 *
 * @author shyamrag
 */
public class Baggage {
	
	private String bagNumber;
	private String entryPoint;
	private String flightId;
	
	public Baggage(String bagNumber, String entryPoint, String flightId) {
		super();
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}
	
	public String getBagNumber() {
		return bagNumber;
	}
	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}
	public String getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n Bag Number ="+bagNumber);
		buffer.append("\n Source = "+entryPoint);
		buffer.append("\n Flight Id/Baggage Claim ="+ (flightId.equalsIgnoreCase("ARRIVAL")?"ARRIVAL":flightId));
		return buffer.toString();
	}

}
