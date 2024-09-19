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
            String result5 = Fracciones.handleInput("un ceroavos mas un medio");
            System.out.println("Result 5: " + result5);
        
            // System.out.println("Result 5: " + e.getMessage());

        String result6 = Fracciones.handleInput("un medio multiplicado por un cuarto");
        System.out.println("Result 6: " + result6);

        String result7 = Fracciones.handleInput("tres quintos mas cuatro septimos");
        System.out.println("Result 7: " + result7);

        String result8 = Fracciones.handleInput("veinticuatro treinta y sieteavos menos diecinueve treinta y sieteavos");
        System.out.println("Result 8: " + result8);

        String result9 = Fracciones.handleInput("tres cuartos dividido por dos quintos");
        System.out.println("Result 9: " + result9);

        String result10 = Fracciones.handleInput("cinco septimos multiplicado por tres septimos");
        System.out.println("Result 10: " + result10);

        String result11 = Fracciones.handleInput("un tercio menos cuatro tercios");
        System.out.println("Result 11: " + result11);


        String result12 = Fracciones.handleInput("dos tercios mas un tercio");
        System.out.println("Result 12: " + result12);

        String result14 = Fracciones.handleInput("dos medios multiplicado por cuatro cuartos");
        System.out.println("Result 14: " + result14);

        String result15 = Fracciones.handleInput("cinco sextos dividido por dos tercios");
        System.out.println("Result 15: " + result15);

    }
}
