package com.example.android.sqliteweather;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.sqliteweather.data.VideoConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity {
    private static final String TAG = "VideoActivity";

    YouTubePlayerView mYouTubePlayerView;
    Button play_button;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Log.d(TAG, "onCreate: Starting");
        play_button = (Button) findViewById(R.id.play_button);
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_player);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Done Intializing Video Player");
                youTubePlayer.loadVideo("1VIZ89FEjYI");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Failed Intializing Video Player");
            }
        };
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Intializing Video Player");
                mYouTubePlayerView.initialize(VideoConfig.getApiKey(), mOnInitializedListener);
            }
        });

//        Log.d(TAG, "onCreate: Starting...");
//        play_button = (Button) findViewById(R.id.play_button);
//        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_player);
//
//        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                Log.d(TAG, "onClick: Done initializing");
//                youTubePlayer.loadVideo("W4hTJybfU7s");
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Log.d(TAG, "onClick: Failed to initializing");
//
//            }
//        };
//
//        play_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: Inializing Video Player");
//                mYouTubePlayerView.initialize(VideoConfig.getApiKey(), mOnInitializedListener);
//            }
//        });
    }
}
