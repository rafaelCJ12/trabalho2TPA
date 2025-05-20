package aplicativo;

import lib.ArvoreAVLExemplo;
import lib.ArvoreBinariaExemplo;
import app.*;
import lib.IArvoreBinaria;

import java.util.Comparator;

public class Main {

    private static void buscaCpf(Cpf c, IArvoreBinaria<Cpf> arv, Entrada e) {
        String s;

        try {
            s = e.lerLinha("Digite o cpf a ser buscado: ");
            c.setNumero(s);
            c = arv.pesquisar(c);

            
            if(c == null) {
                System.out.println("Cpf nao encontrado!");
            }

            else{
                System.out.println("Cpf encontrado: " + c.toString());
            }
            
        } 
        
        catch (Exception x) {
            // TODO: handle exception
        }

    }

    private static void removeCpfArvore(Cpf c, IArvoreBinaria<Cpf> arv, Entrada e) {
        String s;

        try {
            s = e.lerLinha("Digite o cpf a ser removido: ");
            c.setNumero(s);
            c = arv.remover(c);

            if(c == null) {
                System.out.println("Cpf nao encontrado!");
            }

            else{
                System.out.println("Cpf removido: " + c.toString());
            }
            
        } 
        
        catch (Exception x) {
            // TODO: handle exception
        }

    }

    private static void adicionaCpf(IArvoreBinaria<Cpf> arv, Entrada e) {
        String s = "";
        Cpf c = new Cpf(s);

        try {
            s = e.lerLinha("Digite o numero de cpf a ser inserido: ");
            c.setNumero(s);
            
            if(c.numeroValido(s)) {
                arv.adicionar(c);
                System.out.println("Cpf adicionado.");
            }

            else{
                System.out.println("Numero de cpf invalido. Insercao nao concluida.");
            }
  
        }
        catch(Exception x) {

        }
        
    }

    private static void menu(Cpf c, IArvoreBinaria<Cpf> arv, Entrada e) {
        int opcao = -1;

        while(opcao != 0) {
            System.out.println("\n\nOla! Escolha uma opcao:\n1) Buscar cpf.\n2) Remover cpf da arvore.\n3) Adicionar cpf a arvore.\n"+
            "4) Imprimir os cpf's\n0) Sair");

            try{
                opcao = e.lerInteiro("Escolha uma opcao: ");
                switch (opcao) {
                    case 1:
                        buscaCpf(c, arv, e);
                        break;
                    case 2:
                        removeCpfArvore(c, arv, e);
                        break;
                    case 3:
                        adicionaCpf(arv, e);
                        break;
                    case 4:
                        System.out.println(arv.caminharEmOrdem());
                        System.out.println("Altura da arvore: "+arv.altura());
                        break;
                
                    default:
                        break;
                }
            }

            catch(NumberFormatException x) {
                System.out.println("Opcao invalida!");
            }

        }



    }
    
    public static void main(String[] args) {
        Entrada e = new Entrada();
        ComparadorAlunoPorMatricula c = new ComparadorAlunoPorMatricula();
        IArvoreBinaria<Aluno> arv = new ArvoreBinariaExemplo<Aluno>(c);
        GeradorDeArvores g = new GeradorDeArvores();

        g.geraArvorePerfeitamenteBalanceada(1, 8, arv);

        System.out.println(arv.caminharEmOrdem());
       
        
    }

}