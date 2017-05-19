package br.com.example.andre.slotmachine;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private Button btPlay, btSobre;
    private TextView tvHeaderMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btPlay = (Button)findViewById(R.id.btPlay);
        btSobre = (Button)findViewById(R.id.btSobre);
        tvHeaderMenu = (TextView)findViewById(R.id.tvHeaderMenu);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/MonsterHunter.ttf");
        btPlay.setTypeface(fonte);
        btSobre.setTypeface(fonte);
        tvHeaderMenu.setTypeface(fonte);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.chest);
        btSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });
    }

    public void play(View v){
        Intent intent = new Intent(MenuActivity.this, UserActivity.class);
        startActivity(intent);
        MenuActivity.this.finish();

    }
    public void about(View v){


    }

}
