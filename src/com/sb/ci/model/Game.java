package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.utility.Text;

public class Game extends BasePOJO {

	public static String TABLE_NAME = "GAME";

	public static String GAME_ID = "GAME_ID";
	public static String YEAR = "YEAR";
	public static String NAME = "NAME";
	public static String CHAMPIONSHIP_LOCATION = "CHAMPIONSHIP_LOCATION";
	public static String NOTES = "NOTES";

	protected String gameId = "";
	protected String year = "";
	protected String notes = "";
	protected String name = "";
	protected String championshipLocation = "";
	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(GAME_ID, Text.toCamel(GAME_ID));
		attributeMap.put(NAME, Text.toCamel(NAME));
		attributeMap.put(YEAR, Text.toCamel(YEAR));
		attributeMap.put(CHAMPIONSHIP_LOCATION, Text.toCamel(CHAMPIONSHIP_LOCATION));
		attributeMap.put(NOTES, Text.toCamel(NOTES));
	}

	public String getGameId() {
		return gameId;
	}

	public int getGameIdInteger() {
		return Integer.parseInt(gameId);
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChampionshipLocation() {
		return championshipLocation;
	}

	public void setChampionshipLocation(String championshipLocation) {
		this.championshipLocation = championshipLocation;
	}

	public Game() {

	}

	public Game(Map data) {
		setDataMap(data);
	}

	@XmlTransient
	public Map getAtributeMap() {
		return attributeMap;
	}

	@XmlTransient
	public String getTableName() {
		return TABLE_NAME;
	}

	@XmlTransient
	public String getIDName() {
		return GAME_ID;
	}

	@XmlTransient
	public String getId() {
		return gameId;
	}

	@Override
	public void setId(String id) {
		setGameId(id);
	}
}
