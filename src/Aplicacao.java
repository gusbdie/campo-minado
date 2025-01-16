import modelo.Tabuleiro;

public class Aplicacao {
//criei essa classe para ir testando a implementação  
//da visão do jogo no console
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
    
        tabuleiro.abrir(3,3);
        tabuleiro.abrir(4,4);
        tabuleiro.alternarMarcado(4, 5);

        System.out.println(tabuleiro); 
        
    }
}
