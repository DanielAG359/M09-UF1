package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorPolialfabetic(); // Retorna una nueva instancia de XifradorPolialfabetic
    }
}
