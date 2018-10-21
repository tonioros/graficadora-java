package interprete;

public class ExpressionInterpreter {
    private static final String POTENCIA_S = "^";

    public static String transformExpression(CharSequence input) {
        try {
            String expressionFinal = "";
            String expressionStart = input.toString();
            expressionStart = expressionStart.trim().replaceAll(" ", "");
            expressionFinal = expressionStart;
            if (expressionStart.contains(POTENCIA_S)) {
                expressionFinal = replacePotenciaSimbol(expressionStart);
            }
            return expressionFinal;
        } catch (Exception ex) {
            return "Error de ejecucion. Error: " + ex.getMessage();
        }
    }

    private static String replacePotenciaSimbol(String input) {
        String output = "";

        for (int i = 0; i < input.length() - 1; i++) {
            String caracter = input.substring(i, i + 1);
            if (caracter.equals(POTENCIA_S)) {
                String base = input.substring(i - 1, i);
                String exponente = input.substring(i + 1, i + 2);
                if (Integer.parseInt(exponente) > 3) {
                    return "No se puede procesar gracias mayores a grado 3";
                }
                StringBuilder tempCaracteres = new StringBuilder();
                tempCaracteres.append("(");
                for (int a = 0; a < Integer.parseInt(exponente); a++) {
                    tempCaracteres.append(base);
                    tempCaracteres.append("*");
                }
                tempCaracteres.append("1");
                tempCaracteres.append(")");
                output = input.replace(base + caracter + exponente, tempCaracteres.toString());
            }
        }
        return output;
    }

}
