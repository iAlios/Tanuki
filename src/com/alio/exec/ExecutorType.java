package com.alio.exec;

import com.alio.base.ExecutableNode;
import com.alio.base.PriorityConstant;
import com.alio.graph.AnyObject;

public enum ExecutorType {

	PLUS("+") {

		@Override
		public ExecutableNode getExecutor() {
			return new PlusExecutor();
		}

		@Override
		public AnyObject getPriorityWeight() {
			return AnyObject.valueOf(PriorityConstant.PLUS);
		}
		
	},
	MINUS("-") {

		@Override
		public ExecutableNode getExecutor() {
			return new MinusExecutor();
		}

		@Override
		public AnyObject getPriorityWeight() {
			return AnyObject.valueOf(PriorityConstant.MINUS);
		}
		
	},
	MULTIPLY("*") {

		@Override
		public ExecutableNode getExecutor() {
			return new MultipyExecutor();
		}

		@Override
		public AnyObject getPriorityWeight() {
			return AnyObject.valueOf(PriorityConstant.MULTIPLY);
		}
		
	},
	DIVIDE("/") {

		@Override
		public ExecutableNode getExecutor() {
			return new DivideExecutor();
		}

		@Override
		public AnyObject getPriorityWeight() {
			return AnyObject.valueOf(PriorityConstant.DIVIDE);
		}
		
	},
	MOD("%") {

		@Override
		public ExecutableNode getExecutor() {
			return new ModExecutor();
		}

		@Override
		public AnyObject getPriorityWeight() {
			return AnyObject.valueOf(PriorityConstant.MOD);
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
	
	public abstract AnyObject getPriorityWeight() ;
	
}
