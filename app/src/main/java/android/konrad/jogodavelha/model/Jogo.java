package android.konrad.jogodavelha.model;

import android.util.Log;

public class Jogo {

   private int jogada;
   private Posicoes[][] posicoes;

    public Jogo() {

        this.jogada = 0;
        this.posicoes =  new Posicoes[3][3];

        for(int y = 0;y <= 2; y++){

            for(int x = 0; x <= 2; x++){
                posicoes[x][y] = new Posicoes();
            }

        }


    }

    public int atribuirPosicao(int x, int y){

        if (!verificarPosicao(x, y)){
            posicoes[x][y].setnJogador(this.jogada);
            this.jogada++;
            Log.i("JogoJogada", Integer.toString(this.jogada));
        }
        Log.i("jogoPosicao", x + ""  + y);

        return posicoes[x][y].getnJogador();
    }


    public boolean verificarPosicao(int x, int y){

       if(posicoes[x][y].getnJogador() > 0) {
           Log.i("jogoPosicao", "Já colocado: " + x + " "+ y);
           return true;
       }else{
         //  verificarVencedor();
           return false;
       }
    }

    /*
           Posições de vitória
           0,0 ; 1,0 ; 2;0

           0,1 ; 1,1 ; 2,1

           0,2 ; 1,2 ; 2,2

           --------------

           0,0 ; 0,1 ; 0,2

           1,0 ; 1,1 ; 1,2

           2,0 ; 2,1 ; 2,2

           -----------------

           0,0 ; 1,1 ; 2,2;

           2,0 ; 1,1 ; 0,2

            */

    public int verificarVencedor() {

        Log.i("verificarVencedor", "Horizontal: " + verificarHorizontal());
        Log.i("verificarVencedor", "Vertical: " + verificarVertical());
        Log.i("verificarVencedor", "Diagonal: " + verificarDiagonal());
        Log.i("Verificar Vencedor", "Velha: " + verificarVelha());


        //verificarHorizontal();
        //verificarVertical();
        //verificiarDiagonal();

        // CASO ALGUM SEJA MAIOR QUE ZERO O RETORNO RETORNARA O VENCEDOR



    if (!verificarVelha()) {
        return verificarHorizontal() + verificarDiagonal() + verificarVertical();
    }else{
        return 3;
    }
    }


    private int verificarHorizontal(){

        int jogador = 1;
        int vencedor = 0;
        boolean vencedorEncontrado = false;


        while (jogador <= 2){
            int sum;

            for(int y = 0; y <= 2; y++){
            sum = 0;
                for(int x = 0; x <= 2; x++){
                    if (posicoes[x][y].getnJogador() == jogador){
                        sum++;
                    }
                }
                if (sum == 3){
                    vencedor = jogador;
                    vencedorEncontrado = true;
                    break;
                }

            }

            if (vencedorEncontrado){
                break;
            }

            jogador++;
        }


        return vencedor;
    }

    /**
     * Verifica se de acordo com as posições marcadas existe alguém vitorioso pelo menos nas verticais
     * @return
     * Retorna zero caso não há vencedor, um caso encontre o jogador um (bolinha) como vencedor
     * retorna dois caso encotre o jogador dois (xis) como vencedor
     */

    private int verificarVertical(){

        int jogador = 1;
        int vencedor = 0;
        boolean vencedorEncontrado = false;


        while (jogador <= 2){
            int sum;

            for(int x = 0; x <= 2; x++){
                sum = 0;
                for(int y = 0; y <= 2; y++){
                    if (posicoes[x][y].getnJogador() == jogador){
                        sum++;
                    }
                }
                if (sum == 3){
                    vencedor = jogador;
                    vencedorEncontrado = true;
                    break;
                }

            }

            if (vencedorEncontrado){
                break;
            }

            jogador++;
        }


        return vencedor;



    }

    private int verificarDiagonal(){

        int jogador = 1;
        int vencedor = 0;

        while(jogador <= 2 ){

            if (((posicoes[0][0].getnJogador() == jogador)
                    && (posicoes[1][1].getnJogador() == jogador)
                    && (posicoes[2][2].getnJogador() == jogador))
                    || ((posicoes[2][0].getnJogador() == jogador)
                    && (posicoes[1][1].getnJogador() == jogador)
                    && (posicoes[0][2].getnJogador() == jogador))){

                vencedor = jogador;
                break;
            }

            jogador++;
        }
         return vencedor;
    }

    /**
     * Verifica se de acordo com as posições marcadas o resultado a velha
     * @return
     * true -> Deu Velha      false -> Não deu Velha
     */

    private boolean verificarVelha(){
        if (this.jogada >= 9){
            return true;
        }else{
            return false;
        }
    }

}
