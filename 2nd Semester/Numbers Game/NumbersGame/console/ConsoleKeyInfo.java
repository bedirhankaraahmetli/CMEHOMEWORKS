package NumbersGame.console;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enigma.core.Enigma;

public class ConsoleKeyInfo {
	
	private enigma.console.Console cn = Enigma.getConsole("Columns", 100, 25, 20);
	private KeyListener kl;
	public boolean isKeyPressed; 
	public int key;
	
	public ConsoleKeyInfo() throws Exception {
		kl=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(isKeyPressed==false) {
	               isKeyPressed=true;
	               key=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
		};
		cn.getTextWindow().addKeyListener(kl);
		Thread.sleep(20);
	}
	
	public void removeKeyListener() {
		cn.getTextWindow().removeKeyListener(this.kl);
	}
}

