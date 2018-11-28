package com.example.pcx.vki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView boy_tv,durum_tv,ideal_tv,kilo_tv,yagsizagirlik_tv,vya_tv,vki_tv;
    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private boolean erkekmi = true;
    private double boy = 0.0;
    private int kilo=50;

    private TextWatcher editTextOlayIsleyicisi = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                boy = Double.parseDouble(s.toString())/100.0;
            }catch (NumberFormatException e){
                boy = 0.0;

            }

            Guncelle();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private SeekBar.OnSeekBarChangeListener seekBarOlayIsleyicisi = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
             kilo = 30+progress;
             Guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private RadioGroup.OnCheckedChangeListener radioGroupOlayIsleyicisi = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId==R.id.bay){
                erkekmi = true;
            }
            else if (checkedId==R.id.bayan){
                erkekmi = false;
            }
            Guncelle();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText2);
        boy_tv = (TextView) findViewById(R.id.boy_tv);
        durum_tv = (TextView) findViewById(R.id.durum_tv);
        kilo_tv = (TextView) findViewById(R.id.kilo_tv);
        ideal_tv = (TextView) findViewById(R.id.ideal_tv);

        yagsizagirlik_tv = (TextView) findViewById(R.id.yagsizagirlik_tv);
        vya_tv = (TextView) findViewById(R.id.vya_tv);
        vki_tv = (TextView) findViewById(R.id.vki_tv);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        seekBar =  (SeekBar) findViewById(R.id.seekBar);

        editText.addTextChangedListener(editTextOlayIsleyicisi);
        seekBar.setOnSeekBarChangeListener(seekBarOlayIsleyicisi);
        radioGroup.setOnCheckedChangeListener(radioGroupOlayIsleyicisi);

    }

    private  void  Guncelle(){

        kilo_tv.setText(String.valueOf(kilo)+" kg");
        boy_tv.setText(String.valueOf(boy)+ " mt");
        vki_tv.setText(" ");
        vya_tv.setText(" ");

        if (boy>0.99){

            int ideal_kiloBay = (int) (50+2.3*((boy*100*0.4)-60));
            int ideal_kiloBayan = (int) (45.5+2.3*((boy*100*0.4)-60));

            double vki = kilo/(boy*boy);
            double vya = 0.20247 * Math.pow(boy,0.725) * Math.pow(kilo,0.425);
            int yagsizAgirlik_Bay = (int) ((1.10 * kilo) - (128 * (kilo*kilo)/ Math.pow((100 * boy),2)));
            int yagsizAgirlik_Bayan = (int) ((1.07 * kilo) - (148 * (kilo*kilo)/ Math.pow((100 * boy),2)));

            if(vki < 18.5){
                durum_tv.setBackgroundResource(R.color.durum_zayif);
                durum_tv.setText(R.string.zayif);
            }else if(vki>=18.5 && vki<= 24.9){
                durum_tv.setBackgroundResource(R.color.durum_ideal);
                durum_tv.setText(R.string.ideal);
            }else if(vki>=25 && vki<= 29.9){
                durum_tv.setBackgroundResource(R.color.durum_fazlakilolu);
                durum_tv.setText(R.string.fazlakilolu);
            }else if(vki>=30 && vki<= 34.9){
                durum_tv.setBackgroundResource(R.color.durum_obez);
                durum_tv.setText(R.string.obez1);
            }else if(vki>=35 && vki<= 39.9){
                durum_tv.setBackgroundResource(R.color.durum_obez);
                durum_tv.setText(R.string.obez2);
            }else if(vki >= 40){
                durum_tv.setBackgroundResource(R.color.durum_obez);
                durum_tv.setText(R.string.obez3);
            }


            if (erkekmi){
                //erkek ise
                ideal_tv.setText(String.valueOf(ideal_kiloBay));
                yagsizagirlik_tv.setText(String.valueOf(yagsizAgirlik_Bay));
            }
            else{
                //bayan ise
                ideal_tv.setText(String.valueOf(ideal_kiloBayan));
                yagsizagirlik_tv.setText(String.valueOf(yagsizAgirlik_Bayan));
            }

            double t_vya = (int) (vya*100);
            vya = t_vya / 100;

            double t_vki = (int) (vki*10);
            vki = t_vki / 10;

            vya_tv.setText(String.valueOf(vya));
            vki_tv.setText(String.valueOf(vki));

        }else{
            ideal_tv.setText(" ");
            durum_tv.setText(" ");
            vki_tv.setText(" ");
            vya_tv.setText(" ");
            yagsizagirlik_tv.setText(" ");
        }
    }
}
