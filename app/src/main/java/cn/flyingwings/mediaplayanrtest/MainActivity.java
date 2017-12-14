package cn.flyingwings.mediaplayanrtest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static cn.flyingwings.mediaplayanrtest.QingTingPlayTool.NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String URL = "http://od.open.qingting.fm/m4a/5a29ca337cb89146f0e1101f_8344603_64.m4a?u=669&channelId=138264&programId=8180724&deviceid=A025170100199&clientid=ODEzOWFkYjgtMWY0Zi0xMWU3LTkyM2YtMDAxNjNlMDAyMGFk";
    private TestHandlerThread mTestHandlerThread;
    MyHandler myHandler;
    private static Context mContext;
    private static Handler mTestHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        init();
        myHandler = new MyHandler(this.getMainLooper());
        myHandler.sendEmptyMessageDelayed(0x01, 200);
        Log.d(NAME,"MainActivity ,thisLoop = "+this.getMainLooper()+",,,currentThread = "+Thread.currentThread().toString());
    }

    private void init() {
        initView();
    }

    private void initView() {
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        Button release = (Button) findViewById(R.id.release);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        release.setOnClickListener(this);
    }

    public static void setHandler(Handler handler){
        mTestHandler = handler;
    }

    public static Context getContext(){
        return mContext;
    }

    public class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.d(NAME,"MainActivity MyHandler,thisLoop = "+getLooper()+",,,currentThread = "+Thread.currentThread().toString());
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    mTestHandlerThread = new TestHandlerThread("TestHandlerThread");
                    mTestHandlerThread.start();
                    mTestHandlerThread.waitAppReady();
                    mTestHandlerThread.startThread();
                    Log.d(NAME,"start thread success");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                mTestHandler.sendEmptyMessage(1);
                break;
            case R.id.pause:
                mTestHandler.sendEmptyMessage(2);
                break;
            case R.id.stop:
                mTestHandler.sendEmptyMessage(3);
                break;
            case R.id.release:
                mTestHandler.sendEmptyMessage(4);
                break;
            default:
                break;
        }
    }
}
