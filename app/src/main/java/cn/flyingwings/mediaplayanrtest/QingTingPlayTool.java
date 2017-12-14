package cn.flyingwings.mediaplayanrtest;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;


/**
 * Created by xiepengtao on 2017/7/26.
 */

public class QingTingPlayTool implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private CallBack mCallBack;
    public final static String NAME = "tanyang";

    public static QingTingPlayTool getIntent() {
        return Sington.intent;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mCallBack != null) {
            mCallBack.onCompletion();
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        if (mCallBack != null) {
            mCallBack.mediaError();
        }
        return false;
    }

    static private class Sington {
        private static QingTingPlayTool intent = new QingTingPlayTool();
    }

    public MediaPlayer play(Context context) {
        MediaPlayer mediaPlayer = null;
        try {
            //mediaPlayer = MediaPlayer.create(context, );
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setDataSource(context,Uri.parse(MainActivity.URL));
            Log.d(NAME,"setDataSource success");
            mediaPlayer.prepare();
            Log.d(NAME,"prepare success");
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.start();
            Log.d(NAME,"start success");
        } catch (Exception e) {
            e.printStackTrace();
            if (mCallBack != null) {
                mCallBack.mediaError();
            }
        }
        return mediaPlayer;
    }



    public interface CallBack {
        void onCompletion();
        void mediaError();
    }

    public void mediaPlayListener(CallBack callBack) {
        mCallBack = callBack;
    }
}
