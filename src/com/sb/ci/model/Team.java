package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.utility.Text;

@XmlRootElement
public class Team extends BasePOJO {

	public static String TABLE_NAME = "TEAM";

	public static String TEAM_ID = "TEAM_ID";
	public static String TEAM_NUMBER = "TEAM_NUMBER";
	public static String TEAM_NAME = "TEAM_NAME";
	public static String SCHOOL = "SCHOOL";
	public static String CITY = "CITY";
	public static String STATE = "STATE";
	public static String LEADER = "LEADER";
	public static String YEAR_FOUNDED = "YEAR_FOUNDED";
	public static String NOTES = "NOTES";

	protected String teamId = "";
	protected String teamNumber = "";
	protected String teamName = "";
	protected String school = "";
	protected String city = "";
	protected String state = "";
	protected String leader = "";
	protected String yearFounded = "";
	protected String notes = "";

	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(TEAM_ID, Text.toCamel(TEAM_ID));
		attributeMap.put(TEAM_NUMBER, Text.toCamel(TEAM_NUMBER));
		attributeMap.put(TEAM_NAME, Text.toCamel(TEAM_NAME));
		attributeMap.put(SCHOOL, Text.toCamel(SCHOOL));
		attributeMap.put(CITY, Text.toCamel(CITY));
		attributeMap.put(STATE, Text.toCamel(STATE));
		attributeMap.put(LEADER, Text.toCamel(LEADER));
		attributeMap.put(YEAR_FOUNDED, Text.toCamel(YEAR_FOUNDED));
		attributeMap.put(NOTES, Text.toCamel(NOTES));
	}

	public String toString() {
		return "TEAM_ID: " + teamId + ", TEAM_NUMBER: " + teamNumber + ", TEAM_NAME: " + teamName + ", SCHOOL: "
				+ school + ", CITY: " + city + ", STATE: " + state + ", YEAR_FOUNDED: " + yearFounded + ", NOTES: "
				+ notes;
	}

	@XmlTransient
	public String[] getData() {
		String[] data = { teamId, teamNumber, teamName, school, city, state, leader, yearFounded, notes };
		return data;
	}

	public Team() {
	}

	public Team(Map data) {
		setDataMap(data);
	}

	@XmlTransient
	public String getTableName() {
		return TABLE_NAME;
	}

	@XmlTransient
	public Map getAtributeMap() {
		return attributeMap;
	}

	@XmlTransient
	public String getId() {
		return getTeamId();
	}

	@XmlTransient
	public String getIDName() {
		return TEAM_ID;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public int getTeamNumberInteger() {
		return Integer.parseInt(teamNumber);
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
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

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(String yearFounded) {
		this.yearFounded = yearFounded;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setId(String id) {
		setTeamId(id);
	}
}
