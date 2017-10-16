package com.alio.utils.test;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.alio.utils.Terminal;
import com.alio.utils.Terminal.ITerminalListener;

public class ShellTest {

    private final static Lock mLocker = new ReentrantLock();

	@Test
	public void testShell() throws IOException {
		Terminal shell = new Terminal("D:\\development\\apache-tomcat-8.5.15\\bin\\catalina.bat start");
		shell.registerTerminalListener(new ITerminalListener() {
			
			@Override
			public void onLog(String msg) {
				System.out.println("==---onLog---==");
				System.out.println(msg);
                synchronized (mLocker) {
                    mLocker.notifyAll();
                }
			}
			
			@Override
			public void onError(String msg) {
				System.out.println("==---onError---==");
				System.out.println(msg);
                synchronized (mLocker) {
                    mLocker.notifyAll();
                }				
			}
		});
        shell.start();
        synchronized (mLocker) {
            try {
                mLocker.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		System.out.println("====");
	}
	
}
