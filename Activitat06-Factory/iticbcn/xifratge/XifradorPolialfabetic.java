package iticbcn.xifratge;

public class XifradorPolialfabetic implements Xifrador {
    private String permutacio;

    public XifradorPolialfabetic() {
        this.permutacio = "QWERTYUIOPASDFGHJKLZXCVBNM"; // Ejemplo de permutación
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            long clauLong = Long.parseLong(clau);
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < msg.length(); i++) {
                char c = msg.charAt(i);
                if (Character.isAlphabetic(c)) {
                    int j = (i + (int) clauLong) % permutacio.length();
                    result.append(permutacio.charAt(j));
                } else {
                    result.append(c); // Mantener caracteres no alfabéticos
                }
            }
            return new TextXifrat(result.toString().getBytes());

        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            long clauLong = Long.parseLong(clau);
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < new String(xifrat.getBytes()).length(); i++) {
                char c = new String(xifrat.getBytes()).charAt(i);
                if (Character.isAlphabetic(c)) {
                    int j = (i - (int) clauLong + permutacio.length()) % permutacio.length();
                    result.append(permutacio.charAt(j));
                } else {
                    result.append(c); // Mantener caracteres no alfabéticos
                }
            }
            return result.toString();

        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }
}
