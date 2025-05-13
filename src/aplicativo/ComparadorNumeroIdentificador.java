package aplicativo;

import java.util.Comparator;

public class ComparadorNumeroIdentificador implements Comparator<Cpf>{
    public int compare(Cpf o1, Cpf o2) {
        return o1.toString().compareTo(o2.toString());
    }
}