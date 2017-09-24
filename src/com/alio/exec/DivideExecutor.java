package com.alio.exec;

import com.alio.base.ExecutableNode;
import com.alio.base.PriorityConstant;
import com.alio.graph.AnyObject;

public class DivideExecutor extends ExecutableNode {

	public DivideExecutor() {
		super(ExecutorType.DIVIDE, AnyObject.valueOf(PriorityConstant.DIVIDE));
	}

	@Override
	public boolean exec(AnyObject... params) {
		if (params.length == 0) {
			return true;
		}
		mValue = params[0].clone();
		for (int index = 1; index < params.length; index++) {
			if (params[index] == null) {
				continue;
			}
			mValue.divide(params[index]);
		}
		return true;
	}

}
