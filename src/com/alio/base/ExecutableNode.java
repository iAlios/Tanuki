package com.alio.base;

import com.alio.exec.ExecutorType;

public abstract class ExecutableNode extends Node implements IExecutable {

	public abstract ExecutorType getMethodType();
	
	@Override
	public String dump() {
		return "[" + getMethodType() + "] value:["+ super.dump() + "]";
	}
	
}
