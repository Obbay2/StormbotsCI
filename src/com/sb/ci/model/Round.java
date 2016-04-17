package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.model.Competition;
import com.sb.ci.model.Team;
import com.sb.ci.utility.Text;
import com.sb.database.Find;

public class Round extends BasePOJO {

	public static String ID_NAME = "ROUND_ID";
	public static String TABLE_NAME = "ROUND";
	public static String ROUND_ID = "ROUND_ID";
	public static String NUMBER = "NUMBER";
	public static String RED_1_ID = "RED_1_ID";
	public static String RED_2_ID = "RED_2_ID";
	public static String RED_3_ID = "RED_3_ID";
	public static String BLUE_1_ID = "BLUE_1_ID";
	public static String BLUE_2_ID = "BLUE_2_ID";
	public static String BLUE_3_ID = "BLUE_3_ID";
	public static String SCORE_BLUE = "SCORE_BLUE";
	public static String SCORE_RED = "SCORE_RED";
	public static String COMPETITION_ID = "COMPETITION_ID";
	public static String NOTES = "NOTES";
	public static String RED_1_NUMBER = "RED_1_NUMBER";
	public static String RED_2_NUMBER = "RED_2_NUMBER";
	public static String RED_3_NUMBER = "RED_3_NUMBER";
	public static String BLUE_1_NUMBER = "BLUE_1_NUMBER";
	public static String BLUE_2_NUMBER = "BLUE_2_NUMBER";
	public static String BLUE_3_NUMBER = "BLUE_3_NUMBER";

	protected String roundId = "";
	protected String number = "";
	protected String red1Id = "";
	protected String red2Id = "";
	protected String red3Id = "";
	protected String blue1Id = "";
	protected String blue2Id = "";
	protected String blue3Id = "";
	protected String scoreRed = "";
	protected String scoreBlue = "";
	protected String competitionId = "";
	protected String notes = "";
	protected String red1Number = "";
	protected String red2Number = "";
	protected String red3Number = "";
	protected String blue1Number = "";
	protected String blue2Number = "";
	protected String blue3Number = "";

	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(ROUND_ID, Text.toCamel(ROUND_ID));
		attributeMap.put(NUMBER, Text.toCamel(NUMBER));
		attributeMap.put(RED_1_ID, Text.toCamel(RED_1_ID));
		attributeMap.put(RED_2_ID, Text.toCamel(RED_2_ID));
		attributeMap.put(RED_3_ID, Text.toCamel(RED_3_ID));
		attributeMap.put(BLUE_1_ID, Text.toCamel(BLUE_1_ID));
		attributeMap.put(BLUE_2_ID, Text.toCamel(BLUE_2_ID));
		attributeMap.put(BLUE_3_ID, Text.toCamel(BLUE_3_ID));
		attributeMap.put(SCORE_RED, Text.toCamel(SCORE_RED));
		attributeMap.put(SCORE_BLUE, Text.toCamel(SCORE_BLUE));
		attributeMap.put(COMPETITION_ID, Text.toCamel(COMPETITION_ID));
		attributeMap.put(NOTES, Text.toCamel(NOTES));
		
		attributeMap.put(RED_1_NUMBER, Text.toCamel(RED_1_NUMBER));
		attributeMap.put(RED_2_NUMBER, Text.toCamel(RED_2_NUMBER));
		attributeMap.put(RED_3_NUMBER, Text.toCamel(RED_3_NUMBER));
		attributeMap.put(BLUE_1_NUMBER, Text.toCamel(BLUE_1_NUMBER));
		attributeMap.put(BLUE_2_NUMBER, Text.toCamel(BLUE_2_NUMBER));
		attributeMap.put(BLUE_3_NUMBER, Text.toCamel(BLUE_3_NUMBER));
		

	}

	public Round() {

	}

	public String getRoundId() {
		return roundId;
	}

	public int getRoundIdInteger() {
		return Integer.parseInt(roundId);
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRed1Id() {
		return red1Id;
	}

	public void setRed1Id(String red1Id) {
		this.red1Id = red1Id;
	}

	public String getRed2Id() {
		return red2Id;
	}

	public void setRed2Id(String red2Id) {
		this.red2Id = red2Id;
	}

	public String getRed3Id() {
		return red3Id;
	}

	public void setRed3Id(String red3Id) {
		this.red3Id = red3Id;
	}

	public String getBlue1Id() {
		return blue1Id;
	}

	public void setBlue1Id(String blue1Id) {
		this.blue1Id = blue1Id;
	}

	public String getBlue2Id() {
		return blue2Id;
	}

	public void setBlue2Id(String blue2Id) {
		this.blue2Id = blue2Id;
	}

	public String getBlue3Id() {
		return blue3Id;
	}

	public void setBlue3Id(String blue3Id) {
		this.blue3Id = blue3Id;
	}

	public String getScoreRed() {
		return scoreRed;
	}

	public void setScoreRed(String scoreRed) {
		this.scoreRed = scoreRed;
	}

	public String getScoreBlue() {
		return scoreBlue;
	}

	public void setScoreBlue(String scoreBlue) {
		this.scoreBlue = scoreBlue;
	}

	public String getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Round(Map data) {
		setDataMap(data);
	}

	@XmlTransient
	public Map getAtributeMap() {
		return attributeMap;
	}

	@XmlTransient
	public String getId() {
		return roundId;
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
	public String[] getData() {
		// POJOFactory pojo = new POJOFactory();
		// String[] data = { roundId, competitionId, number, red1Id, red2Id,
		// red3Id, blue1Id, blue2Id, blue3Id, scoreBlue,
		// scoreRed, notes };
		// if (red1Id != null && !red1Id.equals("")) {
		// Team team = pojo.getTeamById(red1Id);
		// if (team != null) {
		// data[3] = team.getTeamNumber();
		// red1Number = team.getTeamNumber();
		// }
		// }
		//
		// if (red2Id != null && !red2Id.equals("")) {
		// Team team = pojo.getTeamById(red2Id);
		// if (team != null) {
		// data[4] = team.getTeamNumber();
		// red2Number = team.getTeamNumber();
		// }
		// }
		//
		// if (red3Id != null && !red3Id.equals("")) {
		// Team team = pojo.getTeamById(red3Id);
		// if (team != null) {
		// data[5] = team.getTeamNumber();
		// red3Number = team.getTeamNumber();
		// }
		// }
		//
		// if (blue1Id != null && !blue1Id.equals("")) {
		// Team team = pojo.getTeamById(blue1Id);
		// if (team != null) {
		// data[6] = team.getTeamNumber();
		// blue1Number = team.getTeamNumber();
		// }
		// }
		//
		// if (blue2Id != null && !blue2Id.equals("")) {
		// Team team = pojo.getTeamById(blue2Id);
		// if (team != null) {
		// data[7] = team.getTeamNumber();
		// blue2Number = team.getTeamNumber();
		// }
		// }
		//
		// if (blue3Id != null && !blue3Id.equals("")) {
		// Team team = pojo.getTeamById(blue3Id);
		// if (team != null) {
		// data[8] = team.getTeamNumber();
		// blue3Number = team.getTeamNumber();
		// }
		// }
		return null;

	}

	public String getRed1Number() {
		return red1Number;
	}

	public void setRed1Number(String red1Number) {
		this.red1Number = red1Number;
	}

	public String getRed2Number() {
		return red2Number;
	}

	public void setRed2Number(String red2Number) {
		this.red2Number = red2Number;
	}

	public String getRed3Number() {
		return red3Number;
	}

	public void setRed3Number(String red3Number) {
		this.red3Number = red3Number;
	}

	public String getBlue1Number() {
		return blue1Number;
	}

	public void setBlue1Number(String blue1Number) {
		this.blue1Number = blue1Number;
	}

	public String getBlue2Number() {
		return blue2Number;
	}

	public void setBlue2Number(String blue2Number) {
		this.blue2Number = blue2Number;
	}

	public String getBlue3Number() {
		return blue3Number;
	}

	public void setBlue3Number(String blue3Number) {
		this.blue3Number = blue3Number;
	}

	@Override
	public void setId(String id) {
		setRoundId(id);
	}
}