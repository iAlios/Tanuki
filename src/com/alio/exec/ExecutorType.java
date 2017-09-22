package com.alio.exec;

import com.alio.base.IExecutable;

public enum ExecutorType {

	PLUS {

		@Override
		public IExecutable getExecutor() {
			return new PlusExecutor();
		}
		
	},
	MINUS {

		@Override
		public IExecutable getExecutor() {
			return new MinusExecutor();
		}
		
	},
	MULTIPLY {

		@Override
		public IExecutable getExecutor() {
			return new MultipyExecutor();
		}
		
	},
	DIVIDE {

		@Override
		public IExecutable getExecutor() {
			return new DivideExecutor();
		}
		
	};
	
	public abstract IExecutable getExecutor();
	
}
