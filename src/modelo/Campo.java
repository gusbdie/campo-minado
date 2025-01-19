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
            vizinhos.add(vizinho);
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
            return true;
        }else{
            return false;
        }
    }

    boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado(){
        return marcado;
    }

    void setAberto(boolean aberto){
        this.aberto = aberto;
    }
    void minar(){
        minado = true;
    }

    public boolean isMinado(){
        return minado;
    }

    public boolean isAberto(){
        return aberto;
    }

    public boolean isFechado(){
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;

        return desvendado || protegido;
    }

    long minasNaVizinhanca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString(){
        if(marcado){
            return "x";//colocou bandeira
        }else if(aberto && minado){
            return "*";//tinha bomba
        }else if(aberto && minasNaVizinhanca() > 0){
            return Long.toString(minasNaVizinhanca());
        }else if(aberto){
            return " ";
        }else{
            return "?";
        }
    }
}
