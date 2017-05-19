package br.com.example.andre.slotmachine;

import android.content.Intent;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private RadioGroup rgSex;
    private Button btPlay;
    private ImageView ivSex;
    private EditText etUserName;
    private Spinner spCoins;
    private int sexSel, value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        rgSex = (RadioGroup)findViewById(R.id.rgSex);
        btPlay = (Button)findViewById(R.id.btPlay);
        ivSex = (ImageView)findViewById(R.id.ivSex);
        etUserName = (EditText)findViewById(R.id.etUserName);
        spCoins = (Spinner)findViewById(R.id.spCoins);

        spCoins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        value = 1;
                        break;
                    case 1:
                        value = 2;
                        break;
                    case 2:
                        value = 3;
                        break;
                    case 3:
                        value = 4;
                        break;
                    case 4:
                        value = 5;
                        break;
                    case 5:
                        value = 6;
                        break;
                    case 6:
                        value = 7;
                        break;
                    case 7:
                        value = 8;
                        break;
                    case 8:
                        value = 9;
                        break;
                    case 9:
                        value = 10;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        int checkedButton = rgSex.getCheckedRadioButtonId();
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch (checkedId){
                    case R.id.rbMasc:
                        ivSex.setImageDrawable(ContextCompat.getDrawable(UserActivity.this,R.drawable.rathalos));
                        sexSel = 0;
                        break;
                    case R.id.rbFem:
                        ivSex.setImageDrawable(ContextCompat.getDrawable(UserActivity.this,R.drawable.rathian));
                        sexSel = 1;
                        break;
                }
            }
        });
    }
    public void goPlay(View view) {
        if (etUserName.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.strError);
            builder.setMessage(R.string.userError);
            builder.setPositiveButton("OK",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else if (rgSex.getCheckedRadioButtonId() == -1 ){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.strError);
            builder.setMessage(R.string.sexSelect);
            builder.setPositiveButton("OK",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            Intent intent = new Intent(UserActivity.this, MachineActivity.class);
            intent.putExtra(getString(R.string.USER), etUserName.getText().toString());
            intent.putExtra(getString(R.string.COINS),value);
            intent.putExtra(getString(R.string.SEX),sexSel);
            //intent.putExtra(getString(R.string.value), (Integer) spCoins.getSelectedItem());
            startActivity(intent);
            UserActivity.this.finish();
        }
    }
}
