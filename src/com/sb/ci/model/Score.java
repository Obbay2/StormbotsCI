package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.utility.Text;

public class Score extends BasePOJO {

	public static String TABLE_NAME = "SCORE";
	public static String SCORE_ID = "SCORE_ID";

	public static String ROUND_ID = "ROUND_ID";
	public static String TEAM_ID = "TEAM_ID";
	public static String STAGE = "STAGE";
	public static String POS_X = "POS_X";
	public static String POS_Y = "POS_Y";

	public static String YELLOW_ZONE_PLACEMENT = "YELLOW_ZONE_PLACEMENT";
	public static String ROBOT_PLACEMENT = "ROBOT_PLACEMENT";

	public static String PICK_UP_YELLOW = "PICK_UP_YELLOW";
	public static String FAIL_YELLOW = "FAIL_YELLOW";
	public static String STACK_YELLOW = "STACK_YELLOW";
	public static String PICK_UP_CONTAINER = "PICK_UP_CONTAINER";
	public static String FAIL_CONTAINER = "FAIL_CONTAINER";
	public static String STACK_CONTAINER = "STACK_CONTAINER";
	public static String AUTO_ZONE = "AUTO_ZONE";

	public static String PICK_UP_GRAY = "PICK_UP_GRAY";
	public static String FAIL_GRAY = "FAIL_GRAY";
	public static String STACK_GRAY = "STACK_GRAY";
	public static String PICK_UP_LITTER = "PICK_UP_LITTER";
	public static String FAIL_LITTER = "FAIL_LITTER";
	public static String SCORE_LITTER = "SCORE_LITTER";

	public static String KNOCK_TOTE_ROBOT = "KNOCK_TOTE_ROBOT";
	public static String KNOCK_LITTER_ROBOT = "KNOCK_LITTER_ROBOT";
	public static String KNOCK_CONTAINER_ROBOT = "KNOCK_CONTAINER_ROBOT";
	public static String KNOCK_TOTE_STACK = "KNOCK_TOTE_STACK";
	public static String KNOCK_LITTER_STACK = "KNOCK_LITTER_STACK";
	public static String KNOCK_CONTAINER_STACK = "KNOCK_CONTAINER_STACK";
	public static String STUCK = "STUCK";
	public static String FOUL = "FOUL";
	public static String YELLOW_CARD = "YELLOW_CARD";
	public static String RED_CARD = "RED_CARD";

	public static String STAR = "STAR";

	public static String TEAM_NUMBER = "TEAM_NUMBER";

	protected String scoreId = "";
	protected String roundId = "";
	protected String teamId = "";
	protected String stage = "";
	protected String posX = "";
	protected String posY = "";
	protected String yellowZonePlacement = "";
	protected String robotPlacement = "";
	protected String pickUpYellow = "";
	protected String failYellow = "";
	protected String stackYellow = "";
	protected String pickUpContainer = "";
	protected String failContainer = "";
	protected String stackContainer = "";
	protected String autoZone = "";
	protected String pickUpGray = "";
	protected String failGray = "";
	protected String stackGray = "";
	protected String pickUpLitter = "";
	protected String failLitter = "";
	protected String scoreLitter = "";
	protected String knockToteRobot = "";
	protected String knockLitterRobot = "";
	protected String knockContainerRobot = "";
	protected String knockToteStack = "";
	protected String knockLitterStack = "";
	protected String knockContainerStack = "";
	protected String stuck = "";
	protected String foul = "";
	protected String yellowCard = "";
	protected String redCard = "";
	protected String star = "";

	protected String teamNumber = "";

	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(TEAM_NUMBER, Text.toCamel(TEAM_NUMBER));

		attributeMap.put(SCORE_ID, Text.toCamel(SCORE_ID));
		attributeMap.put(ROUND_ID, Text.toCamel(ROUND_ID));
		attributeMap.put(TEAM_ID, Text.toCamel(TEAM_ID));
		attributeMap.put(STAGE, Text.toCamel(STAGE));
		attributeMap.put(POS_X, Text.toCamel(POS_X));
		attributeMap.put(POS_Y, Text.toCamel(POS_Y));
		attributeMap.put(YELLOW_ZONE_PLACEMENT, Text.toCamel(YELLOW_ZONE_PLACEMENT));
		attributeMap.put(ROBOT_PLACEMENT, Text.toCamel(ROBOT_PLACEMENT));
		attributeMap.put(PICK_UP_YELLOW, Text.toCamel(PICK_UP_YELLOW));
		attributeMap.put(FAIL_YELLOW, Text.toCamel(FAIL_YELLOW));
		attributeMap.put(STACK_YELLOW, Text.toCamel(STACK_YELLOW));
		attributeMap.put(PICK_UP_CONTAINER, Text.toCamel(PICK_UP_CONTAINER));
		attributeMap.put(FAIL_CONTAINER, Text.toCamel(FAIL_CONTAINER));
		attributeMap.put(STACK_CONTAINER, Text.toCamel(STACK_CONTAINER));
		attributeMap.put(AUTO_ZONE, Text.toCamel(AUTO_ZONE));
		attributeMap.put(PICK_UP_GRAY, Text.toCamel(PICK_UP_GRAY));
		attributeMap.put(FAIL_GRAY, Text.toCamel(FAIL_GRAY));
		attributeMap.put(STACK_GRAY, Text.toCamel(STACK_GRAY));
		attributeMap.put(PICK_UP_LITTER, Text.toCamel(PICK_UP_LITTER));
		attributeMap.put(FAIL_LITTER, Text.toCamel(FAIL_LITTER));
		attributeMap.put(SCORE_LITTER, Text.toCamel(SCORE_LITTER));
		attributeMap.put(KNOCK_TOTE_ROBOT, Text.toCamel(KNOCK_TOTE_ROBOT));
		attributeMap.put(KNOCK_LITTER_ROBOT, Text.toCamel(KNOCK_LITTER_ROBOT));
		attributeMap.put(KNOCK_CONTAINER_ROBOT, Text.toCamel(KNOCK_CONTAINER_ROBOT));
		attributeMap.put(KNOCK_TOTE_STACK, Text.toCamel(KNOCK_TOTE_STACK));
		attributeMap.put(KNOCK_LITTER_STACK, Text.toCamel(KNOCK_LITTER_STACK));
		attributeMap.put(KNOCK_CONTAINER_STACK, Text.toCamel(KNOCK_CONTAINER_STACK));
		attributeMap.put(STUCK, Text.toCamel(STUCK));
		attributeMap.put(FOUL, Text.toCamel(FOUL));
		attributeMap.put(YELLOW_CARD, Text.toCamel(YELLOW_CARD));
		attributeMap.put(RED_CARD, Text.toCamel(RED_CARD));
		attributeMap.put(STAR, Text.toCamel(STAR));
	}

	private static Map summingMap = new HashMap();

	static {
		summingMap.put(PICK_UP_YELLOW, Text.toCamel(PICK_UP_YELLOW));
		summingMap.put(FAIL_YELLOW, Text.toCamel(FAIL_YELLOW));
		// Stack Yellow

		summingMap.put(PICK_UP_CONTAINER, Text.toCamel(PICK_UP_CONTAINER));
		summingMap.put(FAIL_CONTAINER, Text.toCamel(FAIL_CONTAINER));
		summingMap.put(STACK_CONTAINER, Text.toCamel(STACK_CONTAINER));

		summingMap.put(PICK_UP_GRAY, Text.toCamel(PICK_UP_GRAY));
		summingMap.put(FAIL_GRAY, Text.toCamel(FAIL_GRAY));
		// Stack Gray

		summingMap.put(PICK_UP_LITTER, Text.toCamel(PICK_UP_LITTER));
		summingMap.put(FAIL_LITTER, Text.toCamel(FAIL_LITTER));
		summingMap.put(SCORE_LITTER, Text.toCamel(SCORE_LITTER));

		summingMap.put(KNOCK_TOTE_ROBOT, Text.toCamel(KNOCK_TOTE_ROBOT));
		summingMap.put(KNOCK_LITTER_ROBOT, Text.toCamel(KNOCK_LITTER_ROBOT));
		summingMap.put(KNOCK_CONTAINER_ROBOT, Text.toCamel(KNOCK_CONTAINER_ROBOT));
		summingMap.put(KNOCK_TOTE_STACK, Text.toCamel(KNOCK_TOTE_STACK));
		summingMap.put(KNOCK_LITTER_STACK, Text.toCamel(KNOCK_LITTER_STACK));
		summingMap.put(KNOCK_CONTAINER_STACK, Text.toCamel(KNOCK_CONTAINER_STACK));
		summingMap.put(STUCK, Text.toCamel(STUCK));

		summingMap.put(FOUL, Text.toCamel(FOUL));
		summingMap.put(YELLOW_CARD, Text.toCamel(YELLOW_CARD));
		summingMap.put(RED_CARD, Text.toCamel(RED_CARD));

		summingMap.put(AUTO_ZONE, Text.toCamel(AUTO_ZONE));

	}

	private static Map averageMap = new HashMap();

	static {
		averageMap.put(PICK_UP_YELLOW, Text.toCamel(PICK_UP_YELLOW));
		averageMap.put(FAIL_YELLOW, Text.toCamel(FAIL_YELLOW));
		averageMap.put(STACK_YELLOW, Text.toCamel(STACK_YELLOW));

		averageMap.put(PICK_UP_CONTAINER, Text.toCamel(PICK_UP_CONTAINER));
		averageMap.put(FAIL_CONTAINER, Text.toCamel(FAIL_CONTAINER));
		averageMap.put(STACK_CONTAINER, Text.toCamel(STACK_CONTAINER));

		averageMap.put(AUTO_ZONE, Text.toCamel(AUTO_ZONE));

		averageMap.put(PICK_UP_GRAY, Text.toCamel(PICK_UP_GRAY));
		averageMap.put(FAIL_GRAY, Text.toCamel(FAIL_GRAY));
		averageMap.put(STACK_GRAY, Text.toCamel(STACK_GRAY));

		averageMap.put(PICK_UP_LITTER, Text.toCamel(PICK_UP_LITTER));
		averageMap.put(FAIL_LITTER, Text.toCamel(FAIL_LITTER));
		averageMap.put(SCORE_LITTER, Text.toCamel(SCORE_LITTER));
	}

	public Score() {

	}

	public Score(Map data) {
		setDataMap(data);
	}

	public static Map getAttributeMap() {
		return attributeMap;
	}

	public static void setAttributeMap(Map attributeMap) {
		Score.attributeMap = attributeMap;
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
		return SCORE_ID;
	}

	@XmlTransient
	public String getId() {
		return scoreId;
	}

	@Override
	public void setId(String id) {
		setScoreId(id);
	}

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getPosX() {
		return posX;
	}

	public void setPosX(String posX) {
		this.posX = posX;
	}

	public String getPosY() {
		return posY;
	}

	public void setPosY(String posY) {
		this.posY = posY;
	}

	public String getYellowZonePlacement() {
		return yellowZonePlacement;
	}

	public void setYellowZonePlacement(String yellowZonePlacement) {
		this.yellowZonePlacement = yellowZonePlacement;
	}

	public String getRobotPlacement() {
		return robotPlacement;
	}

	public void setRobotPlacement(String robotPlacement) {
		this.robotPlacement = robotPlacement;
	}

	public String getPickUpYellow() {
		return pickUpYellow;
	}

	public void setPickUpYellow(String pickUpYellow) {
		this.pickUpYellow = pickUpYellow;
	}

	public String getFailYellow() {
		return failYellow;
	}

	public void setFailYellow(String failYellow) {
		this.failYellow = failYellow;
	}

	public String getStackYellow() {
		return stackYellow;
	}

	public void setStackYellow(String stackYellow) {
		this.stackYellow = stackYellow;
	}

	public String getPickUpContainer() {
		return pickUpContainer;
	}

	public void setPickUpContainer(String pickUpContainer) {
		this.pickUpContainer = pickUpContainer;
	}

	public String getFailContainer() {
		return failContainer;
	}

	public void setFailContainer(String failContainer) {
		this.failContainer = failContainer;
	}

	public String getAutoZone() {
		return autoZone;
	}

	public void setAutoZone(String autoZone) {
		this.autoZone = autoZone;
	}

	public String getPickUpGray() {
		return pickUpGray;
	}

	public void setPickUpGray(String pickUpGray) {
		this.pickUpGray = pickUpGray;
	}

	public String getFailGray() {
		return failGray;
	}

	public void setFailGray(String failGray) {
		this.failGray = failGray;
	}

	public String getStackGray() {
		return stackGray;
	}

	public void setStackGray(String stackGray) {
		this.stackGray = stackGray;
	}

	public String getPickUpLitter() {
		return pickUpLitter;
	}

	public void setPickUpLitter(String pickUpLitter) {
		this.pickUpLitter = pickUpLitter;
	}

	public String getFailLitter() {
		return failLitter;
	}

	public void setFailLitter(String failLitter) {
		this.failLitter = failLitter;
	}

	public String getScoreLitter() {
		return scoreLitter;
	}

	public void setScoreLitter(String scoreLitter) {
		this.scoreLitter = scoreLitter;
	}

	public String getKnockToteRobot() {
		return knockToteRobot;
	}

	public void setKnockToteRobot(String knockToteRobot) {
		this.knockToteRobot = knockToteRobot;
	}

	public String getKnockLitterRobot() {
		return knockLitterRobot;
	}

	public void setKnockLitterRobot(String knockLitterRobot) {
		this.knockLitterRobot = knockLitterRobot;
	}

	public String getKnockContainerRobot() {
		return knockContainerRobot;
	}

	public void setKnockContainerRobot(String knockContainerRobot) {
		this.knockContainerRobot = knockContainerRobot;
	}

	public String getKnockToteStack() {
		return knockToteStack;
	}

	public void setKnockToteStack(String knockToteStack) {
		this.knockToteStack = knockToteStack;
	}

	public String getKnockLitterStack() {
		return knockLitterStack;
	}

	public void setKnockLitterStack(String knockLitterStack) {
		this.knockLitterStack = knockLitterStack;
	}

	public String getKnockContainerStack() {
		return knockContainerStack;
	}

	public void setKnockContainerStack(String knockContainerStack) {
		this.knockContainerStack = knockContainerStack;
	}

	public String getStuck() {
		return stuck;
	}

	public void setStuck(String stuck) {
		this.stuck = stuck;
	}

	public String getFoul() {
		return foul;
	}

	public void setFoul(String foul) {
		this.foul = foul;
	}

	public String getYellowCard() {
		return yellowCard;
	}

	public void setYellowCard(String yellowCard) {
		this.yellowCard = yellowCard;
	}

	public String getRedCard() {
		return redCard;
	}

	public void setRedCard(String redCard) {
		this.redCard = redCard;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public static Map getSummingMap() {
		return summingMap;
	}

	public static void setSummingMap(Map summingMap) {
		Score.summingMap = summingMap;
	}

	public static Map getAverageMap() {
		return averageMap;
	}

	public static void setAverageMap(Map averageMap) {
		Score.averageMap = averageMap;
	}

	public String getStackContainer() {
		return stackContainer;
	}

	public void setStackContainer(String stackContainer) {
		this.stackContainer = stackContainer;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}
}
