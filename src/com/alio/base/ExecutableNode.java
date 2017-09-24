package com.alio.base;

import com.alio.exec.ExecutorType;
import com.alio.graph.AnyObject;

public abstract class ExecutableNode extends Node implements IExecutable {

	public ExecutableNode(AnyObject priorityWeight) {
		super(null, priorityWeight);
	}

	public abstract ExecutorType getMethodType();
	
	@Override
	public String getName() {
		return getMethodType().name();
	}
	
	@Override
	public String dump() {
		return "[" + getMethodType() + "] value:["+ super.dump() + "]";
	}
	
}
