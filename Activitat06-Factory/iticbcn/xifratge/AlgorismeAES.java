package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorAES(); // Retorna una nueva instancia de XifradorAES
    }
}
