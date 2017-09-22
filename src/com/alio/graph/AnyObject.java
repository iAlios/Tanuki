package com.alio.graph;

public class AnyObject {

	private Object mValue;

	public AnyObject(int v) {
		mValue = Integer.valueOf(v);
	}

	public AnyObject(short v) {
		mValue = Short.valueOf(v);
	}

	public AnyObject(long v) {
		mValue = Long.valueOf(v);
	}

	public AnyObject(float v) {
		mValue = Float.valueOf(v);
	}

	public AnyObject(double v) {
		mValue = Double.valueOf(v);
	}

	public AnyObject(Object v) {
		mValue = v;
	}

	public void plus(int v) {
		if (mValue == null) {
			mValue = Integer.valueOf(v);
		} else {
			mValue = ((Integer) mValue) + v;
		}
	}

	public void plus(short v) {
		if (mValue == null) {
			mValue = Short.valueOf(v);
		} else {
			mValue = ((Short) mValue) + v;
		}
	}

	public void plus(long v) {
		if (mValue == null) {
			mValue = Long.valueOf(v);
		} else {
			mValue = ((Long) mValue) + v;
		}
	}

	public void plus(float v) {
		if (mValue == null) {
			mValue = Float.valueOf(v);
		} else {
			mValue = ((Float) mValue) + v;
		}
	}

	public void plus(double v) {
		if (mValue == null) {
			mValue = Double.valueOf(v);
		} else {
			mValue = ((Double) mValue) + v;
		}
	}

	public void plus(Object v) {
		if (v == null) {
			return;
		}
		mValue = v;
	}

	public void plus(AnyObject v) {
		if (v == null) {
			return;
		}
		if (v.mValue instanceof Integer) {
			plus(((Integer) v.mValue).intValue());
		} else if (v.mValue instanceof Short) {
			plus(((Short) v.mValue).shortValue());
		} else if (v.mValue instanceof Long) {
			plus(((Long) v.mValue).longValue());
		} else if (v.mValue instanceof Float) {
			plus(((Float) v.mValue).floatValue());
		} else if (v.mValue instanceof Double) {
			plus(((Double) v.mValue).doubleValue());
		} else {
			plus(v.mValue);
		}
	}

	public void minus(int v) {
		if (mValue == null) {
			mValue = Integer.valueOf(v);
		} else {
			mValue = ((Integer) mValue) - v;
		}
	}

	public void minus(short v) {
		if (mValue == null) {
			mValue = Short.valueOf(v);
		} else {
			mValue = ((Short) mValue) - v;
		}
	}

	public void minus(long v) {
		if (mValue == null) {
			mValue = Long.valueOf(v);
		} else {
			mValue = ((Long) mValue) - v;
		}
	}

	public void minus(float v) {
		if (mValue == null) {
			mValue = Float.valueOf(v);
		} else {
			mValue = ((Float) mValue) - v;
		}
	}

	public void minus(double v) {
		if (mValue == null) {
			mValue = Double.valueOf(v);
		} else {
			mValue = ((Double) mValue) - v;
		}
	}

	public void minus(Object v) {
		if (v == null) {
			return;
		}
		mValue = v;
	}

	public void minus(AnyObject v) {
		if (v == null) {
			return;
		}
		if (v.mValue instanceof Integer) {
			minus(((Integer) v.mValue).intValue());
		} else if (v.mValue instanceof Short) {
			minus(((Short) v.mValue).shortValue());
		} else if (v.mValue instanceof Long) {
			minus(((Long) v.mValue).longValue());
		} else if (v.mValue instanceof Float) {
			minus(((Float) v.mValue).floatValue());
		} else if (v.mValue instanceof Double) {
			minus(((Double) v.mValue).doubleValue());
		} else {
			minus(v.mValue);
		}
	}

	public void divide(int v) {
		if (mValue == null) {
			mValue = Integer.valueOf(v);
		} else {
			mValue = ((Integer) mValue) / v;
		}
	}

	public void divide(short v) {
		if (mValue == null) {
			mValue = Short.valueOf(v);
		} else {
			mValue = ((Short) mValue) / v;
		}
	}

	public void divide(long v) {
		if (mValue == null) {
			mValue = Long.valueOf(v);
		} else {
			mValue = ((Long) mValue) / v;
		}
	}

	public void divide(float v) {
		if (mValue == null) {
			mValue = Float.valueOf(v);
		} else {
			mValue = ((Float) mValue) / v;
		}
	}

	public void divide(double v) {
		if (mValue == null) {
			mValue = Double.valueOf(v);
		} else {
			mValue = ((Double) mValue) / v;
		}
	}

	public void divide(Object v) {
		if (v == null) {
			return;
		}
		mValue = v;
	}

	public void divide(AnyObject v) {
		if (v == null) {
			return;
		}
		if (v.mValue instanceof Integer) {
			divide(((Integer) v.mValue).intValue());
		} else if (v.mValue instanceof Short) {
			divide(((Short) v.mValue).shortValue());
		} else if (v.mValue instanceof Long) {
			divide(((Long) v.mValue).longValue());
		} else if (v.mValue instanceof Float) {
			divide(((Float) v.mValue).floatValue());
		} else if (v.mValue instanceof Double) {
			divide(((Double) v.mValue).doubleValue());
		} else {
			divide(v.mValue);
		}
	}

	public void multipy(int v) {
		if (mValue == null) {
			mValue = Integer.valueOf(v);
		} else {
			mValue = ((Integer) mValue) * v;
		}
	}

	public void multipy(short v) {
		if (mValue == null) {
			mValue = Short.valueOf(v);
		} else {
			mValue = ((Short) mValue) * v;
		}
	}

	public void multipy(long v) {
		if (mValue == null) {
			mValue = Long.valueOf(v);
		} else {
			mValue = ((Long) mValue) * v;
		}
	}

	public void multipy(float v) {
		if (mValue == null) {
			mValue = Float.valueOf(v);
		} else {
			mValue = ((Float) mValue) * v;
		}
	}

	public void multipy(double v) {
		if (mValue == null) {
			mValue = Double.valueOf(v);
		} else {
			mValue = ((Double) mValue) * v;
		}
	}

	public void multipy(Object v) {
		if (v == null) {
			return;
		}
		mValue = v;
	}

	public void multipy(AnyObject v) {
		if (v == null) {
			return;
		}
		if (v.mValue instanceof Integer) {
			multipy(((Integer) v.mValue).intValue());
		} else if (v.mValue instanceof Short) {
			multipy(((Short) v.mValue).shortValue());
		} else if (v.mValue instanceof Long) {
			multipy(((Long) v.mValue).longValue());
		} else if (v.mValue instanceof Float) {
			multipy(((Float) v.mValue).floatValue());
		} else if (v.mValue instanceof Double) {
			multipy(((Double) v.mValue).doubleValue());
		} else {
			multipy(v.mValue);
		}
	}

	public void mod(int v) {
		if (mValue == null) {
			mValue = Integer.valueOf(v);
		} else {
			mValue = ((Integer) mValue) % v;
		}
	}

	public void mod(short v) {
		if (mValue == null) {
			mValue = Short.valueOf(v);
		} else {
			mValue = ((Short) mValue) % v;
		}
	}

	public void mod(long v) {
		if (mValue == null) {
			mValue = Long.valueOf(v);
		} else {
			mValue = ((Long) mValue) % v;
		}
	}

	public void mod(float v) {
		if (mValue == null) {
			mValue = Float.valueOf(v);
		} else {
			mValue = ((Float) mValue) % v;
		}
	}

	public void mod(double v) {
		if (mValue == null) {
			mValue = Double.valueOf(v);
		} else {
			mValue = ((Double) mValue) % v;
		}
	}

	public void mod(Object v) {
		if (v == null) {
			return;
		}
		mValue = v;
	}

	public void mod(AnyObject v) {
		if (v == null) {
			return;
		}
		if (v.mValue instanceof Integer) {
			mod(((Integer) v.mValue).intValue());
		} else if (v.mValue instanceof Short) {
			mod(((Short) v.mValue).shortValue());
		} else if (v.mValue instanceof Long) {
			mod(((Long) v.mValue).longValue());
		} else if (v.mValue instanceof Float) {
			mod(((Float) v.mValue).floatValue());
		} else if (v.mValue instanceof Double) {
			mod(((Double) v.mValue).doubleValue());
		} else {
			mod(v.mValue);
		}
	}

	public Object value() {
		return mValue;
	}

	public static AnyObject valueOf(int v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(short v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(long v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(float v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(double v) {
		return new AnyObject(v);
	}

	public static AnyObject valueOf(Object v) {
		return new AnyObject(v);
	}

}
