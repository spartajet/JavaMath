package com.gxz.mymath.test;

import java.math.BigDecimal;

public class BigdecimalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal aBigDecimal = new BigDecimal("1.22");
		BigDecimal bigDecimal = new BigDecimal("2.22");
		aBigDecimal.add(bigDecimal);
		System.out.println(aBigDecimal);
		Double aaaDouble=1.22;
		System.out.println(2.5*3.1416);
		System.out.println(0.06+0.01);
		System.out.println();
		System.out.println("--------Java自身的Double类型有精度损失----------");
		System.out.println(0.05+0.01); //0.060000000000000005
		System.out.println(1.0-0.54); //0.45999999999999996
		System.out.println(4.015*1000);//4014.9999999999995
		System.out.println(12.3/100);//0.12300000000000001
		
		float f = 0.1f;
		float sum = 0;
		for(int i=0; i<4; i++)
		{
		    sum += f;
		}
		System.out.println(sum);

	}

}
