package com.alio.base;

import com.alio.structure.AnyObject;

public class Node {

	protected AnyObject mValue;
	
	private String mName;
	
	private AnyObject mPriorityWeight;
	
	public Node(String name) {
		super();
		mName = name;
		mPriorityWeight = AnyObject.valueOf(PriorityConstant.NORMAL);
	}
	
	public Node(String name, AnyObject priorityWeight) {
		super();
		mName = name;
		mPriorityWeight = priorityWeight;
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

	public void increasePriorityWeight(long p) {
		mPriorityWeight.plus(p);
	}
	
	public AnyObject getPriorityWeight() {
		return mPriorityWeight;
	}
	
	public String dump() {
		if(mValue == null) {
			return null;
		}
		return mValue.toString();
	}

	@Override
	public String toString() {
		return "Node [mValue=" + mValue + ", mName=" + mName + ", mPriorityWeight=" + mPriorityWeight + "]";
	}

}
