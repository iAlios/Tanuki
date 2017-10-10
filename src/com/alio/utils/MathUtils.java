package com.alio.utils;

public class MathUtils {

    public static final double angle(int x1, int y1, int x2, int y2, int x3, int y3) {
        int vx1 = x2 - x1;
        int vy1 = y2 - y1;
        int vx2 = x2 - x3;
        int vy2 = y2 - y3;
        double productValue = (vx1 * vx2) + (vy1 * vy2);
        double vaMod = Math.sqrt(vx1 * vx1 + vy1 * vy1);
        double vbMod = Math.sqrt(vx2 * vx2 + vy2 * vy2);
        double cosValue = productValue / (vaMod * vbMod);
        return Math.acos(cosValue) * 180 / Math.PI;
    }
    
    public static final double angle(int x1, int y1, int x2, int y2) {
    	double result = angle(x2 - x1, y2 - y1, 0, 0, 1, 0);
    	if(x1 < x2) {
    		if(y1 <= y2) {
    			return result;
    		} else {
    	    	return -result;
    		}
    	} else {
    		if(y1 <= y2) {
    	    	return result;
    		} else {
    	    	return -result;
    		}
    	}
    }

	public static void main(String[] args) {
		System.out.println(angle(0, -2, 0, 0, 2, 0));
		System.out.println(angle(2, 0, 0, 0));
	}

}
