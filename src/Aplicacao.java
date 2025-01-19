import modelo.Tabuleiro;
import visao.TabuleiroConsole;

public class Aplicacao {
//criei essa classe para ir testando a implementação  
//da visão do jogo no console
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 5);

        new TabuleiroConsole(tabuleiro);

    }
}
