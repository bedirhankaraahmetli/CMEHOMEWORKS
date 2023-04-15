import java.awt.event.KeyEvent;

import NumbersGame.console.ConsoleHelper;
import NumbersGame.console.ConsoleKeyInfo;
import enigma.core.Enigma;

public class Menu {

	public static enigma.console.Console cn = Enigma.getConsole("****Deu Numbers Game****", 100, 25, 20);
	
	public static void menu() throws Exception {
		
		ConsoleKeyInfo cki = new ConsoleKeyInfo();
		
		printMenu(); // Printing the menu
		
		while (true) {
			
			Thread.sleep(50); // Wait command for key detection
			
			if (cki.isKeyPressed) {
				
				cki.isKeyPressed = false;
				
				switch (cki.key) {
				
				case KeyEvent.VK_1:
					
					Game.game();
					ConsoleHelper.clear();
					printMenu();
					break;

			
				case KeyEvent.VK_2:
					
					HighScoreTable.highScoreTable();
					ConsoleHelper.clear();
					printMenu();
					break;
					
				
				case KeyEvent.VK_3:
					
					System.out.println("Goodbye :)");
					Thread.sleep(1500);
					System.exit(0);
					break;
				}
			}
		}
	}
	
	public static void printMenu() {
		
		ConsoleHelper.print("+-------------------------+", 35, 9);
        ConsoleHelper.print("|   1- Play Game          | ", 35, 10);
        ConsoleHelper.print("|   2- High Score Table   |", 35, 11);
        ConsoleHelper.print("|   3- Exit               |", 35, 12);
        ConsoleHelper.print("+-------------------------+", 35, 13);
	}
	
	
}
