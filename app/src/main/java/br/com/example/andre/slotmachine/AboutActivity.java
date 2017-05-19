package br.com.example.andre.slotmachine;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView tvAbout, tvHeaderAbout;
    private Button btVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tvAbout = (TextView)findViewById(R.id.tvAbout);
        tvHeaderAbout = (TextView)findViewById(R.id.tvHeaderAbout);
        btVoltar = (Button)findViewById(R.id.btVoltar);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/MonsterHunter.ttf");
        tvAbout.setTypeface(fonte);
        tvHeaderAbout.setTypeface(fonte);
        btVoltar.setTypeface(fonte);

    }

    public void voltar(View v){
        Intent intent = new Intent(AboutActivity.this, MenuActivity.class);
        startActivity(intent);
        AboutActivity.this.finish();
    }
}
