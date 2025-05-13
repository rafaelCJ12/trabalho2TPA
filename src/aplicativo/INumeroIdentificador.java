package aplicativo;

public interface INumeroIdentificador{
    public boolean formatacaoValida(String numero);

    public boolean numeroValido(String numero);

    public String toString();
}