import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.Color;
import java.util.Random;

public class EnigmaWrapper {

    private Console cn;
    private Color default_background = new Color(64, 64, 66); //Grey
    private Color default_foreground = new Color(159, 249, 224); //Neon Green

    public EnigmaWrapper(Console cn){
        this.cn = cn;
    }

    public void clearConsole(){ //print empty lines and reset the cursor locations
        for (int i = 0; i < 100; i++) {            
            System.out.println(" ");
        }
        cn.getTextWindow().setCursorPosition(0,0);
    }

    public void consoleColor(int r, int g, int b){ //color the text to rgb values
        Color color = new Color(r,g,b);
        TextAttributes ta = new TextAttributes(color);
        cn.setTextAttributes(ta);
    }

    public void consoleColor(int r1, int g1, int b1, int r2, int g2, int b2){ // foreground|background
        Color color1 = new Color(r1,g1,b1);
        Color color2 = new Color(r2,g2,b2);
        TextAttributes ta = new TextAttributes(color1, color2);
        cn.setTextAttributes(ta);
    }

    public void consoleColor(Color color1, Color color2){ //color the console using Color.color
        TextAttributes ta = new TextAttributes(color1, color2);
        cn.setTextAttributes(ta);
    }

    public void printInColor(Color color, String text){ //print a single text and reset the color
        TextAttributes ta = new TextAttributes(color);
        cn.setTextAttributes(ta);
        System.out.println(text);
        ta = new TextAttributes(default_foreground, default_background);
        cn.setTextAttributes(ta);
    }

    public void encodeWriting(String text){
        
        char[] char_text = text.toCharArray();
        Random random = new Random();
        int encode_speed = 5;
        char random_char;
        int ascii = 123;
        String str = "";

        for (int i = 0; i < char_text.length; i++) {
            for (int j = 0; j < encode_speed; j++) {
                while(true){
                    ascii = random.nextInt(122);
                    if (ascii > 97)
                    break;
                }
                random_char = (char)ascii;

                clearConsole();
                System.out.print(str + random_char);
                try {
                    Thread.sleep(18);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if(i != char_text.length - 1){
                str = str + char_text[i];
            }
            clearConsole();
            System.out.println(str);
            
        }
        
    }

    public static int compress(double value){ //reset the formatting of the rgb values
        if(value < 0){
            return 0;
        }
        else if(value > 255){
            return 255;
        }
        value += 0.5;
        return (int)value;

    }
    public static int[] hueCube(double degree, int r, int g, int b){ //create new rgb values by turning an imaginary color cube
        int[] arr = new int[3];
        double[][] cube_matrix = new double[3][3];
        double cosA = Math.cos(degree);
        double sinA = Math.sin(degree);

        cube_matrix[0][0] = cosA + (1.0 - cosA) / 3.0;
        cube_matrix[0][1] = 1./3. * (1.0 - cosA) - Math.sqrt(1./3.) * sinA;
        cube_matrix[0][2] = 1./3. * (1.0 - cosA) + Math.sqrt(1./3.) * sinA;
        cube_matrix[1][0] = 1./3. * (1.0 - cosA) + Math.sqrt(1./3.) * sinA;
        cube_matrix[1][1] = cosA + 1./3.*(1.0 - cosA);
        cube_matrix[1][2] = 1./3. * (1.0 - cosA) - Math.sqrt(1./3.) * sinA;
        cube_matrix[2][0] = 1./3. * (1.0 - cosA) - Math.sqrt(1./3.) * sinA;
        cube_matrix[2][1] = 1./3. * (1.0 - cosA) + Math.sqrt(1./3.) * sinA;
        cube_matrix[2][2] = cosA + 1./3. * (1.0 - cosA);

        double new_r = r * cube_matrix[0][0] + g * cube_matrix[0][1] + b * cube_matrix[0][2];
        double new_b = r * cube_matrix[1][0] + g * cube_matrix[1][1] + b * cube_matrix[1][2];
        double new_g = r * cube_matrix[2][0] + g * cube_matrix[2][1] + b * cube_matrix[2][2];

        arr[0] = compress(new_r);
        arr[1] = compress(new_g);
        arr[2] = compress(new_b);

        return arr;
    }

    public void splashScreen(){
        Color color = Color.orange;
        consoleColor(color,Color.black);
        clearConsole();
        System.out.println("eyup");
        int cn_size_x = cn.getTextWindow().getColumns();
        int cn_size_y = cn.getTextWindow().getRows();
        double degree = 1;
        int[] hue_cube;
        int dot_size = 1;
        Color[] colors = new Color[100];
        int color_counter = 0;
        Random random = new Random();
        int quantity = 0;

        while(true){
            quantity = random.nextInt(20);
            if(quantity > 5)
                break;
        }

        for (int i = 0; i < 100; i++) {
            hue_cube = hueCube(degree, color.getRed(), color.getGreen(), color.getBlue());
            color = new Color(hue_cube[0], hue_cube[1], hue_cube[2]);
            colors[i] = color;
            degree++;
        }

        while(color_counter != quantity){

            color = colors[color_counter];
            clearConsole();
            consoleColor(color, Color.black);
            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 1);
            System.out.println("'|| '||'  '|' '||                 '|| '||'  '|'                    .               .              '||                 ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 2);
            System.out.println(" '|. '|.  .'   || ..     ...       '|. '|.  .'   ....   .. ...   .||.   ....     .||.    ...       || ...    ....     ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 +3);
            System.out.println("  ||  ||  |    ||' ||  .|  '|.      ||  ||  |   '' .||   ||  ||   ||   ||. '      ||   .|  '|.     ||'  || .|...||    ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 4);
            System.out.println("   ||| |||     ||  ||  ||   ||       ||| |||    .|' ||   ||  ||   ||   . '|..     ||   ||   ||     ||    | ||         ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 5);
            System.out.println("    |   |     .||. ||.  '|..|'        |   |     '|..'|' .||. ||.  '|.' |'..|'     '|.'  '|..|'     '|...'   '|...'    ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 6);
            System.out.println("");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 7);
            System.out.println("");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 8);
            System.out.println("                       '||    ||'  ||  '||  '||   ||                            ||                  .|.               ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 9);
            System.out.println("             ....       |||  |||  ...   ||   ||  ...    ...   .. ...    ....   ...  ... ..    ....  |||               ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 10);
            System.out.println("            '' .||      |'|..'||   ||   ||   ||   ||  .|  '|.  ||  ||  '' .||   ||   ||' '' .|...|| '|'               ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 11);
            System.out.println("            .|' ||      | '|' ||   ||   ||   ||   ||  ||   ||  ||  ||  .|' ||   ||   ||     ||       |                ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 12);
            System.out.println("            '|..'|'    .|. | .||. .||. .||. .||. .||.  '|..|' .||. ||. '|..'|' .||. .||.     '|...'  .                ");

            cn.getTextWindow().setCursorPosition(cn_size_x/4, cn_size_y/4 + 13);
            System.out.println("                                                                                                    '|'               ");

            cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 15);
            System.out.println("Loading the game, please don't resize the console");
            if(dot_size == 3){
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 16);
                System.out.println(" _ _ _ ");
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 17);
                System.out.println("(_|_|_)");
                dot_size = 4;
            }
            else if(dot_size == 4){
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 16);
                System.out.println(" _ _  ");
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 17);
                System.out.println("(_|_)");
                dot_size = 1;
            }
            else if(dot_size == 1){
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 16);
                System.out.println(" _ ");
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 17);
                System.out.println("(_)");
                dot_size = 2;
            }
            else if(dot_size == 2){
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 16);
                System.out.println(" _ _  ");
                cn.getTextWindow().setCursorPosition(cn_size_x/3, cn_size_y/4 + 17);
                System.out.println("(_|_)");
                dot_size = 3;
            }
            color_counter++;

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){ //!debug func, remove in production
        enigma.console.Console cn = Enigma.getConsole("console", 200, 50, 20);
        EnigmaWrapper ew = new EnigmaWrapper(cn);
        ew.consoleColor(Color.orange, Color.black);
        ew.encodeWriting("Mathematics#The eigen values of a skew-symmetric matrix are#always zero#always pure imaginary#either zero or pure imaginary#always real#C#1");

        




        
    }
}
