package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int rot = Integer.parseInt(clau);
            if (rot < 0 || rot > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }

            StringBuilder result = new StringBuilder();
            for (char c : msg.toCharArray()) {
                if (Character.isAlphabetic(c)) {
                    result.append((char) ((c + rot - (Character.isLowerCase(c) ? 'a' : 'A')) % 26 + (Character.isLowerCase(c) ? 'a' : 'A')));
                } else {
                    result.append(c); // Mantener caracteres no alfabéticos
                }
            }
            return new TextXifrat(result.toString().getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            int rot = Integer.parseInt(clau);
            if (rot < 0 || rot > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }

            StringBuilder result = new StringBuilder();
            for (char c : new String(xifrat.getBytes()).toCharArray()) {
                if (Character.isAlphabetic(c)) {
                    result.append((char) ((c - rot - (Character.isLowerCase(c) ? 'a' : 'A')) % 26 + (Character.isLowerCase(c) ? 'a' : 'A')));
                } else {
                    result.append(c); // Mantener caracteres no alfabéticos
                }
            }
            return result.toString();
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }
}
