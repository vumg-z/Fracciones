package coco.secret.fracciones;

import java.util.HashMap;
import java.util.Map;

public class Mapa {

    private static final Map<String, Integer> unidades = new HashMap<>();
    private static final Map<String, Integer> decenas = new HashMap<>();
    private static final Map<String, String> denominadorPlural = new HashMap<>();
    private static final Map<Integer, String> denominadorSingular = new HashMap<>();

    static {
        unidades.put("cero", 0);
        unidades.put("uno", 1);
        unidades.put("un", 1);
        unidades.put("dos", 2);
        unidades.put("tres", 3);
        unidades.put("cuatro", 4);
        unidades.put("cinco", 5);
        unidades.put("seis", 6);
        unidades.put("siete", 7);
        unidades.put("ocho", 8);
        unidades.put("nueve", 9);
        unidades.put("diez", 10);
        unidades.put("once", 11);
        unidades.put("doce", 12);
        unidades.put("trece", 13);
        unidades.put("catorce", 14);
        unidades.put("quince", 15);
        unidades.put("dieciseis", 16);
        unidades.put("diecisiete", 17);
        unidades.put("dieciocho", 18);
        unidades.put("diecinueve", 19);
        unidades.put("veinte", 20);
        unidades.put("veintiuno", 21);
        unidades.put("veintidos", 22);
        unidades.put("veintitres", 23);
        unidades.put("veinticuatro", 24);
        unidades.put("veinticinco", 25);
        unidades.put("veintiseis", 26);
        unidades.put("veintisiete", 27);
        unidades.put("veintiocho", 28);
        unidades.put("veintinueve", 29);

        decenas.put("treinta", 30);
        decenas.put("cuarenta", 40);
        decenas.put("cincuenta", 50);
        decenas.put("sesenta", 60);
        decenas.put("setenta", 70);
        decenas.put("ochenta", 80);
        decenas.put("noventa", 90);

        denominadorSingular.put(2, "medio");
        denominadorSingular.put(3, "tercio");
        denominadorSingular.put(4, "cuarto");
        denominadorSingular.put(5, "quinto");
        denominadorSingular.put(6, "sexto");
        denominadorSingular.put(7, "séptimo");
        denominadorSingular.put(8, "octavo");
        denominadorSingular.put(9, "noveno");
        denominadorSingular.put(10, "décimo");

        denominadorPlural.put("medio", "medios");
        denominadorPlural.put("tercio", "tercios");
        denominadorPlural.put("cuarto", "cuartos");
        denominadorPlural.put("quinto", "quintos");
        denominadorPlural.put("sexto", "sextos");
        denominadorPlural.put("séptimo", "séptimos");
        denominadorPlural.put("octavo", "octavos");
        denominadorPlural.put("noveno", "novenos");
        denominadorPlural.put("décimo", "décimos");
    }

    public static NumberParseResult parseNumber(String[] words, int index) {
        int number = -1;
        int wordsConsumed = 0;

        // Try to parse "tens y units"
        if (index + 2 < words.length && words[index + 1].equals("y")) {
            String tensWord = words[index];
            String unitsWord = words[index + 2];

            Integer tens = decenas.get(tensWord);
            Integer units = unidades.get(unitsWord);

            if (tens != null && units != null) {
                number = tens + units;
                wordsConsumed = 3;
                return new NumberParseResult(number, wordsConsumed);
            }
        }

        // Try to parse one word number
        String word1 = words[index];
        if (unidades.containsKey(word1)) {
            number = unidades.get(word1);
            wordsConsumed = 1;
        } else if (decenas.containsKey(word1)) {
            number = decenas.get(word1);
            wordsConsumed = 1;
        }

        if (number != -1) {
            return new NumberParseResult(number, wordsConsumed);
        } else {
            return null;
        }
    }

    public static int getNumero(String text) {
        text = text.trim().toLowerCase();

        if (unidades.containsKey(text)) {
            return unidades.get(text);
        } else if (decenas.containsKey(text)) {
            return decenas.get(text);
        } else if (text.contains(" y ")) {
            String[] parts = text.split(" y ");
            if (parts.length != 2) {
                return -1;
            }
            String tensText = parts[0];
            String unitsText = parts[1];
            Integer tens = decenas.get(tensText);
            Integer units = unidades.get(unitsText);
            if (tens == null || units == null) {
                return -1;
            }
            return tens + units;
        } else {
            return -1;
        }
    }

    public static int getDenominador(String text) {
        text = text.trim().toLowerCase();

        // Remove plural 's' if present
        if (text.endsWith("s")) {
            text = text.substring(0, text.length() - 1);
        }

        // Check if text matches denominador singular or plural
        for (Map.Entry<Integer, String> entry : denominadorSingular.entrySet()) {
            String singular = entry.getValue();
            String plural = denominadorPlural.get(singular);

            if (singular.equals(text) || (plural != null && plural.equals(text))) {
                return entry.getKey();
            }
        }

        // Check for numbers ending with 'avo' or 'avo'
        if (text.endsWith("avo")) {
            int index = text.lastIndexOf("avo");
            String numberText = text.substring(0, index).trim();
            int denominator = getNumero(numberText);
            if (denominator != -1) {
                return denominator;
            }
        }

        return -1;
    }

    public static String numeroATexto(int number) {
        if (number == 0) {
            return "cero";
        } else if (number < 0) {
            return "menos " + numeroATexto(-number);
        } else if (number <= 15) {
            switch (number) {
                case 1: return "uno";
                case 2: return "dos";
                case 3: return "tres";
                case 4: return "cuatro";
                case 5: return "cinco";
                case 6: return "seis";
                case 7: return "siete";
                case 8: return "ocho";
                case 9: return "nueve";
                case 10: return "diez";
                case 11: return "once";
                case 12: return "doce";
                case 13: return "trece";
                case 14: return "catorce";
                case 15: return "quince";
                default: return String.valueOf(number);
            }
        } else if (number < 20) {
            return "dieci" + numeroATexto(number - 10);
        } else if (number == 20) {
            return "veinte";
        } else if (number < 30) {
            return "veinti" + numeroATexto(number - 20);
        } else if (number < 100) {
            int tens = (number / 10) * 10;
            int units = number % 10;
            String tensText = "";
            for (Map.Entry<String, Integer> entry : decenas.entrySet()) {
                if (entry.getValue() == tens) {
                    tensText = entry.getKey();
                    break;
                }
            }
            if (units == 0) {
                return tensText;
            } else {
                return tensText + " y " + numeroATexto(units);
            }
        } else if (number == 100) {
            return "cien";
        } else if (number < 200) {
            return "ciento " + numeroATexto(number - 100);
        } else if (number < 1000) {
            int hundreds = number / 100;
            int remainder = number % 100;
            String hundredsText = "";
            switch (hundreds) {
                case 2: hundredsText = "doscientos"; break;
                case 3: hundredsText = "trescientos"; break;
                case 4: hundredsText = "cuatrocientos"; break;
                case 5: hundredsText = "quinientos"; break;
                case 6: hundredsText = "seiscientos"; break;
                case 7: hundredsText = "setecientos"; break;
                case 8: hundredsText = "ochocientos"; break;
                case 9: hundredsText = "novecientos"; break;
            }
            if (remainder == 0) {
                return hundredsText;
            } else {
                return hundredsText + " " + numeroATexto(remainder);
            }
        } else if (number == 1000) {
            return "mil";
        } else if (number < 2000) {
            return "mil " + numeroATexto(number % 1000);
        } else if (number < 1000000) {
            int thousands = number / 1000;
            int remainder = number % 1000;
            String thousandsText = numeroATexto(thousands) + " mil";
            if (remainder != 0) {
                thousandsText += " " + numeroATexto(remainder);
            }
            return thousandsText;
        } else {
            return String.valueOf(number);
        }
    }

    public static String getDenominadorSingular(int denominator) {
        if (denominadorSingular.containsKey(denominator)) {
            return denominadorSingular.get(denominator);
        } else {
            return numeroATexto(denominator) + "avo";
        }
    }

    public static String getDenominadorPlural(String singular) {
        if (denominadorPlural.containsKey(singular)) {
            return denominadorPlural.get(singular);
        } else if (singular.endsWith("avo")) {
            return singular + "s";
        } else {
            return singular;
        }
    }

    // Inner class for number parsing result
    public static class NumberParseResult {
        public int number;
        public int wordsConsumed;

        public NumberParseResult(int number, int wordsConsumed) {
            this.number = number;
            this.wordsConsumed = wordsConsumed;
        }
    }
}
