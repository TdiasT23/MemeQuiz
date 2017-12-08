package com.example.viniciusnunes.memequiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    ImageView imgPergunta;
    RadioGroup rgRespostas;
    int respostaCerta;
    int pontos;
    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao(R.drawable.morre, "O suspeito acima ficou chateado com as perguntas e falou para os reporteres", R.id.rbResposta1, "A) Morre, diabo!", "B) Quero que todos saiam daqui!", "C) Vão todos tomar no c*", "D) Vão se foderem"));
            add(new Questao(R.drawable.choque, "Aqui por exemplo, temos...", R.id.rbResposta4, "A) Uiiii uii ui", "B) Ai caramba!", "C) Ai meu Deus!", "D) Aiii ai aiii"));
            add(new Questao(R.drawable.gostozinho, "Segundo a pensadora TN Martins, o novinho está", R.id.rbResposta1, "A) Gostozinho no azeite", "B) Mec Nelson nos acessos", "C) Brotando a xota", "D) Aquelas coisas"));
            add(new Questao(R.drawable.poha, "O comentarista informou aos telespectadores que o programa:", R.id.rbResposta3, "A) Ta uma merda", "B) Ta uma droga", "C) Ta uma poha", "D) Ta uma zorra"));
            add(new Questao(R.drawable.burro, "Não cara...", R.id.rbResposta2, "A) Como você é ridículo!", "B) Como você é burro!", "C) Como você é idiota!", "D) Como você é pífio"));
            add(new Questao(R.drawable.pao, "Mamãe, no céu tem pão?", R.id.rbResposta3, "A) E sobreviveu", "B) E viveu", "C) E morreu", "D) E faleceu"));
            add(new Questao(R.drawable.mamilos, "Hoje eu vim falar de um assunto polêmico..", R.id.rbResposta4, "A) Nádegas", "B) Olhos", "C) Mãos", "D) Mamilos"));
            add(new Questao(R.drawable.cremoso, "Complete a frase segundo Gilderlan. E depois.. tem:", R.id.rbResposta1, "A) Xenhenhenhenhem", "B) Nheco Nheco", "C) Nananananana", "D) Um furdunço"));
            add(new Questao(R.drawable.lohany, "Qual é o nome artístico dessa maravilhosa criatura?", R.id.rbResposta2, "A) Lohane Vêkanandre Gisele Gavião da Portela de bala Halls", "B) Lohane Vêkanandre Sthephany Smith Bueno de HA HA HA de Raio Laser bala de Icekiss", "C) Lohane Vêkanandre Bueno de HA HA HA de Raio Laser de bala Mentos", "D) Lohane Vêkanandre Sthephany Smith Brasil de Paitê"));
            add(new Questao(R.drawable.jesus, "Qual o refrão da música que essa missionária estava cantando?", R.id.rbResposta3, "A) Sem Jesus não viverão!", "B) Jesus é bom o tempo todo", "C) O sangue de Jesus tem poder!", "D) O sangue de Jesus é maravilhosooooo!"));


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imgPergunta = (ImageView)findViewById(R.id.imgPergunta);
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
        if (rgRespostas.getCheckedRadioButtonId() != -1) {

            RadioButton rb = (RadioButton)findViewById(rgRespostas.getCheckedRadioButtonId());
            Intent intent = new Intent(this, RespostaActivity.class);
            if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
                intent.putExtra("acertou", true);
                pontos++;
            } else
            intent.putExtra("acertou", false);
            intent.putExtra("pontos", pontos);
            startActivity(intent);

            rgRespostas.clearCheck();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(QuizActivity.this).create();
            alertDialog.setTitle("Ooops!");
            alertDialog.setMessage("Você precisa selecionar uma alternativa :)");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    private void carregarQuestao(){

        embaralharQuestao(questoes);

        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            imgPergunta.setImageResource(q.getImgPergunta());
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

    public void embaralharQuestao(List<Questao> questoes){
        Collections.shuffle(questoes);
    }
}
