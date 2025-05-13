package aplicativo;
import java.util.Random;


public class Cpf implements INumeroIdentificador{
    private String numeroCpf;

    public Cpf(String numero) {
        if(numeroValido(numero)) {
            this.numeroCpf = numero;
        }

        else{
            this.numeroCpf = "";
        }
    }

    /*passando um objeto Random para o construtor da classe, pode-se usar somente uma instancia da classe Random
    para gerar numeros aleatorios, melhorando a qualidade do codigo (eu acho) 
    */
    public Cpf(Random gerador) {
        String numero = "";
        int i = 0;
        char valores[] = {'0','1','2','3','4','5','6','7','8','9'};

        while(!numeroValido(numero)) {
            numero = "";

            for(i = 0; i < 11; i++) {
                numero += valores[gerador.nextInt(10)];
            }
        }

        this.numeroCpf = numero;
    }

    public boolean formatacaoValida(String numero) {
        int i = 0;
        int j = 0;
        char valores[] = {'0','1','2','3','4','5','6','7','8','9'};

        if(numero.length() != 11 || numero == null) {
            return false;
        }

        for(i = 0; i < numero.length(); i++) {
            j = 0;

            while(j < 10 && numero.charAt(i) != valores[j]) {
                j++;
            }

            /*significa que encontrou um caracter que nao eh numerico*/
            if(j == 10) {
                return false;
            }

        }

        return true;

    }

    public boolean numeroValido(String numero) {
        int i = 0;
        int j = 10;
        int soma = 0;

        if(!formatacaoValida(numero)) {
            return false;
        }
        

        for(i = 0; i < 9; i++) {
            soma += (numero.charAt(i) - '0') * j;
            j--;
        }

        if((soma % 11 == 0) || (soma % 11 == 1)) {
            if(numero.charAt(9) - '0' != 0) {
                return false;
            }
        }

        else if(11 - (soma % 11) != numero.charAt(9) - '0') {
            return false;
        }

        soma = 0;
        j = 10;

        for(i = 1; i < 10; i++) {
            soma += (numero.charAt(i) - '0') * j;
            j--;
        }

        if((soma % 11 == 0) || (soma % 11 == 1)) {
            if(numero.charAt(10) - '0' != 0) {
                return false;
            }
        }

        else if(11 - (soma % 11) != numero.charAt(10) - '0') {
            return false;
        }

        return true;
    }

    public void setNumero(String s) {
        if(numeroValido(s)) {
            this.numeroCpf = s;
        }

        else{
            this.numeroCpf = "";
        }
    }

    public String toString() {
        return this.numeroCpf;
    }

}