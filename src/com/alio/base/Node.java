package com.alio.base;

import com.alio.graph.AnyObject;

public class Node {

	protected AnyObject mValue;
	
	public void setValue(AnyObject obj) {
		mValue = obj;
	}
	
	public AnyObject getValue() {
		return mValue;
	}

	public String dump() {
		return mValue.toString();
	}
	
}
