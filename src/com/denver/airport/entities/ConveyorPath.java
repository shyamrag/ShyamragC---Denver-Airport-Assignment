package com.denver.airport.entities;

public class ConveyorPath {

	String source;
	String destination;
	int travel_time;
	
	public ConveyorPath(String source, String destination, int travel_time) {
		super();
		this.source = source;
		this.destination = destination;
		this.travel_time = travel_time;
	}
	
	public int getTravel_time() {
		return travel_time;
	}
	public void setTravel_time(int travel_time) {
		this.travel_time = travel_time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
