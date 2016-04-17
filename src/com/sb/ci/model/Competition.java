package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.utility.Text;
import com.sb.database.Find;
import com.sb.database.Query;

public class Competition extends BasePOJO {
	
	public static String TABLE_NAME = "COMPETITION";
	public static String ID_NAME = "COMPETITION_ID";
	
	public static String COMPETITION_ID = "COMPETITION_ID";
	public static String GAME_ID = "GAME_ID";
	public static String NAME = "NAME";
	public static String CITY = "CITY";
	public static String STATE = "STATE";
	public static String CURRENT = "CURRENT";
	public static String COMPETITION_ORDER = "COMPETITION_ORDER";
	public static String URL = "URL";
	public static String CURRENT_ROUND = "CURRENT_ROUND";

	protected String competitionId = "";
	protected String gameId = "";
	protected String name = "";
	protected String city = "";
	protected String state = "";
	protected String current = "";
	protected String competitionOrder = "";
	protected String url = "";
	protected String currentRound = "";
	
	
	//Strings not within the db table go below
	protected String year = "";

	private static Map attributeMap = new HashMap();
	
	static {
		attributeMap.put(COMPETITION_ID, Text.toCamel(COMPETITION_ID));
		attributeMap.put(GAME_ID, Text.toCamel(GAME_ID));
		attributeMap.put(NAME, Text.toCamel(NAME));
		attributeMap.put(CITY, Text.toCamel(CITY));
		attributeMap.put(STATE, Text.toCamel(STATE));
		attributeMap.put(CURRENT, Text.toCamel(CURRENT));
		attributeMap.put(COMPETITION_ORDER, Text.toCamel(COMPETITION_ORDER));
		attributeMap.put(URL, Text.toCamel(URL));
		attributeMap.put(CURRENT_ROUND, Text.toCamel(CURRENT_ROUND));
	}

	public Competition() {
		
	}

	public Competition(Map data) {
		setDataMap(data);
	}
	
	public String toString() {
		return "Competition_ID: " + competitionId + ", Game_Id: " + gameId
				+ ", Competition Name: " + name + ", State: " + state
				+ ", City: " + city;
	}

	public String getCompetitionId() {
		return competitionId;
	}

	public int getCompetitionIdInteger() {
		return Integer.parseInt(competitionId);
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlTransient
	public String getTableName() {
		return TABLE_NAME;
	}

	@XmlTransient
	public String getIDName() {
		return ID_NAME;
	}

	@XmlTransient
	public Map getAtributeMap() {
		return attributeMap;
	}

	@XmlTransient
	public String getId() {
		return competitionId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getCompetitionOrder() {
		return competitionOrder;
	}

	public void setCompetitionOrder(String competitionOrder) {
		this.competitionOrder = competitionOrder;
	}

	@Override
	public void setId(String id) {
		setCompetitionId(id);
	}

	public void setYear() {
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(String currentRound) {
		this.currentRound = currentRound;
	}
}
