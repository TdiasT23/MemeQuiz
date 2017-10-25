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
    RadioGroup rgRespostas;
    int respostaCerta = R.id.rbResposta1;
    int pontos;
    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("Não cara...", R.id.rbResposta2, "Como você é ridículo!", "Como você é burro!", "Como você é idiota!", "Como você é pífio"));
            add(new Questao("Aqui por exemplo, temos...", R.id.rbResposta4, "Uiiii uii ui", "Ai caramba!", "Ai meu Deus!", "Aiii ai aiii"));
            add(new Questao("Segundo a pensadora TN Martins, o novinho está", R.id.rbResposta1, "Gostozinho no azeite", "Mec Nelson nos acessos", "Brotando a xota", "Aquelas coisas"));
            add(new Questao("O comentarista informou aos telespectadores que o programa:", R.id.rbResposta3, "Ta uma merda", "Ta uma droga", "Ta uma poha", "Ta uma zorra"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        pergunta = (TextView)findViewById(R.id.pergunta);
        rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
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
        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);
        }
        else{ //acabaram as questões
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }
}
