package com.alio.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Terminal {

	private String mCommand = null;
	
    private Process mProcess = null;

    public interface ITerminalListener {
        void onError(String msg);

        void onLog(String msg);
    }

    private ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();

    private List<ITerminalListener> mTerminalListenerList = new ArrayList<>();

	public Terminal(String cmd) {
		super();
		mCommand = cmd;
	}

    public synchronized void registerTerminalListener(ITerminalListener iTerminalListener) {
        mTerminalListenerList.add(iTerminalListener);
    }

    public synchronized void unregisterTerminalListener(ITerminalListener iTerminalListener) {
        mTerminalListenerList.remove(iTerminalListener);
    }

    private synchronized void notifyLog(String log) {
        for(ITerminalListener iTerminalListener:mTerminalListenerList) {
            iTerminalListener.onLog(log);
        }
    }

    private synchronized void notifyError(String err) {
        for(ITerminalListener iTerminalListener:mTerminalListenerList) {
            iTerminalListener.onError(err);
        }
    }

    public void start() throws IOException {
    	if(mProcess != null) {
    		if(mProcess.isAlive()) {
    			return;
    		} else {
    			mProcess.destroy();
    		}
    	}
        mProcess = Runtime.getRuntime().exec(String.format(Platform.getCommandFormat(), mCommand));
        mCachedThreadPool.execute(new LoggerReader(mProcess.getErrorStream(), new ILogger() {
            @Override
            public void log(String msg) {
                notifyError(msg);
            }
        }));
        mCachedThreadPool.execute(new LoggerReader(mProcess.getInputStream(), new ILogger() {
            @Override
            public void log(String msg) {
                notifyLog(msg);
            }
        }));
    }

    protected OutputStream getOutputStream() {
        return mProcess == null ? null : mProcess.getOutputStream();
    }

    public void stop() {
        mProcess.destroyForcibly();
    }

    private interface ILogger {
        void log(String msg);
    }

    private class LoggerReader implements Runnable {

        private BufferedReader mBufferedReader = null;

        private ILogger mLogger = null;

        private LoggerReader(InputStream inputStream, ILogger iLogger) {
            mBufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            mLogger = iLogger;
        }

        @Override
        public void run() {
            try {
                while(mBufferedReader.ready()) {
                    mLogger.log(mBufferedReader.readLine());
                }
            } catch (IOException e) {
                try {
                    mBufferedReader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
