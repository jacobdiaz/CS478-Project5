package course.examples.Services.KeyClient;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MusicService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private MediaPlayer mPlayer;
    private int mStartID;
    private Notification notification ;
    private static String CHANNEL_ID = "Music player style" ;

    int songIndex; //Dictates which song is to be played

    @Override
    public void onCreate() {
        super.onCreate();

        this.createNotificationChannel();
        final Intent notificationIntent = new Intent(getApplicationContext(),
                MusicClient.class);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0) ;
        notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("Music Playing")
                .setContentText("Click to Access Music Player")
                .setTicker("Music is playing!")
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_launcher, "Show service", pendingIntent)
                .build();


//        mPlayer.start() ;

        if (null != mPlayer) {

            mPlayer.setLooping(false);

            // Stop Service when music has finished playing
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // stop Service if it was started with this ID
                    // Otherwise let other start commands proceed
                    stopSelf(mStartID);
                }
            });


        }

        // Put this Service in a foreground state, so it won't
        // readily be killed by the system
        startForeground(NOTIFICATION_ID, notification);

    }

    private void createNotificationChannel() {
        CharSequence name = "Music player notification";
        String description = "The channel for music player notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.setDescription(description);
        }


        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        // Get arguments passed into service
        Bundle extras = intent.getExtras();
        if(extras == null){
            Log.d("onStartCommand","No Extras passed to service");
        } else{
            songIndex = (int) extras.get("songIndex");

            // Set songs to play
            switch (songIndex){
                case 0:
                    mPlayer = MediaPlayer.create(this, R.raw.ifollowrivers);
                    break;
                case 1:
                    mPlayer = MediaPlayer.create(this, R.raw.thundercat);
                    break;
                case 2:
                    mPlayer = MediaPlayer.create(this, R.raw.blueworld);
                    break;
                case 3:
                    mPlayer = MediaPlayer.create(this, R.raw.americanboy);
                    break;
                case 4:
                    mPlayer = MediaPlayer.create(this, R.raw.devuelveme);
                    break;
            }

            mPlayer.start() ;

                Log.d("onStartCommand","Param passed was "+songIndex);
        }

        if (null != mPlayer) {

            // ID for this start command
            mStartID = startid;

            if (mPlayer.isPlaying()) {

                // Rewind to beginning of song
                mPlayer.seekTo(0);
            } else {
                // Start playing song
                mPlayer.start();
            }
        }

        // Don't automatically restart this Service if it is killed
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        if (null != mPlayer) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}