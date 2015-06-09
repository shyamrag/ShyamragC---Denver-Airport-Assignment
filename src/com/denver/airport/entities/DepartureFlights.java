package com.denver.airport.entities;


/**
*
* @author shyamrag
*/
public class DepartureFlights {
	
	private String flight_id;
	private String gate_no;
	private String destination;
	private String departureTime;
	
	public DepartureFlights(String flight_id, String gate_no, String destination,
			String departureTime) {
		super();
		this.flight_id = flight_id;
		this.gate_no = gate_no;
		this.destination = destination;
		this.departureTime = departureTime;
	}
	
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public String getGate_no() {
		return gate_no;
	}
	public void setGate_no(String gate_no) {
		this.gate_no = gate_no;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nFlight Id="+flight_id);
		buffer.append("\nGate No="+gate_no);
		buffer.append("\nDestination="+destination);
		buffer.append("\nDeparture Time="+departureTime);
		
		return buffer.toString();
	}

}
