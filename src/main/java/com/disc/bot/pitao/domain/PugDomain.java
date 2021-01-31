package com.disc.bot.pitao.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.api.entities.Member;

public class PugDomain {
	
	private String pugId;
	
	private Member captain1;
	
	private Member captain2;
	
	private List<Member> team1 = new ArrayList<Member>();
	
	private List<Member> team2 = new ArrayList<Member>();
	
	private List<String> maps = new ArrayList<String>();
	
	private List<Member> players = new ArrayList<Member>();
	
	private boolean isStarted = false;
	
	private HashMap<Integer, Member> pickOrder = new HashMap<Integer, Member>();
	
	private Integer currentPick = 1;

	public String getPugId() {
		return pugId;
	}

	public void setPugId(String pugId) {
		this.pugId = pugId;
	}

	public List<String> getMaps() {
		return maps;
	}

	public void setMaps(List<String> maps) {
		this.maps = maps;
	}

	public List<Member> getPlayers() {
		return players;
	}

	public void setPlayers(List<Member> players) {
		this.players = players;
	}

	public Member getCaptain1() {
		return captain1;
	}

	public void setCaptain1(Member captain1) {
		this.captain1 = captain1;
	}

	public Member getCaptain2() {
		return captain2;
	}

	public void setCaptain2(Member captain2) {
		this.captain2 = captain2;
	}

	public List<Member> getTeam1() {
		return team1;
	}

	public void setTeam1(List<Member> team1) {
		this.team1 = team1;
	}

	public List<Member> getTeam2() {
		return team2;
	}

	public void setTeam2(List<Member> team2) {
		this.team2 = team2;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public HashMap<Integer, Member> getPickOrder() {
		return pickOrder;
	}

	public void setPickOrder(HashMap<Integer, Member> pickOrder) {
		this.pickOrder = pickOrder;
	}

	public Integer getCurrentPick() {
		return currentPick;
	}

	public void setCurrentPick(Integer currentPick) {
		this.currentPick = currentPick;
	}
	
}