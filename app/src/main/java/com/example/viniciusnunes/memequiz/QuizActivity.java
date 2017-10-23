package com.example.viniciusnunes.memequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void btnResponderOnClick(View v){
        RadioGroup rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
        Intent intent = new Intent(this, RespostaActivity.class);
        intent.putExtra("acertou", rgRespostas.getCheckedRadioButtonId() == R.id.rbResposta1);
        startActivity(intent);
    }
}
