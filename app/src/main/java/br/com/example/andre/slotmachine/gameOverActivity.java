package br.com.example.andre.slotmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class gameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void backMenu(View view) {
        Intent intent = new Intent(gameOverActivity.this, MenuActivity.class);
        startActivity(intent);
        gameOverActivity.this.finish();
    }
}
