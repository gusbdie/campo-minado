package modelo;

import java.util.ArrayList;
import java.util.List;

import excecao.ExplosaoException;

public class Campo {

    private final int linha;
    private final int coluna;

    private boolean aberto;
    private boolean minado;
    private boolean marcado;

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

    void alternarMarcado(){
        if(!aberto){
            marcado = !marcado;
        }
    }

    boolean abrir(){
        if(!aberto && !marcado){
            aberto = true;

            if(minado){
                throw new ExplosaoException();
            }
            if(vizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }
        }
        return false;
    }

    boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado(){
        return marcado;
    }
}
