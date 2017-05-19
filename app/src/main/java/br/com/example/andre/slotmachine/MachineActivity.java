package br.com.example.andre.slotmachine;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MachineActivity extends AppCompatActivity {

    private ImageView ivSlot1, ivSlot2, ivSlot3, ivSexGame;
    private Roda slot1, slot2, slot3;
    private Button btPlayMachine;
    private TextView tvUserName, tvCurCoins;
    public int value;
    public int sex;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        ivSlot1 = (ImageView)findViewById(R.id.ivSlot1);
        ivSlot2 = (ImageView)findViewById(R.id.ivSlot2);
        ivSlot3 = (ImageView)findViewById(R.id.ivSlot3);
        ivSexGame = (ImageView)findViewById(R.id.ivSexGame);
        tvUserName = (TextView)findViewById(R.id.tvUserName);
        btPlayMachine = (Button)findViewById(R.id.btPlayMachine);
        tvCurCoins = (TextView)findViewById(R.id.tvCurCoins);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/MonsterHunter.ttf");
        btPlayMachine.setTypeface(fonte);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.questdepart);
        if (getIntent() != null){
            tvUserName.setText(getIntent().getStringExtra(getString(R.string.USER)));
            value = getIntent().getIntExtra(getString(R.string.COINS),0);
            tvCurCoins.setText(getString(R.string.fichas) + value);
            sex = getIntent().getIntExtra(getString(R.string.SEX),0);
            if (sex  == 0){
                ivSexGame.setImageDrawable(ContextCompat.getDrawable(MachineActivity.this,R.drawable.rathalos));
            }else {ivSexGame.setImageDrawable(ContextCompat.getDrawable(MachineActivity.this,R.drawable.rathian));}
        }
        btPlayMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value > 0) {
                    value -= 1;
                    tvCurCoins.setText(getString(R.string.fichas) + value);
                    mediaPlayer.start();
                    runSlot1();
                    runSlot2();
                    runSlot3();
                    btPlayMachine.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            slot1.stopSpin();
                            slot2.stopSpin();
                            slot3.stopSpin();
                            calcResults();
                            btPlayMachine.setEnabled(true);
                        }
                    },3000);
                }
            }
        });
    }


    private void calcResults(){
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.won);
        if (slot1.curInd == slot2.curInd && slot2.curInd == slot3.curInd){
            value += 2;
            tvCurCoins.setText(getString(R.string.fichas) + value);
            mediaPlayer1.start();
        }else if (slot1.curInd == slot2.curInd || slot1.curInd == slot3.curInd ||
                slot2.curInd == slot3.curInd){
            value += 1;
            tvCurCoins.setText(getString(R.string.fichas) + value);
            mediaPlayer1.start();
        }
        if (value == 0) {
            Intent intent = new Intent(MachineActivity.this, gameOverActivity.class);
            startActivity(intent);
            MachineActivity.this.finish();
        }
    }

    private void runSlot1(){
        slot1 = new Roda(new Roda.RodaListener() {
            @Override
            public void NewImage(final int ResourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot1.setImageResource(ResourceID);
                    }
                });
            }
        },200, randomLong(0,200));
        slot1.start();
    }
    private void runSlot2(){
        slot2 = new Roda(new Roda.RodaListener() {
            @Override
            public void NewImage(final int ResourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot2.setImageResource(ResourceID);
                    }
                });
            }
        },200, randomLong(150,200));
        slot2.start();
    }
    private void runSlot3(){
        slot3 = new Roda(new Roda.RodaListener() {
            @Override
            public void NewImage(final int ResourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot3.setImageResource(ResourceID);
                    }
                });
            }
        },200, randomLong(300,100));
        slot3.start();
    }
}
