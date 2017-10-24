package com.example.viniciusnunes.memequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);

        ImageView imgResposta = (ImageView)findViewById(R.id.imgResposta);
        TextView resposta = (TextView)findViewById(R.id.resposta);

        Intent intent = getIntent();
        boolean acertou = intent.getBooleanExtra("acertou", false);
        if(acertou){
            imgResposta.setImageResource(R.drawable.correct);
            resposta.setText("Acertou!");
        }
        else{
            imgResposta.setImageResource(R.drawable.incorrect);
            resposta.setText("Errou!");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        thread.start();
    }
}
