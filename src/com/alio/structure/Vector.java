package com.alio.structure;

public class Vector<T> {

	private T[] mValues;
	
	public Vector(int length) {
		super();
	}
	
	public Vector(T[] values) {
		super();
		mValues = values;
	}
	
	public T get(int index) {
		return mValues[index];
	}
	
	public int size() {
		return mValues.length;
	}
	
	public Vector<T> add(Vector<T> v) {
		Vector<T> result = null;
		if(this.mValues.length > v.size()) {
			result = this.clone();
			for(int index = 0; index < mValues.length; index ++) {
				//result.mValues[index] += v.mValues[index];
			}
		} else {
			result = v.clone();
			for(int index = 0; index < mValues.length; index ++) {
				//result.mValues[index] += mValues[index];
			}
		}
		return result;
	}
	
	public Vector<T> clone() {
		return new Vector<T>(mValues);
	}
	
	public static void main(String[] args) {

	}

}
