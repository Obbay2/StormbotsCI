package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.utility.Text;

public class RobotDetails extends BasePOJO {

	public static String TABLE_NAME = "ROBOT_DETAILS";

	public static String ROBOT_DETAILS_ID = "ROBOT_DETAILS_ID";
	public static String ROBOT_ID = "ROBOT_ID";
	public static String AUTO_PICK_UP_TOTE = "AUTO_PICK_UP_TOTE";
	public static String AUTO_PICK_UP_CONTAINER = "AUTO_PICK_UP_CONTAINER";
	public static String AUTO_CAN_STACK_TOTE = "AUTO_CAN_STACK_TOTE";
	public static String AUTO_CAN_STACK_CONTAINER = "AUTO_CAN_STACK_CONTAINER";
	public static String AUTO_MOVE_TO_AUTOZONE = "AUTO_MOVE_TO_AUTOZONE";
	public static String PICK_UP_TOTE = "PICK_UP_TOTE";
	public static String PICK_UP_CONTAINER = "PICK_UP_CONTAINER";
	public static String PICK_UP_LITTER = "PICK_UP_LITTER";
	public static String TOTE_STORAGE = "TOTE_STORAGE";
	public static String CONTAINER_STORAGE = "CONTAINER_STORAGE";
	public static String LITTER_STORAGE = "LITTER_STORAGE";
	public static String CAN_CHUTE_TOTE = "CAN_CHUTE_TOTE";
	public static String CAN_CHUTE_LITTER = "CAN_CHUTE_LITTER";
	public static String STACK_TOTE = "STACK_TOTE";
	public static String CAN_STACK_CONTAINER = "CAN_STACK_CONTAINER";
	public static String CAN_STACK_LITTER = "CAN_STACK_LITTER";
	public static String STACK_COOP = "STACK_COOP";
	public static String NOTES = "NOTES";

	public String robotDetailsId = "";
	public String robotId = "";
	public String autoPickUpTote = "";
	public String autoPickUpContainer = "";
	public String autoCanStackTote = "";
	public String autoCanStackContainer = "";
	public String autoMoveToAutozone = "";
	public String pickUpTote = "";
	public String pickUpContainer = "";
	public String pickUpLitter = "";
	public String toteStorage = "";
	public String containerStorage = "";
	public String litterStorage = "";
	public String canChuteTote = "";
	public String canChuteLitter = "";
	public String stackTote = "";
	public String canStackContainer = "";
	public String canStackLitter = "";
	public String stackCoop = "";
	public String notes = "";

	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(ROBOT_DETAILS_ID, Text.toCamel(ROBOT_DETAILS_ID));
		attributeMap.put(ROBOT_ID, Text.toCamel(ROBOT_ID));
		attributeMap.put(AUTO_PICK_UP_TOTE, Text.toCamel(AUTO_PICK_UP_TOTE));
		attributeMap.put(AUTO_PICK_UP_CONTAINER,Text.toCamel(AUTO_PICK_UP_CONTAINER));
		attributeMap.put(AUTO_CAN_STACK_TOTE, Text.toCamel(AUTO_CAN_STACK_TOTE));
		attributeMap.put(AUTO_CAN_STACK_CONTAINER,Text.toCamel(AUTO_CAN_STACK_CONTAINER));
		attributeMap.put(AUTO_MOVE_TO_AUTOZONE,Text.toCamel(AUTO_MOVE_TO_AUTOZONE));
		attributeMap.put(PICK_UP_TOTE, Text.toCamel(PICK_UP_TOTE));
		attributeMap.put(PICK_UP_CONTAINER, Text.toCamel(PICK_UP_CONTAINER));
		attributeMap.put(PICK_UP_LITTER, Text.toCamel(PICK_UP_LITTER));
		attributeMap.put(TOTE_STORAGE, Text.toCamel(TOTE_STORAGE));
		attributeMap.put(CONTAINER_STORAGE, Text.toCamel(CONTAINER_STORAGE));
		attributeMap.put(LITTER_STORAGE, Text.toCamel(LITTER_STORAGE));
		attributeMap.put(CAN_CHUTE_TOTE, Text.toCamel(CAN_CHUTE_TOTE));
		attributeMap.put(CAN_CHUTE_LITTER, Text.toCamel(CAN_CHUTE_LITTER));
		attributeMap.put(STACK_TOTE, Text.toCamel(STACK_TOTE));
		attributeMap.put(CAN_STACK_CONTAINER, Text.toCamel(CAN_STACK_CONTAINER));
		attributeMap.put(CAN_STACK_LITTER, Text.toCamel(CAN_STACK_LITTER));
		attributeMap.put(STACK_COOP, Text.toCamel(STACK_COOP));
		attributeMap.put(NOTES, Text.toCamel(NOTES));
	}

	@XmlTransient
	public String toString() {
		return "Robot Details Id: " + robotDetailsId + ", Robot Id: " + robotId + ", Auto Pickup Tote: "
				+ autoPickUpTote + ", Auto Pickup Container: " + autoPickUpContainer
				+ ", Auto Can Stack Tote: " + autoCanStackTote + ", Auto Can Stack Container: " + autoCanStackContainer
				+ ", Auto Move To Autozone: " + autoMoveToAutozone + ", Pick Up Tote: " + pickUpTote + ", Pick Up Container: "
				+ pickUpContainer + ", Pick Up Litter: " + pickUpLitter + ", Tote Storage: " + toteStorage + ", Container Storage: " + containerStorage
				+ "Litter Storage: " + litterStorage + ", Can Chute Tote: " + canChuteTote + ", Can Chute Litter: "
				+ canChuteLitter + ", Stack Tote: " + stackTote
				+ ", Can Stack Container: " + canStackContainer + ", Can Stack Litter: " + canStackLitter
				+ ", Stack Coop: " + stackCoop + ", Notes: " + notes;
	}
	
	public RobotDetails(){
		
	}

	public RobotDetails(Map data) {

	}

	@Override
	public String getId(){
		return robotDetailsId;
	}

	@Override
	public void setId(String id) {
		setRobotDetailsId(id);
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getIDName() {
		return ROBOT_DETAILS_ID;
	}

	@Override
	public Map getAtributeMap() {
		return attributeMap;
	}

	public String getRobotDetailsId() {
		return robotDetailsId;
	}

	public void setRobotDetailsId(String robotDetailsId) {
		this.robotDetailsId = robotDetailsId;
	}

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getAutoPickUpTote() {
		return autoPickUpTote;
	}

	public void setAutoPickUpTote(String autoPickUpTote) {
		this.autoPickUpTote = autoPickUpTote;
	}

	public String getAutoPickUpContainer() {
		return autoPickUpContainer;
	}

	public void setAutoPickUpContainer(String autoPickUpContainer) {
		this.autoPickUpContainer = autoPickUpContainer;
	}

	public String getAutoCanStackTote() {
		return autoCanStackTote;
	}

	public void setAutoCanStackTote(String autoCanStackTote) {
		this.autoCanStackTote = autoCanStackTote;
	}

	public String getAutoCanStackContainer() {
		return autoCanStackContainer;
	}

	public void setAutoCanStackContainer(String autoCanStackContainer) {
		this.autoCanStackContainer = autoCanStackContainer;
	}

	public String getAutoMoveToAutozone() {
		return autoMoveToAutozone;
	}

	public void setAutoMoveToAutozone(String autoMoveToAutozone) {
		this.autoMoveToAutozone = autoMoveToAutozone;
	}

	public String getPickUpTote() {
		return pickUpTote;
	}

	public void setPickUpTote(String pickUpTote) {
		this.pickUpTote = pickUpTote;
	}

	public String getPickUpContainer() {
		return pickUpContainer;
	}

	public void setPickUpContainer(String pickUpContainer) {
		this.pickUpContainer = pickUpContainer;
	}

	public String getPickUpLitter() {
		return pickUpLitter;
	}

	public void setPickUpLitter(String pickUpLitter) {
		this.pickUpLitter = pickUpLitter;
	}

	public String getToteStorage() {
		return toteStorage;
	}

	public void setToteStorage(String toteStorage) {
		this.toteStorage = toteStorage;
	}

	public String getContainerStorage() {
		return containerStorage;
	}

	public void setContainerStorage(String containerStorage) {
		this.containerStorage = containerStorage;
	}

	public String getLitterStorage() {
		return litterStorage;
	}

	public void setLitterStorage(String litterStorage) {
		this.litterStorage = litterStorage;
	}

	public String getCanChuteTote() {
		return canChuteTote;
	}

	public void setCanChuteTote(String canChuteTote) {
		this.canChuteTote = canChuteTote;
	}

	public String getCanChuteLitter() {
		return canChuteLitter;
	}

	public void setCanChuteLitter(String canChuteLitter) {
		this.canChuteLitter = canChuteLitter;
	}

	public String getStackTote() {
		return stackTote;
	}

	public void setStackTote(String stackTote) {
		this.stackTote = stackTote;
	}

	public String getCanStackContainer() {
		return canStackContainer;
	}

	public void setCanStackContainer(String canStackContainer) {
		this.canStackContainer = canStackContainer;
	}

	public String getCanStackLitter() {
		return canStackLitter;
	}

	public void setCanStackLitter(String canStackLitter) {
		this.canStackLitter = canStackLitter;
	}

	public String getStackCoop() {
		return stackCoop;
	}

	public void setStackCoop(String stackCoop) {
		this.stackCoop = stackCoop;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
