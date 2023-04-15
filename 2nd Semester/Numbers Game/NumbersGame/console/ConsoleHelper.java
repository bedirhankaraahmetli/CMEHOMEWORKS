package NumbersGame.console;

import java.awt.Color;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class ConsoleHelper {

	static enigma.console.Console console = Enigma.getConsole("Columns", 100, 25, 20);
	
	public static void clear() {
		
		console.getTextWindow().setCursorPosition(0, 0);
		
		for (int i = 0; i < console.getTextWindow().getColumns(); i++) {
			String s = "";
			for (int j = 0; j < console.getTextWindow().getRows(); j++) {
				s+=" ";
			}
			System.out.println(s);
		}
		
		console.getTextWindow().setCursorPosition(0, 0);
	}
	
	public static void setCursorPosition (int x, int y) {
		
		console.getTextWindow().setCursorPosition(x,y);
	}
	
	public static void print(Object object, int x, int y) {
		console.getTextWindow().setCursorPosition(x, y);
		System.out.print(object);
		console.getTextWindow().setCursorPosition(x, y);
	}
	
	public static void setColor(Color forecolor) {
		console.setTextAttributes(new TextAttributes(forecolor));
	}
	
	public static void setColor(Color forecolor, Color backcolor) {
		console.setTextAttributes(new TextAttributes(forecolor, backcolor));
	}
}
