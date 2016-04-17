package com.sb.ci.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ObjectHelper {

	/**
	 * This method sets the value of a specified field on a given object.
	 * <p>
	 * This method will check to see if the field is public, and if it is,
	 * access the field directly. If the field is not public, the method will
	 * look for a mutator method based on the <em>javaBeans</em> specification.
	 * If a mutator cannot be found, then the exception
	 * <code>IllegalAccessException</code> is thrown to the caller.
	 * </p>
	 * 
	 * <p>
	 * <strong><em>WARNING:</em> </strong> This method does not check to see if
	 * the target field is assignable based on the type of object being passed,
	 * nor does it check to see if the field is even accessible. This check must
	 * be performed (if needed) by the caller.
	 * </p>
	 * 
	 * @param obj
	 *            The object instance to set the value on
	 * @param field
	 *            The field definition for the field to be set in the class of
	 *            the object
	 * @param value
	 *            The value to be set in the field (wrapped in a
	 *            java.lang.Object, even if the field is a primitive).
	 * @throws IllegalAccessException
	 *             Thrown if the field can not be set.
	 * @throws AjentException
	 *             if anything fails
	 */
	public static void setFieldValue(Object obj, Field field, Object value) throws IllegalAccessException,
			InvocationTargetException {

		if (obj != null && field != null) {
			if (Modifier.isPublic(field.getModifiers())) {
				field.set(obj, value);
			} else {
				Method mutator = getMutator(obj.getClass(), field);
				if (mutator == null) {
					IllegalAccessException ae = new IllegalAccessException(
							"No mutator available for non-public field \"" + field.getName() + "\" on "
									+ obj.getClass());
					throw ae;
				}
				mutator.setAccessible(Modifier.isProtected(mutator.getModifiers()));
				mutator.invoke(obj, new Object[] { value });
			}
		}
	}

	/**
	 * This method returns the mutator method that corresponds to the
	 * <code>Field</code> object provided.
	 * 
	 * @param clazz
	 *            The class that contains the field for which the mutator is
	 *            needed.
	 * @param field
	 *            The field that the mutator operates on
	 * @return A <code>Method</code> object that defines the mutator, or null if
	 *         no acceptible mutator was found.
	 */
	public static Method getMutator(Class clazz, Field field) {
		Method mutator = null;
		if (field != null) {
			String mutatorName = "set" + capitalizeFirstChar(field.getName());
			Class[] args = new Class[] { field.getType() };
			mutator = getMethod(clazz, mutatorName, args);
		}
		return mutator;
	}

	private static String capitalizeFirstChar(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}

	/**
	 * This is a method used to return a method on a class regardless of the
	 * class in the hierarchy that implements the method
	 * 
	 * @param clazz
	 *            The Class to be examined
	 * @param methodName
	 *            The method name to be returned
	 * @param args
	 *            The <code>Class[]</code> (array of Class objects) array that
	 *            represents the argument list part of the method signature.
	 * @return The <code>Method</code> or null.
	 */
	public static Method getMethod(Class clazz, String methodName, Class[] args) {
		Method method;

		// If were passed a null Class reference then this can't possibly
		// contain the method.
		if (clazz == null) {
			return null;
		}

		// Check to see if the class is one of the java primitives. If it is,
		// then there is no need to look any further.
		if (clazz.isPrimitive()) {
			return null;
		}

		// stop at class Object, note this will NOT return methods on Object
		// itself
		if (clazz.getName().equals(Object.class.getName())) {
			return null;
		}

		try {
			method = clazz.getDeclaredMethod(methodName, args);
		} catch (SecurityException e) {
			return null;
		} catch (NoSuchMethodException e) {
			return getMethod(getSuperclass(clazz), methodName, args);
		}
		return method;
	}

	/**
	 * This method sets the value of a specified field on a given object.
	 * <p>
	 * This method will check to see if the field is public, and if it is,
	 * access the field directly. If the field is not public, the method will
	 * look for a mutator method based on the <em>javaBeans</em> specification.
	 * If a mutator cannot be found, then the exception
	 * <code>IllegalAccessException</code> is thrown to the caller.
	 * </p>
	 * 
	 * <p>
	 * <strong><em>WARNING:</em> </strong> This method does not check to see if
	 * the target field is assignable based on the type of object being passed,
	 * nor does it check to see if the field is even accessible. This check must
	 * be performed (if needed) by the caller.
	 * </p>
	 * 
	 * @param obj
	 *            The object instance to set the value on
	 * @param fieldName
	 *            The field name for the field to be set in the class of the
	 *            object
	 * @param value
	 *            The value to be set in the field (wrapped in a
	 *            java.lang.Object, even if the field is a primitive).
	 * @throws IllegalAccessException
	 *             Thrown if the field can not be set.
	 * @throws InvocationTargetException
	 * @throws AjentException
	 *             if anything fails
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value) throws IllegalAccessException,
			InvocationTargetException {
		if (obj != null) {
			Field field = getField(obj.getClass(), fieldName);
			if (field == null) {
				IllegalAccessException ae = new IllegalAccessException("Field \"" + fieldName + "\" does not exist on "
						+ obj.getClass());
				throw ae;
			}
			setFieldValue(obj, field, value);
		}
	}

	/**
	 * This method is used to return a field from any class in a class hierarchy
	 * regardless of it's access controls, scope, etc.
	 * 
	 * @param clazz
	 *            The class to be examined for the field
	 * @param fieldName
	 *            The name of the field desired
	 * @return The <code>Field</code> object for the field, or null if the field
	 *         can not be found
	 */
	public static Field getField(Class clazz, String fieldName) {

		// If were passed a null Class reference then this can't possibly
		// contain the field.
		if (clazz == null) {
			return null;
		}

		// Check to see if the class is one of the java primitives. If it is,
		// then there is no need to look any further.
		if (clazz.isPrimitive()) {
			return null;
		}

		if (clazz.getName().equals(Object.class.getName())) {
			return null;
		}

		if (fieldName != null && !fieldName.equals("")) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (SecurityException e) {
				return null;
			} catch (NoSuchFieldException e) {
				return getField(getSuperclass(clazz), fieldName);
			}
		}
		return null;
	}

	/**
	 * Returns the Class representing the superclass of the entity. Correctly
	 * handles interfaces instead of returning null (which is what
	 * java.lang.Class.getSuperclass() returns for interfaces even if they
	 * actually extend another interface).
	 * 
	 * @param clazz
	 * @return
	 */
	public static Class getSuperclass(Class clazz) {

		if (clazz.isInterface()) {
			/*
			 * Note that since we were passed an interface, this returns a list
			 * of all of the interfaces we extend.
			 */
			Class[] interfaces = clazz.getInterfaces();
			return interfaces != null && interfaces.length > 0 ? interfaces[0] : null;
		} else {
			return clazz.getSuperclass();
		}
	}

	/**
	 * This method returns the value for a specified field on a given object.
	 * <p>
	 * This method will check to see if the field is public, and if it is,
	 * access the value directly. If the field is not public, the method will
	 * look for an accessor method based on the <em>javaBeans</em>
	 * specification. If an accessor cannot be found, then the exception
	 * <code>IllegalAccessException</code> is thrown to the caller.
	 * </p>
	 * 
	 * <p>
	 * <strong><em>WARNING:</em> </strong> This method does not check to see if
	 * the target field is accessible. This check must be performed (if needed)
	 * by the caller.
	 * </p>
	 * 
	 * @param obj
	 *            The object instance to obtain the value from
	 * @param fieldName
	 *            The field name for the field in the class of this object
	 * @return The object that contains the value for the field.
	 * @throws IllegalAccessException
	 *             If the field can not be accessed
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static Object getFieldValue(Object obj, String fieldName) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object value = null;
		if (obj != null) {
			Field field = getField(obj.getClass(), fieldName);
			if (field == null) {
				IllegalAccessException ae = new IllegalAccessException("Field \"" + fieldName + "\" does not exist on "
						+ obj.getClass());
				throw ae;
			}
			value = getFieldValue(obj, field);
		}
		return value;
	}

	/**
	 * This method returns the value for a specified field on a given object.
	 * <p>
	 * This method will check to see if the field is public, and if it is,
	 * access the value directly. If the field is not public, the method will
	 * look for an accessor method based on the <em>javaBeans</em>
	 * specification. If an accessor cannot be found, then the exception
	 * <code>IllegalAccessException</code> is thrown to the caller.
	 * </p>
	 * 
	 * <p>
	 * <strong><em>WARNING:</em> </strong> This method does not check to see if
	 * the target field is accessible. This check must be performed (if needed)
	 * by the caller.
	 * </p>
	 * 
	 * @param obj
	 *            The object instance to obtain the value from
	 * @param field
	 *            The field definition for the field in the class of this object
	 * @return The object that contains the value for the field.
	 * @throws IllegalAccessException
	 *             If the field can not be accessed
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static Object getFieldValue(Object obj, Field field) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object value = null;
		if (obj != null && field != null) {
			if (Modifier.isPublic(field.getModifiers())) {
				value = field.get(obj);
			} else {
				value = getAccessorValue(obj, field.getName());
			}
		}
		return value;
	}

	/**
	 * This method returns the value for a specified field accessor on a given
	 * object. It does not verify that the field is on the object, it just looks
	 * for an accessor that would match the field name.
	 * <p>
	 * The method will look for an accessor method based on the
	 * <em>javaBeans</em> specification. If an accessor cannot be found, then
	 * the exception <code>IllegalAccessException</code> is thrown to the
	 * caller.
	 * </p>
	 * 
	 * @param obj
	 *            The object instance to obtain the value from
	 * @param fieldName
	 *            The field name for which the accessor is named in the class of
	 *            this object
	 * @return The object that contains the value for the field.
	 * @throws IllegalAccessException
	 *             If the field value can not be accessed
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static Object getAccessorValue(Object obj, String fieldName) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object value = null;
		if (obj != null) {
			Method accessor = getAccessor(obj.getClass(), fieldName, false);
			if (accessor == null) {
				IllegalAccessException ae = new IllegalAccessException("No accessor available for field \"" + fieldName
						+ "\" on " + obj.getClass());
				throw ae;
			}
			accessor.setAccessible(Modifier.isProtected(accessor.getModifiers()));
			value = accessor.invoke(obj, new Object[] {});
		}
		return value;
	}

	/**
	 * This method returns the accessor method that corresponds to the field
	 * name provided.
	 * 
	 * @param clazz
	 *            The class that contains the named field
	 * @param fieldName
	 *            The field to return the accessor method for
	 * @param verifyField
	 *            Indicates whether or not to validate that the field is on the
	 *            object and that the accessor returns the same type as the
	 *            field.
	 * @return The <code>Method</code> that represents the accessor
	 */
	public static Method getAccessor(Class clazz, String fieldName, boolean verifyField) {
		Method accessor = null;

		Field field = getField(clazz, fieldName);
		if (!verifyField || field != null) {
			String fieldType = field != null ? field.getType().getName() : null;

			/*
			 * Check if the field is a boolean or a Boolean. If it is, then
			 * according to the javaBeans spec, the accessor could be named "is"
			 * + fieldName instead of "get" + fieldName. On the other hand, the
			 * spec says that "get" + fieldName could be used too.
			 */
			if (field != null && (fieldType.equals("boolean") || fieldType.equals(Boolean.class.getName()))) {
				// Yep, so use the "is" getter method per JavaBeans
				String accessorName = "is" + capitalizeFirstChar(fieldName);
				accessor = getMethod(clazz, accessorName, new Class[] {});
			}

			/*
			 * If the field is not a boolean/Boolean (or the boolean accessor is
			 * not named "is"+fieldName), try to find the accessor as
			 * "get"+fieldName.
			 */
			if (accessor == null) {
				String accessorName = "get" + capitalizeFirstChar(fieldName);
				accessor = getMethod(clazz, accessorName, new Class[] {});
			}

			// verify accessor return type same as field type
			if (verifyField && field != null && accessor != null
					&& !fieldType.equals(accessor.getReturnType().getName())) {
				// accessor does not return same type as field
				accessor = null;
			}
		}
		return accessor;
	}

	public static void main(String args[]) throws Exception {

		Team team = new Team();

		ObjectHelper.setFieldValue(team, "teamNumber", "2811");

		System.out.println("Team Number is: " + team.getTeamNumber());
	}

}
