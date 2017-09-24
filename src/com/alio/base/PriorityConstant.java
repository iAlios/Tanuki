package com.alio.base;

public class PriorityConstant {

	/**
	 * 普通常量之间的优先级关系
	 */
	public static final long NORMAL = 0x1;
	
	public static final long PLUS = 0x2;
	
	public static final long MINUS = 0x2;
	
	public static final long MOD = 0x4;
	
	public static final long MULTIPLY = 0x4;
	
	public static final long DIVIDE = 0x4;

	public static final long BRACKET = 0x8;
	
}
