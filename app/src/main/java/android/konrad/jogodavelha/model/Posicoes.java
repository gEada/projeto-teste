package android.konrad.jogodavelha.model;

import android.util.Log;

public class Posicoes {

    private int nJogador;
    private boolean jogado;

    public Posicoes() {
    Log.i("Posicoes", "0");
        this.nJogador = 0;
        this.jogado = false;
    }

    public int getnJogador() {
        return nJogador;
    }

    public void setnJogador(int nJogador) {

        this.nJogador =  ((nJogador % 2) == 0)?1:2;
        Log.i("Posicoes", "Setado como: " + this.getnJogador());

    }


    public boolean isJogado() {
        return jogado;
    }
}
