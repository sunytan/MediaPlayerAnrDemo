package cn.flyingwings.mediaplayanrtest;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import static cn.flyingwings.mediaplayanrtest.QingTingPlayTool.NAME;

/**
 * Created by edz on 2017/12/13.
 */

public class TestHandlerThread extends HandlerThread {

    // synchronized 锁对象
    private final byte[] bytelock = new byte[0];
    private boolean onLooperHasrun = false;

    private TestHandler mTeshHandler;
    private Handler myHandler;

    public TestHandlerThread(String name) {
        super(name);
    }


    public void startThread(){
        myHandler = new Handler(this.getLooper(), new TestCallBack());
        MainActivity.setHandler(myHandler);
        mTeshHandler = new TestHandler();
    }


    /**
     * 覆盖HandlerThread 方法
     */
    @Override
    protected final void onLooperPrepared() {
        super.onLooperPrepared();
        synchronized (bytelock) {
            bytelock.notify();
            onLooperHasrun = true;
        }
    }

    /**
     * 等待HandlerThread中的Looper线程初始化完成
     */
    public void waitAppReady() {
        synchronized (bytelock) {
            try {
                if (onLooperHasrun) {
                    return;
                }
                bytelock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class TestCallBack implements Handler.Callback{

        @Override
        public boolean handleMessage(Message msg) {
            Log.d(NAME,"TestCallBack ,thisLoop = "+getLooper()+",,,,Thread id = "+getThreadId()+",,,currentThread = "+Thread.currentThread().toString());
            Log.d(NAME,"msg.what = "+msg.what);
            switch (msg.what) {
                case 1:
                    mTeshHandler.sendEmptyMessage(1);
                    break;

                case 2:
                    mTeshHandler.sendEmptyMessage(2);
                    break;

                case 3:
                    mTeshHandler.sendEmptyMessage(3);
                    break;

                case 4:
                    mTeshHandler.sendEmptyMessage(4);
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
