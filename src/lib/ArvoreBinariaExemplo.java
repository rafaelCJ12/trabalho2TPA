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


    @Override
    public T remover(T valor) {
        return remover(null, this.raiz, valor, 0);
    }

    private T remover(No<T> pai, No<T> r, T v, int tipoR) {
        No<T> aux = null;
        T x = null;

        //vai entar aqui se o valor a ser removido nao esta na arvore
        if(r == null) {
            return null;
        }

        //verifica se o valor a ser procurado eh menor que a atual raiz
        else if(this.comparador.compare(r.getValor(), v) == 1) {
            //se for, chama recursivamente, passando a nova raiz da subarvore da esquerda 
            return remover(r, r.getFilhoEsquerda(), v, -1);
        }

        //verifica se o valor a ser procurado eh maior que a atual raiz
        else if(this.comparador.compare(r.getValor(), v) == -1) {
            //se for, chama recursivamente, passando a nova raiz da subarvore da direita 
            return remover(r, r.getFilhoDireita(), v, 1);
        }

        //se o valor a ser removido nao for maior nem menor que a raiz, entao esse valor eh igual a propria raiz
        else{
            x = r.getValor(); //usa a variavel auxilir para guardar o valor a ser removido

            //verifica se r eh uma folha
            if(r.getFilhoEsquerda() == null && r.getFilhoDireita() == null) {
                //verifica se r eh um filho a esquerda
                if(tipoR == -1) {
                    //se r for um filho a esquerda, entao o no esquerdo do pai apontara para null
                    pai.setFilhoEsquerda(null);
                }

                else if(tipoR == 1) {
                    //se r for um filho a direita, o no direito do pai apontara para null
                    pai.setFilhoDireita(null);
                }
            }

            //verifica se r so tem filho na direita
            else if(r.getFilhoEsquerda() == null) {
                //verifica se r nao eh a raiz da arvore
                if(pai != null) {
                    //se r nao for a raiz da arvore, entao o no direito do pai sera o no direito de r
                    pai.setFilhoDireita(r.getFilhoDireita());
                    r = null;
                }

                //se o pai for null, entao r eh a raiz da arvore
                else{
                    //como r eh a raiz da arvore entao a raiz sera o filho a direita de r
                    this.raiz = r.getFilhoDireita();
                    r = null;
                }
            }

            //verifica se r so tem filho na esquerda
            else if(r.getFilhoDireita() == null) {
                //verifica se r nao eh a raiz da arvore
                if(pai != null) {
                    //se r nao for a raiz da arvore, entao o no esquerdo do pai sera o no esquerdo de r
                    pai.setFilhoEsquerda(r.getFilhoEsquerda());
                    r = null;
                }

                //se o pai for null, entao r eh a raiz da arvore
                else{
                    //como r eh a raiz da arvore entao a raiz sera o filho a esquerda de r
                    this.raiz = r.getFilhoEsquerda();
                }
            }

            //entrara aqui se r tiver dois filhos
            else{
                //aqui comeca a busca pelo maior elemento da subarvore da esquerda
                aux = r.getFilhoEsquerda();

                while(aux.getFilhoDireita() != null) {
                    aux = aux.getFilhoDireita();
                }
                //fim da busca
                
                r.setValor(aux.getValor()); //r tera como valor o maior elemento da subarvore da esquerda
                aux.setValor(v); //o valor de aux agora sera o elemento a ser removido, que agora estara em um no folha

                //o filho a esquerda de r sera o maior elemento da arvore,
                r.getFilhoEsquerda().setValor(remover(r, r.getFilhoEsquerda(), v, -1)); 
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
