package com.alio.exec;

import com.alio.base.ExecutableNode;
import com.alio.base.PriorityConstant;
import com.alio.structure.AnyObject;

public class PlusExecutor extends ExecutableNode {

	public PlusExecutor() {
		super(ExecutorType.PLUS, AnyObject.valueOf(PriorityConstant.PLUS));
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
			mValue.plus(params[index]);
		}
		return true;
	}

}