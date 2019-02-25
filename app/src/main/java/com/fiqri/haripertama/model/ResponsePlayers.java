package com.fiqri.haripertama.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePlayers{

	@SerializedName("player")
	private List<PlayerItem> player;

	public void setPlayer(List<PlayerItem> player){
		this.player = player;
	}

	public List<PlayerItem> getPlayer(){
		return player;
	}
}