package com.example.viniciusnunes.memequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    ImageView imgPergunta;
    int respostaCerta = R.id.rbResposta1;
    RadioGroup rgRespostas;
    int pontos;
    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("Quem descobriu o Brasil?", R.id.rbResposta1, "Pedro Álvares Cabral", "Cristóvão Colombo", "Donald Trump", "Prof. Luiz (LuizTools)"));
            add(new Questao("Qual o melhor site para aprender a programar apps?", R.id.rbResposta2, "http://www.g1.com", "http://www.luiztools.com.br", "http://www.facebook.com", "http://www.twitter.com"));
            add(new Questao("Qual o melhor político do Brasil?", R.id.rbResposta3, "Tiririca", "Eneas Carneiro", "Nenhum presta", "Todos são bons"));
            add(new Questao("Qual a melhor plataforma mobile?", R.id.rbResposta4, "Symbian", "BlackBerry", "iOS", "Android <<<"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        pergunta = (TextView)findViewById(R.id.pergunta);
        rbResposta1 = (RadioButton)findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton)findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton)findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton)findViewById(R.id.rbResposta4);
        carregarQuestao();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarQuestao();
    }

    public void btnResponderOnClick(View v){
        RadioButton rb = (RadioButton)findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;
        }
        else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rb.setChecked(false);
    }

    private void carregarQuestao(){
        Questao q = questoes.remove(0);
        pergunta.setText(q.getPergunta());
        List<String> resposta = q.getRespostas();
        rbResposta1.setText(resposta.get(0));
        rbResposta2.setText(resposta.get(1));
        rbResposta3.setText(resposta.get(2));
        rbResposta4.setText(resposta.get(3));
        respostaCerta = q.getRespostaCerta();
    }
}
