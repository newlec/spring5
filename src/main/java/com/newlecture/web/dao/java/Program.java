package com.newlecture.web.dao.java;

import java.lang.reflect.InvocationTargetException;

import com.newlecture.web.entity.File;

public class Program {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		// D:\tools
		
		java.io.File directory = new java.io.File("D:\\tools");
		
		java.io.File[] files = directory.listFiles();
		
		for(int i=0; i<files.length; i++) {
			File f = new File(files[i]);		
			System.out.println(f.toJSON());
		}
			
		/*
		 * File f1 = new File(files[0]); System.out.println(f1.toString());
		 * 
		 * File f2 = new File(files[1]); System.out.println(f2.toString());
		 */
		
	}

}
