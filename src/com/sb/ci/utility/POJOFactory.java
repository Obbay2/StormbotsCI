package com.sb.ci.utility;

import java.util.HashMap;
import java.util.Map;

import com.sb.ci.exception.CIException;
import com.sb.ci.model.BasePOJO;
import com.sb.ci.model.Competition;
import com.sb.ci.model.Game;
import com.sb.ci.model.Robot;
import com.sb.ci.model.RobotDetails;
import com.sb.ci.model.Round;
import com.sb.ci.model.Score;
import com.sb.ci.model.Team;

public class POJOFactory {

	private static Map<String, Class<? extends BasePOJO>> classes = new HashMap<String, Class<? extends BasePOJO>>();

	static {
		register(Team.TABLE_NAME.toUpperCase(), Team.class);
		register(Game.TABLE_NAME.toUpperCase(), Game.class);
		register(Competition.TABLE_NAME.toUpperCase(), Competition.class);
		register(Robot.TABLE_NAME.toUpperCase(), Robot.class);
		register(RobotDetails.TABLE_NAME.toUpperCase(), RobotDetails.class);
		register(Score.TABLE_NAME.toUpperCase(), Score.class);
		register(Round.TABLE_NAME.toUpperCase(), Round.class);
	}
	
	public static void register(String tableName,
			Class<? extends BasePOJO> pojoClass) {
		classes.put(tableName.toUpperCase(), pojoClass);
	}

	public static BasePOJO getInstance(String tableName) {
		BasePOJO result = null;

		Class<? extends BasePOJO> clazz = classes.get(tableName.toUpperCase());

		try {
			result = clazz.newInstance();
		} catch (Exception e) {
			throw new CIException(e);
		}

		return result;
	}

}
