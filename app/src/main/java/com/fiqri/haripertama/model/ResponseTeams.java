package com.fiqri.haripertama.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTeams{

	@SerializedName("teams")
	private List<TeamsItem> teams;

	public void setTeams(List<TeamsItem> teams){
		this.teams = teams;
	}

	public List<TeamsItem> getTeams(){
		return teams;
	}
}