package com.netmind.business;

public class Calculator {
	
	public int add(int num1, int num2)
	{
		return num1 + num2;
	}
	
	public int substract(int num1, int num2)
	{
		return num1 - num2;
	}
	
	public int multiply(int num1, int num2)
	{
		return num1 * num2;
	}
	
	public int divide(int num1, int num2)
	{
		return num1 / num2;
	}
	
	public int arraySum() {
		int paresSum = 0;
		int[] matrix = new int[100];
		
		
		
		for(int index = 0; index<matrix.length;index++) {
			
			if(index%2==0) {
				paresSum+=index;
			}
		}
		
		return paresSum;
	}
	
	
}	
	
