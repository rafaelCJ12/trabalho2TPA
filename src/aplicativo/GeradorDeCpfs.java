package aplicativo;

import lib.*;
import java.util.Random;

public class GeradorDeCpfs{
    private int quantidade;

    public GeradorDeCpfs(int q) {
        this.quantidade = q;
    }

    public void guardaCpfs(IArvoreBinaria<Cpf> arv) {
        Random gerador = new Random();
        int i = 0;
    
        for(i = 0; i < this.quantidade; i++) {
            arv.adicionar(new Cpf(gerador));
        }
        
    }


}