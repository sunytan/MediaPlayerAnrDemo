package cn.flyingwings.mediaplayanrtest;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import static cn.flyingwings.mediaplayanrtest.QingTingPlayTool.NAME;

/**
 * Created by edz on 2017/12/13.
 */

public class TestHandler extends Handler {

    MediaPlayer mediaPlayer;

    public TestHandler(){
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Log.d(NAME,"TestHandler ,thisLoop = "+getLooper()+",,,currentThread = "+Thread.currentThread().toString());
        Log.d(NAME,"msg.what = "+msg.what);
        switch (msg.what){
            case 1:
                mediaPlayer = QingTingPlayTool.getIntent().play(MainActivity.getContext());
                break;
            case 2:
                try {
                    mediaPlayer.pause();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    mediaPlayer.stop();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    mediaPlayer.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:

                break;
        }
    }
}
