package com.sb.ci.utility;

public class Text {
	
	public static String toCamel(String input) {
		String lowCase = input.toLowerCase();

		for (int i = 0; i < lowCase.length(); i++) {
			if (lowCase.charAt(i) == '_') {
				char low = lowCase.charAt(i + 1);
				low = Character.toUpperCase(low);
				lowCase = (lowCase.substring(0, i) + low + lowCase
						.substring(i + 2));
			}
		}
		String camel = lowCase.replace("_", "");
		return camel;
	}
	
	public static String toSQLCase(String input) {
		String SQLCase = input;
		String placeHold = SQLCase;
		int stringLength = SQLCase.length();
		for(int i = 0; i < stringLength; i++) {
			char characterAt = placeHold.charAt(i);
			if(Character.isUpperCase(characterAt)) {
				SQLCase = (placeHold.substring(0, i)+"_"+Character.toLowerCase(characterAt)+placeHold.substring(i+1, placeHold.length()));
			}
		}
		SQLCase = SQLCase.toUpperCase();
		return SQLCase;
	}

}
