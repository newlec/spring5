package com.newlecture.web.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JSONParser {

	public static String toJSON(Object object) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {

		Class<?> clazz= object.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		//Method[] methods = clazz.getDeclaredMethods();
		
		StringBuilder json = new StringBuilder();
		json.append("{");
		for(Field field : fields) {
			
			String name = field.getName();
			String getterName = "get" 
											+ Character.toUpperCase(name.charAt(0))
											+ name.substring(1);
			
			Method method = clazz.getDeclaredMethod(getterName, new Class[] {});			
			String value = String.valueOf(method.invoke(object));
			
			json.append(String.format("\"%s\":\"%s\""	, name, value));
		}
		json.append("}");
				
		
		return json.toString();
	}
	
}
