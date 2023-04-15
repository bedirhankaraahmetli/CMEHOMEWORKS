import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.text.DecimalFormat;
import NumbersGame.console.ConsoleHelper;
import NumbersGame.console.ConsoleKeyInfo;

public class HighScoreTable {

	public static String location = "High Score Table.txt";
	public static DoubleLinkedList high_score_table_dll;
	public static Scanner my_input = new Scanner (System.in);
	
	public static void highScoreTable() throws Exception {
		
		boolean flag = true;
		
		ConsoleHelper.clear();
		System.out.println("ESC- Menu\n");
		
		high_score_table_dll =  FileOperations.readFile(location);
		high_score_table_dll.displayHST();
		
		ConsoleKeyInfo cki = new ConsoleKeyInfo();
		
		while (flag) {
			
			Thread.sleep(50); // Wait command for key detection.
			
			if (cki.isKeyPressed) {
				
				cki.isKeyPressed = false;
				
				switch (cki.key) {
				
				case KeyEvent.VK_ESCAPE:
					
					flag = false;
					break;
				}
			}
		}
	}
	
	public static void savePlayerScore (int score, int number_of_move) throws Exception {
		
		ConsoleHelper.clear();
		
		boolean flag = true;
		double player_score = scoreCalculation(score, number_of_move);
		
		ConsoleHelper.print("Your Score ---> ", 1, 1);
		ConsoleHelper.print(player_score, 18, 1);
		ConsoleHelper.print("Do you want to save?", 1, 2);
		ConsoleHelper.print("  1- Yes   2- No", 1, 3);
		
		ConsoleKeyInfo cki = new ConsoleKeyInfo();
		
		while (flag) {
			
			Thread.sleep(50); // Wait command for key detection.
			
			if (cki.isKeyPressed) {
				
				cki.isKeyPressed = false;
				
				switch (cki.key) {
				
				case KeyEvent.VK_1:
					
					ConsoleHelper.clear();
					
					ConsoleHelper.print("Your Name: ", 1, 1);
					ConsoleHelper.print("Your Score: " + player_score, 1, 2);
					ConsoleHelper.setCursorPosition(13, 1);
					String player_name = my_input.next();
					
					DoubleLinkedList dll = FileOperations.readFile(location);
					
					Player p = new Player();
					p.setPlayer_name(player_name);
					p.setPlayer_score(player_score);
					
					dll.sortAddDll(p);
					
					FileOperations.writeFile(location, dll);
					
					flag = false;
					break;
					
				case KeyEvent.VK_2:
					
					flag = false;
					break;
				}
			}
		}
	}
	
	public static double scoreCalculation(int score, int number_of_moves) {
		
		double player_score;
		
		player_score = (double) (Math.round(score * 1000.0) / 1000) / number_of_moves;
		player_score=decimal(player_score, 3);
		return player_score;
		
	}
	
	public static double decimal(double number,int count){

        String decimal_type="###.";
        for (int i=1;i<=count;i++){
        decimal_type+="#";
        }
        String new_number=(new DecimalFormat(decimal_type).format(number)).replace(',','.');
        System.out.println(new_number);
        double doublenumber=Double.parseDouble(new_number);
        return doublenumber;
        }
}
