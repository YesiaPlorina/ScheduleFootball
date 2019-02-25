package com.fiqri.haripertama.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseEvents{

	@SerializedName("events")
	private List<EventsItem> events;

	public void setEvents(List<EventsItem> events){
		this.events = events;
	}

	public List<EventsItem> getEvents(){
		return events;
	}
}