package com.alio.exec;

import com.alio.base.ExecutableNode;
import com.alio.graph.AnyObject;

public class MinusExecutor extends ExecutableNode {

	@Override
	public boolean exec(AnyObject... param) {
		return false;
	}

	@Override
	public ExecutorType getMethodType() {
		return ExecutorType.MINUS;
	}

}
