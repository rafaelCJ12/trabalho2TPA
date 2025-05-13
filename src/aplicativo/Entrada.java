package aplicativo;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.util.Scanner;

class Entrada {
    Scanner input;

    /**
     * Construtor da classe InputOutput
     */
    Entrada() {
        this.input = new Scanner(System.in);
    }

    /**
     * Faz a leitura de uma linha inteira
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    int lerInteiro(String msg) throws NumberFormatException{
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um double
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    double lerDouble(String msg) throws NumberFormatException{
        // Imprime uma mensagem ao usuário, lê uma linha contendo um double e retorna este double
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }


}