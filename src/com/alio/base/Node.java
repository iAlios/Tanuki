package com.alio.base;

import com.alio.graph.AnyObject;

public class Node {

	protected AnyObject mValue;
	
	private String mName;
	
	public Node(String name) {
		super();
		mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setValue(AnyObject obj) {
		mValue = obj;
	}
	
	public AnyObject getValue() {
		return mValue;
	}

	public String dump() {
		if(mValue == null) {
			return null;
		}
		return mValue.toString();
	}
	
}
