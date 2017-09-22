package com.alio.graph;

public class AnyObject {

	private Object mValue;
	
	public AnyObject(int v) {
		mValue = Integer.valueOf(v);
	}

	public AnyObject(short v) {
		mValue = Short.valueOf(v);
	}

	public AnyObject(long v) {
		mValue = Long.valueOf(v);
	}

	public AnyObject(float v) {
		mValue = Float.valueOf(v);
	}

	public AnyObject(double v) {
		mValue = Double.valueOf(v);
	}

	public AnyObject(Object v) {
		mValue = v;
	}
	
	public Object value() {
		return mValue;
	}
	
	public static AnyObject valueOf(int v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(short v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(long v)  {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(float v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(double v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(Object v) {
		return new AnyObject(v);
	}
	
}
