package lib;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreAVLExemplo <T> extends ArvoreBinariaExemplo<T>{

    public ArvoreAVLExemplo(Comparator<T> comparator) {
        super(comparator);
    }
    
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...

    private int fatorBalanceamento(No<T> r) {
        return super.altura(r.getFilhoDireita()) - super.altura(r.getFilhoEsquerda());
    }

    private No<T> rotacaoEsquerda(No<T> r) {
        No<T> aux = r.getFilhoDireita();
        r.setFilhoDireita(aux.getFilhoEsquerda());
        aux.setFilhoEsquerda(r);
        return aux;
    }

    private No<T> rotacaoDireita(No<T> r) {
        No<T> aux = r.getFilhoEsquerda();
        r.setFilhoEsquerda(aux.getFilhoDireita());
        aux.setFilhoDireita(r);
        return aux;
    }

    private No<T> rotacaoEsquerdaDireita(No<T> r) {
        r.setFilhoEsquerda(this.rotacaoEsquerda(r.getFilhoEsquerda()));
        return this.rotacaoDireita(r);
    }

    private No<T> rotacaoDireitaEsquerda(No<T> r) {
        r.setFilhoDireita(this.rotacaoDireita(r.getFilhoDireita()));
        return this.rotacaoEsquerda(r);
    }

    private No<T> balancear(No<T> r) {
        if(this.fatorBalanceamento(r) > 1) {
            if(this.fatorBalanceamento(r.getFilhoDireita()) > 0) {
                r = this.rotacaoEsquerda(r);
            }

            else{
                r = this.rotacaoDireitaEsquerda(r);
            }
        }

        else if(this.fatorBalanceamento(r) < -1) {
            if(this.fatorBalanceamento(r.getFilhoEsquerda()) < 0) {
                r = this.rotacaoDireita(r);
            }

            else{
                r = this.rotacaoEsquerdaDireita(r);
            }
        }
        return r;

    }

    public void adicionar(T novoValor) {
        No<T> novoNo = new No<T>(novoValor);

        if(this.raiz == null) {
            this.raiz = novoNo;
            return;
        }
        
        this.raiz = this.adicionar(this.raiz, novoNo);
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
        r = balancear(r);
        return r;
    }
 
    public int altura() {
        return super.altura(this.raiz);
    }

    public T remover(T valor) {
        T x = super.pesquisar(valor);
        this.raiz = remover(this.raiz, valor);
        return x;
    }

    protected No<T> remover(No<T> r, T v) {
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

            r = this.balancear(r);
            return r;
        }

    }


    

}
