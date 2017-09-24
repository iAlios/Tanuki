package com.alio.base;

import com.alio.exec.ExecutorType;
import com.alio.graph.AnyObject;

public abstract class ExecutableNode extends Node implements IExecutable {

	private ExecutorType mExecutorType = null;
	
	public ExecutableNode(ExecutorType type, AnyObject priorityWeight) {
		super(type.type(), priorityWeight);
		mExecutorType = type;
	}

	public ExecutorType getMethodType() {
		return mExecutorType;
	}
	
	@Override
	public String dump() {
		return "[" + getMethodType() + "] value:["+ super.dump() + "]";
	}
	
}
