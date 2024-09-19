package coco.secret.fracciones;

public class Fracciones {

   public static int[] parseFraction(String text) {
        text = text.trim().toLowerCase();
        String[] words = text.split(" ");

        // Parse numerator
        Mapa.NumberParseResult numeratorResult = Mapa.parseNumber(words, 0);
        if (numeratorResult == null) {
            throw new IllegalArgumentException("Numerador no válido en: " + text);
        }

        // Parse denominator
        int index = numeratorResult.wordsConsumed;

        // Build denominator text from remaining words
        StringBuilder denominatorBuilder = new StringBuilder();
        for (int i = index; i < words.length; i++) {
            denominatorBuilder.append(words[i]).append(" ");
        }

        String denominatorText = denominatorBuilder.toString().trim();
        int denominator = Mapa.getDenominador(denominatorText);

        if (denominator == -1) {
            throw new IllegalArgumentException("Denominador no válido en: " + denominatorText);
        }

        // **Agregamos esta verificación para el denominador cero**
        if (denominator == 0) {
            throw new IllegalArgumentException("División por cero no permitida");
        }

        return new int[]{numeratorResult.number, denominator};
    }






    private static int[] addFractions(int[] frac1, int[] frac2) {
        int numerator = frac1[0] * frac2[1] + frac2[0] * frac1[1];
        int denominator = frac1[1] * frac2[1];
        return new int[]{numerator, denominator};
    }

    private static int[] subtractFractions(int[] frac1, int[] frac2) {
        int numerator = frac1[0] * frac2[1] - frac2[0] * frac1[1];
        int denominator = frac1[1] * frac2[1];
        return new int[]{numerator, denominator};
    }

    private static int[] multiplyFractions(int[] frac1, int[] frac2) {
        int numerator = frac1[0] * frac2[0];
        int denominator = frac1[1] * frac2[1];
        return new int[]{numerator, denominator};
    }

    private static int[] divideFractions(int[] frac1, int[] frac2) {
        int numerator = frac1[0] * frac2[1];
        int denominator = frac1[1] * frac2[0];

        if (denominator == 0) {
            throw new IllegalArgumentException("División por cero no permitida");
        }

        return new int[]{numerator, denominator};
    }

    static String fractionToText(int[] fraction) {
    int numerator = fraction[0];
    int denominator = fraction[1];

    if (denominator == 1) {
        // Es un número entero
        String numeratorText = Mapa.numeroATexto(Math.abs(numerator));
        if (numerator < 0) {
            numeratorText = "menos " + numeratorText;
        }
        return numeratorText;
    }

    String numeratorText = Mapa.numeroATexto(Math.abs(numerator));
    String denominatorText;

    if (denominator <= 10) {
        String denominatorSingular = Mapa.getDenominadorSingular(denominator);
        if (Math.abs(numerator) > 1) {
            denominatorText = Mapa.getDenominadorPlural(denominatorSingular);
        } else {
            denominatorText = denominatorSingular;
        }
    } else {
        String numberText = Mapa.numeroATexto(denominator);
        if (Math.abs(numerator) > 1) {
            denominatorText = numberText + "avos";
        } else {
            denominatorText = numberText + "avo";
        }
    }

    if (numerator == 1) {
        numeratorText = "un";
    } else if (numerator == -1) {
        numeratorText = "menos un";
    } else if (numerator < 0) {
        numeratorText = "menos " + numeratorText;
    }

    return numeratorText + " " + denominatorText;
}



    private static int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
    }

    private static int[] simplifyFraction(int numerator, int denominator) {
        int gcdValue = gcd(Math.abs(numerator), Math.abs(denominator));
        return new int[]{numerator / gcdValue, denominator / gcdValue};
    }


    public static String handleInput(String input) {
        try {
            String[] parts;
            String operation;

            if (input.contains("mas")) {
                parts = input.split("mas");
                operation = "add";
            } else if (input.contains("menos")) {
                parts = input.split("menos");
                operation = "subtract";
            } else if (input.contains("multiplicado por")) {
                parts = input.split("multiplicado por");
                operation = "multiply";
            } else if (input.contains("dividido por")) {
                parts = input.split("dividido por");
                operation = "divide";
            } else {
                return "Operación no soportada";
            }

            if (parts.length != 2) {
                return "Entrada inválida: se esperaban dos fracciones separadas por una operación";
            }

            int[] frac1 = parseFraction(parts[0].trim());
            int[] frac2 = parseFraction(parts[1].trim());
            int[] result;

            switch (operation) {
                case "add":
                    result = addFractions(frac1, frac2);
                    break;
                case "subtract":
                    result = subtractFractions(frac1, frac2);
                    break;
                case "multiply":
                    result = multiplyFractions(frac1, frac2);
                    break;
                case "divide":
                    result = divideFractions(frac1, frac2);
                    break;
                default:
                    return "Operación no soportada";
            }

            result = simplifyFraction(result[0], result[1]);

            return fractionToText(result);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }




   


}
