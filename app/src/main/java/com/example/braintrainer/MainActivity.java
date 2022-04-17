package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button mulai,button,button1,button2,button3,again;
    TextView sum,timer,correct,points,konfirm;
    ConstraintLayout gameplay;
    LinearLayout landing,papanSkor;
    ArrayList<Integer> jawaban = new ArrayList<Integer>();
    int lokasiJawaban,jawabanSalah,score = 0, nomorSoal = 0;

    public void generateSoal()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sum.setText(Integer.toString(a)+" + "+Integer.toString(b));

        lokasiJawaban = rand.nextInt(4);
        jawaban.clear();
        for(int i = 0; i<4;i++)
        {
            if(i == lokasiJawaban)
            {
                jawaban.add(a+b);
            } else {
                jawabanSalah = rand.nextInt(41);

                while(jawabanSalah == a+b)
                {
                    jawabanSalah = rand.nextInt(41);
                }

                jawaban.add(jawabanSalah);

            }
        }

        button.setText(Integer.toString(jawaban.get(0)));
        button1.setText(Integer.toString(jawaban.get(1)));
        button2.setText(Integer.toString(jawaban.get(2)));
        button3.setText(Integer.toString(jawaban.get(3)));
    }

    public void jawaban(View view)
    {



            if(view.getTag().toString().equals(Integer.toString(lokasiJawaban)))
            {
                konfirm.setText("Correct !");
                score++;
            } else {
                konfirm.setText("Wrong !");
            }

            nomorSoal++;
            points.setText(Integer.toString(score)+" / "+Integer.toString(nomorSoal));
            generateSoal();
    }

    public void countDown()
    {
        new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {

                gameplay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));

                gameplay.setVisibility(View.INVISIBLE);


                papanSkor.setVisibility(View.VISIBLE);
                timer.setText("0s");
                correct.setText("Your Score: "+ Integer.toString(score) + " / " + Integer.toString(nomorSoal));

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         sum = findViewById(R.id.sum);
        gameplay = findViewById(R.id.gameplay);
        landing = findViewById(R.id.landing);
         button = findViewById(R.id.button);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         timer = findViewById(R.id.timer);
        again = findViewById(R.id.again);
        papanSkor = findViewById(R.id.papanSkor);
        mulai = findViewById(R.id.mulai);
        points = findViewById(R.id.point);
        konfirm = findViewById(R.id.konfirm);
        correct = findViewById(R.id.correct);


        generateSoal();

        try {
            mulai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    landing.setVisibility(v.INVISIBLE);
                    gameplay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
                    gameplay.setVisibility(v.VISIBLE);
                    countDown();
                }
            });
        } catch (Exception e)
        {
            gameplay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
            gameplay.setVisibility(View.INVISIBLE);

            papanSkor.setVisibility(View.VISIBLE);
            timer.setText("0s");
            correct.setText("Your Score: "+ Integer.toString(score) + " / " + Integer.toString(nomorSoal));

        }

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                konfirm.setText("");

                    papanSkor.setVisibility(View.INVISIBLE);

                    gameplay.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
                    gameplay.setVisibility(View.VISIBLE);
                    points.setText("0 / 0");
                    correct.setText("");
                    score = 0;
                    nomorSoal = 0;
                    timer.setText("30s");
                    generateSoal();
                    new CountDownTimer(31000, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText(String.valueOf(millisUntilFinished / 1000)+ "s");
                        }

                        @Override
                        public void onFinish() {
                            gameplay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
                            gameplay.setVisibility(View.INVISIBLE);
                            papanSkor.setVisibility(View.VISIBLE);
                            timer.setText("0s");
                            correct.setText("Your Score: "+ Integer.toString(score) + " / " + Integer.toString(nomorSoal));

                        }
                    }.start();




            }
        });



    }
}