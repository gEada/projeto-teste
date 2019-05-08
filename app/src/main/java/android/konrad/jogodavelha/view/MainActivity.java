package android.konrad.jogodavelha.view;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.konrad.jogodavelha.R;
import android.konrad.jogodavelha.model.Jogo;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

/**
 * Esta classe implementa os componentes da interface e os interage a fim de dar funcionamento
 * ao jogo da velha
 *
 *
 * @author Konrad
 *
 */

public class MainActivity extends AppCompatActivity {

    ImageView[][] img = new ImageView[3][3];
    Jogo jogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Relaciona todos os botões do layout

            img[0][0] = findViewById(R.id.img00);
            img[1][0] = findViewById(R.id.img10);
            img[2][0] = findViewById(R.id.img20);

            img[0][1] = findViewById(R.id.img01);
            img[1][1] = findViewById(R.id.img11);
            img[2][1] = findViewById(R.id.img21);

            img[0][2] = findViewById(R.id.img02);
            img[1][2] = findViewById(R.id.img12);
            img[2][2] = findViewById(R.id.img22);

        Log.i("viewMain", "Image Views relacionadas");

        // Instancia um novo jogo (neste caso será sempre o primeiro)

        jogo = new Jogo();

        img[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.i("viewImg", "00");
            marcarPosicao(0, 0);
            }
        });

        img[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "10");
                marcarPosicao(1, 0);
            }
        });

        img[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "20");
                marcarPosicao(2, 0);
            }
        });

        img[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "01");
                marcarPosicao(0, 1);
            }
        });



        img[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "11");
                marcarPosicao(1, 1);
            }
        });

        img[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "21");
                marcarPosicao(2, 1);
            }
        });

        img[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "02");
                marcarPosicao(0, 2);
            }
        });

        img[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "12");
                marcarPosicao(1, 2);
            }
        });

        img[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("viewImg", "22");
                marcarPosicao(2, 2);
            }
        });

    }

    /**
     * Este método marca a posição no objeto jogo, caso a mesma seja valida,
     * e logo após seta na interface
     * @param x
     * Coordenada X da posição Lembre-se: (X,Y)
     * @param y
     * Coordenada Y da posição, Lembre-se: (X,Y)
     */

    public void marcarPosicao(int x, int y){

        // Checar se a posição indica já esta em uso ou o jogo já está vencido

if(jogo.verificarVencedor() == 0) {
  //  Log.i("viewPosicao", "Vencedor: " + jogo.verificarVencedor());
    if (!jogo.verificarPosicao(x, y)) {
        // Caso sim seta essa posição no jogo e altera a imagem na view
        if (jogo.atribuirPosicao(x, y) == 1) {
            img[x][y].setImageResource(R.drawable.circle);
        } else {
            img[x][y].setImageResource(R.drawable.x);
        }
        if(jogo.verificarVencedor() > 0){
            declararVencedor();
        }
    } else {
        Log.i("viewPosicao", "Posição já marcada");
    }
    Log.i("viewPosicao", "Vencedor: " + jogo.verificarVencedor());
}else{
    declararVencedor();

}

        // Caso a posição já setada nada acontece

        // Caso o jogo já estega ganho aparece o pop-up

        // Logo após isso será verificado o vencedor caso o resultado do vencendor seja != 0

    }

    public void declararVencedor(){

        String mensagem;

        if (jogo.verificarVencedor() == 1){
            mensagem = "Bolinha venceu";
        }else if(jogo.verificarVencedor() == 2){
            mensagem = "Xis venceu";
        }else if(jogo.verificarVencedor() == 3){
            mensagem = "Deu velha";
        }else{
            mensagem = "Erro";
        }

        // Puxa o vencedor do método verificarVencedor() de jogo mostrando no pop-up
        // Logo usar o método resetar() resentando o jogo

       android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
       builder.setMessage(mensagem)
       .setNegativeButton("Recomeçar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               resetar();
               Log.i("viewVencedor", "Jogo finalizado");
           }
       }).setNeutralButton("Sair", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

               Log.i("viewVencedor", "App finalizado");
               finish();
           }
       })
       .create()
       .show();



        //Toast.makeText(getApplicationContext(), "Jogo Encerrado", Toast.LENGTH_LONG).show();
       // resetar();


    }

    public void resetar(){
        jogo = new Jogo();

        // Limpa a imagens
       // img[0][0].setImageResource(R.color.buttonCollor);

        limparImagens();
    }
    /*
    É utilizado para voltar o visual padrão de todos viewImage
     */
    private void limparImagens(){
        for(int y = 0;y <= 2; y++){

            for(int x = 0; x <= 2; x++){
                img[x][y].setImageResource(R.color.buttonCollor);
            }

        }
    }



}
