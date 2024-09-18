import coco.secret.fracciones.Fracciones;

public class Main {
    public static void main(String[] args) {

        //  99/99 + 99/99
        String result1 = Fracciones.handleInput("noventa y nueve noventa y nueveavos mas noventa y nueve noventa y nueveavos");
        System.out.println("Result 1: " + result1);

        //  99/99 * 99/99
        String result2 = Fracciones.handleInput("noventa y nueve noventa y nueveavos multiplicado por noventa y nueve noventa y nueveavos");
        System.out.println("Result 2: " + result2);

        //  1/2 - 3/2
        String result3 = Fracciones.handleInput("un medio menos tres medios");
        System.out.println("Result 3: " + result3);

        //  1/3 - 2/5
        String result4 = Fracciones.handleInput("un tercio menos dos quintos");
        System.out.println("Result 4: " + result4);

        //  1/0 + 1/2 (division por cero
        try {
            String result5 = Fracciones.handleInput("un ceroavos mas un medio");
            System.out.println("Result 5: " + result5);
        } catch (IllegalArgumentException e) {
            System.out.println("Result 5: " + e.getMessage());
        }

    }
}
