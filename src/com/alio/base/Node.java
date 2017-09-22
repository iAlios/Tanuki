package com.alio.base;

import com.alio.graph.AnyObject;

public class Node {

	protected AnyObject mResult;
	
	public void set(AnyObject obj) {
		mResult = obj;
	}
	
	public AnyObject getResult() {
		return mResult;
	}

}
