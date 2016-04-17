package com.sb.database;

import java.util.Map;

import com.sb.ci.model.BasePOJO;

public class Update {

	private Query query;

	public Update(Query query) {
		this.query = query;
	}

	public String updateSomething(BasePOJO pojo) {

		Map data = pojo.getDataMap();
		String tableName = pojo.getTableName();

		String result = "UPDATE " + tableName + " SET ";
		String where = " WHERE " + pojo.getIDName() + " = ";

		query.execute("SELECT * FROM " + tableName + " WHERE 1=1");
		String[] fields = query.getColumnNames();

		for (int i = 0; i < fields.length; i++) {

			if (fields[i].equals(pojo.getIDName())) {
				where += "'" + (String) data.get(fields[i]) + "'";
				continue;
			}

			result = result + fields[i];
			String value = (String) data.get(fields[i]);
			String quote = "'";

			if (value == null || value.equals("")) {
				if (value != null) {
					value = null;
				}

				quote = "";
			}

			result += " = " + quote + value + quote;

			if (i < fields.length - 1) {
				result = result + ", ";
			}
		}
		result = result + where;
		query.executeUpdate(result);
		String id = query.getId();

		pojo.setId(id);

		return pojo.getId();
	}
}
