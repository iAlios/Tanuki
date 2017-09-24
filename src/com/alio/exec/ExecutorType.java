package com.alio.exec;

import com.alio.base.ExecutableNode;

public enum ExecutorType {

	PLUS("+") {

		@Override
		public ExecutableNode getExecutor() {
			return new PlusExecutor();
		}

	},
	MINUS("-") {

		@Override
		public ExecutableNode getExecutor() {
			return new MinusExecutor();
		}

	},
	MULTIPLY("*") {

		@Override
		public ExecutableNode getExecutor() {
			return new MultipyExecutor();
		}

	},
	DIVIDE("/") {

		@Override
		public ExecutableNode getExecutor() {
			return new DivideExecutor();
		}

	},
	MOD("%") {

		@Override
		public ExecutableNode getExecutor() {
			return new ModExecutor();
		}

	};
	private String mType;
	
	private ExecutorType(String type) {
		mType = type;
	}
	
	public String type() {
		return mType;
	}
	
	public abstract ExecutableNode getExecutor();

	public static final ExecutorType typeOf(String type) {
		for(ExecutorType eType:values()) {
			if(eType.mType.equals(type)) {
				return eType;
			}
		}
		return null;
	}
	
}
