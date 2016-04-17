package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.utility.Text;

public class Robot extends BasePOJO {

	public static String TABLE_NAME = "ROBOT";
	public static String ID_NAME = "ROBOT_ID";

	public static String ROBOT_ID = "ROBOT_ID";
	public static String TEAM_ID = "TEAM_ID";
	public static String GAME_ID = "GAME_ID";
	public static String DRIVE_TRAIN_TYPE = "DRIVE_TRAIN";
	public static String DRIVE_TRAIN_MOTORS_NUMBER = "DRIVE_TRAIN_MOTORS_NUMBER";
	public static String DRIVE_TRAIN_MOTORS_TYPES = "DRIVE_TRAIN_MOTORS_TYPES";
	public static String OTHER_MOTORS_NUMBER = "OTHER_MOTORS_NUMBER";
	public static String OTHER_MOTORS_TYPES = "OTHER_MOTORS_TYPES";
	public static String WEIGHT = "WEIGHT";
	public static String PROGRAM_LANGUAGE = "PROGRAM_LANGUAGE";
	public static String AUTONOMOUS = "AUTONOMOUS";
	public static String PICTURE = "PICTURE";
	public static String NOTES = "NOTES";
	public static String WATCHING = "WATCHING";

	protected String robotId = "";
	protected String teamId = "";
	protected String gameId = "";
	protected String driveTrain = "";
	protected String driveTrainMotorsNumber = "";
	protected String driveTrainMotorsTypes = "";
	protected String otherMotorsNumber = "";
	protected String otherMotorsTypes = "";
	protected String weight = "";
	protected String programLanguage = "";
	protected String autonomous = "";
	protected String picture = "";
	protected String notes = "";
	protected String watching = "";

	// Variables not in database
	protected String year = "";
	protected String teamNumber = "";

	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(ROBOT_ID, Text.toCamel(ROBOT_ID));
		attributeMap.put(TEAM_ID, Text.toCamel(TEAM_ID));
		attributeMap.put(GAME_ID, Text.toCamel(GAME_ID));
		attributeMap.put(DRIVE_TRAIN_TYPE, Text.toCamel(DRIVE_TRAIN_TYPE));
		attributeMap.put(DRIVE_TRAIN_MOTORS_NUMBER, Text.toCamel(DRIVE_TRAIN_MOTORS_NUMBER));
		attributeMap.put(OTHER_MOTORS_NUMBER, Text.toCamel(OTHER_MOTORS_NUMBER));
		attributeMap.put(DRIVE_TRAIN_MOTORS_TYPES, Text.toCamel(DRIVE_TRAIN_MOTORS_TYPES));
		attributeMap.put(OTHER_MOTORS_TYPES, Text.toCamel(OTHER_MOTORS_TYPES));
		attributeMap.put(WEIGHT, Text.toCamel(WEIGHT));
		attributeMap.put(PROGRAM_LANGUAGE, Text.toCamel(PROGRAM_LANGUAGE));
		attributeMap.put(AUTONOMOUS, Text.toCamel(AUTONOMOUS));
		attributeMap.put(PICTURE, Text.toCamel(PICTURE));
		attributeMap.put(NOTES, Text.toCamel(NOTES));
		attributeMap.put(WATCHING, Text.toCamel(WATCHING));
	}

	public Robot() {

	}

	public Robot(Map data) {
		setDataMap(data);
	}

	@XmlTransient
	public String toString() {
		return "Robot ID: " + robotId + ", Drive Type: " + driveTrain + ", Number Of Drive Motors: "
				+ driveTrainMotorsNumber + ", Type Of Drive Motors: " + driveTrainMotorsTypes
				+ ", Number Of Other Motors: " + otherMotorsNumber + ", Type Of Other Motors: " + otherMotorsTypes
				+ ", Weight: " + weight + ", Programming Language: " + programLanguage + ", Autonomous: " + autonomous
				+ ", Picture: " + picture + ", Team Number: " + teamNumber + ", Year: " + year;
	}

	public String getRobotId() {
		return robotId;
	}

	public int getRobotIdInteger() {
		return Integer.parseInt(robotId);
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getDriveTrain() {
		return driveTrain;
	}

	public void setDriveTrain(String driveTrain) {
		this.driveTrain = driveTrain;
	}

	public String getOtherMotorsTypes() {
		return otherMotorsTypes;
	}

	public void setOtherMotorsTypes(String otherMotorsTypes) {
		this.otherMotorsTypes = otherMotorsTypes;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getProgrammingLanguage() {
		return programLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programLanguage = programmingLanguage;
	}

	public String getAutonomous() {
		return autonomous;
	}

	public void setAutonomous(String autonomous) {
		this.autonomous = autonomous;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
		return getRobotId();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	@XmlTransient
	public int getTeamNumberInteger() {
		return Integer.parseInt(teamNumber);
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAutonomousYesNo() {
		String out = null;
		if (autonomous.equals("1")) {
			out = "Yes";
		} else if (autonomous.equals("0")) {
			out = "No";
		} else {
			out = "-";
		}
		return out;
	}

	@Override
	public void setId(String id) {
		setRobotId(id);
	}

	public String getWatching() {
		return watching;
	}

	public void setWatching(String watching) {
		this.watching = watching;
	}

	public String getDriveTrainMotorsNumber() {
		return driveTrainMotorsNumber;
	}

	public void setDriveTrainMotorsNumber(String driveTrainMotorsNumber) {
		this.driveTrainMotorsNumber = driveTrainMotorsNumber;
	}

	public String getDriveTrainMotorsTypes() {
		return driveTrainMotorsTypes;
	}

	public void setDriveTrainMotorsTypes(String driveTrainMotorsTypes) {
		this.driveTrainMotorsTypes = driveTrainMotorsTypes;
	}

	public String getOtherMotorsNumber() {
		return otherMotorsNumber;
	}

	public void setOtherMotorsNumber(String otherMotorsNumber) {
		this.otherMotorsNumber = otherMotorsNumber;
	}

	public String getProgramLanguage() {
		return programLanguage;
	}

	public void setProgramLanguage(String programLanguage) {
		this.programLanguage = programLanguage;
	}
}
