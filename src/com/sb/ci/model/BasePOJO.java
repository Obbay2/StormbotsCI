package com.sb.ci.model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.model.ObjectHelper;

public abstract class BasePOJO {

	@XmlTransient
	public Map data;

	public abstract String getId();

	public abstract void setId(String id);

	public abstract String getTableName();

	public abstract String getIDName();

	public abstract Map getAtributeMap();

	public void buildFromParameters(Map<String, String[]> data) throws Exception {
		if (data != null) {
			Map<String, String> attributes = getAtributeMap();

			for (String attributeName : attributes.values()) {
				String[] valueArray = (String[]) data.get(attributeName);
				if (valueArray == null) {
					continue;
					// throw new Exception("Attribute " + attributeName +
					// " expected to build "
					// + this.getClass().getName()
					// +
					// " however was not sent.\n Check name of tag 'input' in corresponing HTML.");
				}
				String value = ((String[]) data.get(attributeName))[0];

				value = getAttributeValue(attributeName, value);

				try {
					ObjectHelper.setFieldValue(this, attributeName, value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@XmlTransient
	public String getAttributeValue(String attributeName, String attributeValue) throws Exception {

		if (attributeValue.equals("")) {
			attributeValue = null;
		}

		return attributeValue;
	}

	public void setDataMap(Map data) {
		this.data = data;
		normalizeData();

		if (data == null) {
			data = new HashMap();
		}
		Map<String, String> attributes = getAtributeMap();

		for (String dbName : attributes.keySet()) {
			Object value = data.get(dbName);
			String attributeName = attributes.get(dbName);
			try {
				ObjectHelper.setFieldValue(this, attributeName, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	@XmlTransient
	public Map getDataMap() {
		data = normalizeData();

		if (data == null) {
			data = new HashMap();
		}

		Map<String, String> attributes = getAtributeMap();

		for (String dbName : attributes.keySet()) {
			String attributeName = attributes.get(dbName);

			Object value = null;

			try {
				value = ObjectHelper.getFieldValue(this, attributeName);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			data.put(dbName, value);

		}

		return data;
	}

	private Map normalizeData() {
		if (data != null) {
			for (Iterator i = data.keySet().iterator(); i.hasNext();) {
				Object name = i.next();
				Object value = data.get(name);

				if (value == null) {
					value = "";
				}

				data.put(name, value);
			}
		}

		return data;
	}

	public static String mergeData(String[] data, String template) {

		for (int i = 0; i < data.length; i++) {
			String index = "{" + i + "}";
			template = template.replace(index, data[i]);
		}

		// MessageFormat formatter = new MessageFormat(template);
		// return formatter.format(data);

		return template;
	}
}
