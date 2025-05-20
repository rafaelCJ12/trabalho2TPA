/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
        
       this.adicionarI(this.raiz, novoNo);
    }
    
    protected No<T> adicionar(No<T> r, No<T> n) {

        if(r != null) {
            if(this.comparador.compare(n.getValor(), r.getValor()) < 0) {
                if(r.getFilhoEsquerda() == null) {
                    r.setFilhoEsquerda(n);

                }
                else{
                    r.setFilhoEsquerda(this.adicionar(r.getFilhoEsquerda(), n));
                }
              
            }

            else{
                if(r.getFilhoDireita() == null) {
                    r.setFilhoDireita(n);

                }
                else{
                    r.setFilhoDireita(this.adicionar(r.getFilhoDireita(), n));
                }
            }

        }

        return r;

    }

    protected void adicionarI(No<T> r, No<T> n) {
        Boolean b = true;

        while(b) {
            //verifica se o valor da raiz eh maior que o valor do no a ser inserido
            if(this.comparador.compare(r.getValor(), n.getValor()) >= 1) {

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

        else if(c.compare(r.getValor(), v) >= 1) {
            return pesquisar(r.getFilhoEsquerda(), v, c);
        }

        else{
            return pesquisar(r.getFilhoDireita(), v, c);
        }

    }


    @Override
    public T remover(T valor) {
        T x = this.pesquisar(valor);
        this.raiz = remover(this.raiz, valor);
        return x;
    }

    protected No<T> remover( No<T> r, T v) {
        No<T> aux = null;
        if(r == null) {
            return null;
        }

        else{
            if(this.comparador.compare(r.getValor(), v) == 0) {
                if(r.getFilhoEsquerda() == null && r.getFilhoDireita() == null) {
                    return null;
                }

                else{
                    if(r.getFilhoEsquerda() != null && r.getFilhoDireita() != null) {
                        aux = r.getFilhoEsquerda();

                        while(aux.getFilhoDireita() != null) {
                            aux = aux.getFilhoDireita();
                        }

                        r.setValor(aux.getValor());

                        aux.setValor(v);

                        r.setFilhoEsquerda(remover(r.getFilhoEsquerda(), v));
                        return r;
                    }

                    else{
                        if(r.getFilhoEsquerda() != null) {
                            aux = r.getFilhoEsquerda();
                        }

                        else{
                            aux = r.getFilhoDireita();
                        }

                        return aux;
                    }
                }
            }

            else{
                if(this.comparador.compare(r.getValor(), v) >= 1) {
                    r.setFilhoEsquerda(remover(r.getFilhoEsquerda(), v));
                }

                else{
                    r.setFilhoDireita(remover(r.getFilhoDireita(), v));
                }
            }

            return r;
        }

    }

    @Override
    public int altura() {
        return alturaI(this.raiz);

    }

    protected int altura(No<T> r) {
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

    private int alturaI(No<T> r) {
        int altura = 0;
        Queue<No<T>> q = new LinkedList<>();
        No<T> aux = null;
        int i = 0;
        
        if(r == null) {
            return -1;
        }
        
        q.add(r);

        while(true) {
            i = q.size();

            if(i == 0) {
                return altura - 1;
            }

            altura++;

            while (i > 0) {
                aux = q.poll();

                if(aux != null) {
                    if(aux.getFilhoEsquerda() != null) {
                        q.add(aux.getFilhoEsquerda());
                    }

                    if(aux.getFilhoDireita() != null) {
                        q.add(aux.getFilhoDireita());
                    }
                }

                i--;

            }

        }

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
            s = caminharEmOrdem(r.getFilhoEsquerda(), s) + r.getValor().toString() + ", " + caminharEmOrdem(r.getFilhoDireita(), s);
        }
        return s;

    }

        
}
