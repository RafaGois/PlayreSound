package com.example.playarsouds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private SeekBar seekBarVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bach);

        inicializarSeekBar();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void inicializarSeekBar () {
        seekBarVolume = findViewById(R.id.seekBar);

        //configurando audio
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //pega o volume maximo das musicas
        int volMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //configurando o vol maximo do seak bar

        seekBarVolume.setMax(volMax);

        //atual
        seekBarVolume.setProgress(volAtual);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,AudioManager.FLAG_SHOW_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void executeSound (View view) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void pauseMusic (View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopMusic (View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}