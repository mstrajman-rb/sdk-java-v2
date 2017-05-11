package com.decidir.sdk.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by ivalek on 11/06/17.
 */
public class DecisionManagerTravel implements Serializable {

	private String complete_route;
	private JourneyType journey_type;
	private Date departure_date_time;
	
	public String getComplete_route() {
		return complete_route;
	}
	public void setComplete_route(String complete_route) {
		this.complete_route = complete_route;
	}
	public JourneyType getJourney_type() {
		return journey_type;
	}
	public void setJourney_type(JourneyType journey_type) {
		this.journey_type = journey_type;
	}
	public Date getDeparture_date_time() {
		return departure_date_time;
	}
	public void setDeparture_date_time(Date departure_date_time) {
		this.departure_date_time = departure_date_time;
	}
	
}
