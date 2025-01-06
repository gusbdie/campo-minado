package modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;

    //private boolean aberto;
    //private boolean minado;
    //private boolean marcado;

    private List<Campo> vizinhos = new ArrayList<>();

    Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = this.linha != vizinho.linha;
        boolean colunaDiferente = this.coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int valorLinha = Math.abs(this.linha - vizinho.linha);
        int valorColuna = Math.abs(this.coluna - vizinho.coluna);
        int valorGeral = valorColuna + valorLinha;

        if(valorGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        }else if(valorGeral == 2 && diagonal){
            return true;
        }else{
            return false;
        }
    }
}
