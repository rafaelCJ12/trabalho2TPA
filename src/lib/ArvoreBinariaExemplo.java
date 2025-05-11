/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinariaExemplo<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
  
    public ArvoreBinariaExemplo(Comparator<T> comp) {
        comparador = comp;
    }
    
    @Override
    public void adicionar(T novoValor) {
        No<T> novoNo = new No<T>(novoValor);

        if(this.raiz == null) {
            this.raiz = novoNo;
            return;
        }
        
        adicionarI(this.raiz, novoNo);
    }
    
    private void adicionar(No<T> r, No<T> n) {

        if(r != null) {
            if(this.comparador.compare(r.getValor(), n.getValor()) == 1) {
                if(r.getFilhoEsquerda() != null) {
                    adicionar(r.getFilhoEsquerda(), n);

                }
                else{
                    r.setFilhoEsquerda(n);
                }
              
            }

            else{
                if(r.getFilhoDireita() != null) {
                    adicionar(r.getFilhoDireita(), n);

                }
                else{
                    r.setFilhoDireita(n);
                }
            }

        }

    }

    private void adicionarI(No<T> r, No<T> n) {
        Boolean b = true;

        while(b) {
            //verifica se o valor da raiz eh maior que o valor do no a ser inserido
            if(this.comparador.compare(r.getValor(), n.getValor()) == 1) {

                //se o valor do no a ser inserido for menor, verifica se o filho a esquerda da raiz eh null
                if(r.getFilhoEsquerda() == null) {
                    //se o filho a esquerda for null, o no a ser inserido ficara a esquerda da raiz
                    r.setFilhoEsquerda(n);
                    b = false;
                }

                else{
                    //se o filho a esquerda da raiz nao for null, entao a raiz passa a ser esse filho
                    r = r.getFilhoEsquerda();
                }
            }

            else{
                //se o valor do no a ser inserido for menor, verifica se o filho a direita da raiz eh null
                if(r.getFilhoDireita() == null) {
                    //se o filho a direita for null, o no a ser inserido ficara a direita da raiz
                    r.setFilhoDireita(n);
                    b = false;
                }

                else{
                    //se o filho a esquerda da raiz nao for null, entao a raiz passa a ser esse filho
                    r = r.getFilhoDireita();
                }
            }
        }
    }

    @Override
    public T pesquisar(T valor) {
        return pesquisar(this.raiz, valor, this.comparador);
    }


   @Override
    public T pesquisar(T valor, Comparator comparador) {
        return pesquisar(this.raiz, valor, comparador);
    }

    private T pesquisar(No<T> r, T v, Comparator c) {
        if(r == null) {
            return null;
        }

        if(c.compare(r.getValor(), v) == 0) {
            return r.getValor();
        }

        else if(c.compare(r.getValor(), v) == 1) {
            return pesquisar(r.getFilhoEsquerda(), v, c);
        }

        else{
            return pesquisar(r.getFilhoDireita(), v, c);
        }

    }

    private No<T> maiorDireita(No<T> n) {
        No<T> aux = null;

        if(n.getFilhoDireita() != null) {
            return maiorDireita(n.getFilhoDireita());
        }

        else{
            aux = n;

            if(n.getFilhoEsquerda() != null) {
                n = n.getFilhoEsquerda();
            }

            else{
                n = null;
            }

            return aux;
        }

    }

    @Override
    public T remover(T valor) {
        return remover(this.raiz, valor);
    }

    private T remover(No<T> r, T v) {
        No<T> aux = null;
        T x = null;

        //vai entar aqui se o valor a ser removido nao esta na arvore
        if(r == null) {
            return v;
        }

        //verifica se o valor a ser procurado eh menor que a atual raiz
        if(this.comparador.compare(r.getValor(), v) == 1) {
            //se for, chama recursivamente, passando a nova raiz da subarvore da esquerda 
            return remover(r.getFilhoEsquerda(), v);
        }

        //verifica se o valor a ser procurado eh maior que a atual raiz
        else if(this.comparador.compare(r.getValor(), v) == -1) {
            //se for, chama recursivamente, passando a nova raiz da subarvore da direita 
            return remover(r.getFilhoDireita(), v);
        }

        //se o valor a ser removido nao for maior nem menor que a raiz, entao esse valor eh igual a propria raiz
        else{
            aux = r;
            x = r.getValor(); //usa a variavel auxilir para guardar o valor a ser removido

            //verifica se r eh uma folha
            if(r.getFilhoEsquerda() == null && r.getFilhoDireita() == null) {
                r.setValor(null); //o valor a ser guardado pelo no r agora aponta para null
                r = null; //r agora aponta para null
                return x;
            }

            //verifica se r so tem filho na direita
            else if(r.getFilhoEsquerda() == null) {
                //como r so tem filho na direita entao r agora deve apontar para esse filho da direita
                r = r.getFilhoDireita();
                aux.setFilhoDireita(null);
                aux.setValor(null);
                aux = null;
            }

            //verifica se r so tem filho na esquerda
            else if(r.getFilhoDireita() == null) {
                //como r so tem filho na esquerda entao r agora deve apontar para esse filho da esquerda
                r = r.getFilhoEsquerda();
                aux.setFilhoEsquerda(null);;
                aux.setValor(null);
                aux = null;
            }

            //entrara aqui se r tiver dois filhos
            else{
                aux = maiorDireita(r.getFilhoEsquerda());
                aux.setFilhoEsquerda(r.getFilhoEsquerda());
                aux.setFilhoDireita(r.getFilhoDireita());
                r.setFilhoDireita(null);
                r.setFilhoEsquerda(null);
                r.setValor(null);
                aux.setValor(null);
            }

            return x;

        }
    }

    @Override
    public int altura() {
        return altura(this.raiz);

    }

    private int altura(No<T> r) {
        int esq = 0;
        int dir = 0;

        if(r == null) {
            return -1;
        }

        esq = altura(r.getFilhoEsquerda());
        dir = altura(r.getFilhoDireita());

        if(esq > dir) {
            return esq + 1;
        }

        return dir + 1;

    }
       
    
    @Override
    public int quantidadeNos() {
        return quantidadeNos(this.raiz);
        
    }

    private int quantidadeNos(No<T> r) {
        if(r == null) {
            return 0;
        }

        return 1 + quantidadeNos(r.getFilhoEsquerda()) + quantidadeNos(r.getFilhoDireita());

    }

    @Override
    public String caminharEmNivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }
    
    @Override
    public String caminharEmOrdem() {
        String sArv = "";
        return caminharEmOrdem(this.raiz, sArv);    
    }

    private String caminharEmOrdem(No<T> r, String s) {

        if(r != null) {
            s += caminharEmOrdem(r.getFilhoEsquerda(), s) + r.getValor().toString() + ", " + caminharEmOrdem(r.getFilhoDireita(), s);
        }
        return s;

    }

        
}
