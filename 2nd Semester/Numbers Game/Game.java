import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import NumbersGame.console.ConsoleHelper;
import NumbersGame.console.ConsoleKeyInfo;

public class Game {

	private static Random r = new Random();
	private static int x,y,zx,zy,bx,by,number_of_moves, score;
	private static SingleLinkedList box = new SingleLinkedList(), transport_sll = new SingleLinkedList();
	private static MultiLinkedList play_ground = new MultiLinkedList();
	private static NumberNodeMll selected_number, box_transport_node;
	
	public static void game() throws Exception {
		
		boolean the_end = false, flag_z = false, flag_b = false;
		x = 2; y = 2; number_of_moves = 0; score = 0;
		int mesage = 0;
		selected_number = null; box_transport_node = null;
		
		ConsoleHelper.clear();
		
		play_ground = creationOfColumns(); // Mll için column larý oluþturur.
		box = fillingTheBox(); // Random filling of the box
		placingStarterCards(); // baþlangýç kartlarýný columnlara yerleþtirir
		gameScreen(); // Printing the game start screen
		
		ConsoleKeyInfo cki = new ConsoleKeyInfo();
		
		while (!the_end) {
			
			Thread.sleep(50); // Wait command for key detection.
			
			
			if (cki.isKeyPressed) {
				
				cki.isKeyPressed = false;
				
				switch (cki.key) {
				
				case KeyEvent.VK_UP: // Move up
					
					if (!flag_b && !flag_z && y > 2) {
						
						// Pre-movement wipe
						String column = selectColumnName(x);
						ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
						
						// Post-action printing
						y--;
						ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
						ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
						ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
					}
					break;
					
				case KeyEvent.VK_DOWN: // Move Down
					
					if (!flag_z && !flag_b) {
						
						int column_size = play_ground.sizeNumber(selectColumnName(x)); // Columnun uzunluüuna baðlý hareket sýnýrýný belirler
						
						if (y < column_size + 1) { 
							
							// Pre-movement wipe
							String column = selectColumnName(x);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
							
							// Post-action printing
							y++;
							ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
						}
					}
					break;
					
				case KeyEvent.VK_RIGHT: // Move Right
	
					if (!flag_z && !flag_b && x < 26) {
						
						boolean flag = true;
						int next_column_x = x + 6, next_column_y = y;
						int next_column_size = play_ground.sizeNumber(selectColumnName(next_column_x)); 
						
						// cursorun hareket edebileceði kontrol edilir.
						while (true) {
							
							if (next_column_size == 0 && next_column_x < 26) { // gidilecek columnun boþ olma durumu
								
								next_column_x += 6; // bir sonraki columna ilerlenir.
								next_column_size = play_ground.sizeNumber(selectColumnName(next_column_x));
							} 
							else if(next_column_size != 0 && next_column_size < y){ // column un uzunluðunun y den küçük olma durumu
								next_column_y = next_column_size + 1;
								flag = true;
								break;
							}
							else if (next_column_size != 0) { // column un uzunluðunun y'ye eþit olma ve büyük olma durumu.
								next_column_y = y;
								flag = true;
								break;
							}
							else { // diðer durumlar
								
								flag = false;
								break;
							}
						}
						
						if (flag) { // cursor hareketinin gerçekleþtirilmesi.
							
							// Pre-movement wipe
							String column = selectColumnName(x);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
							
							// Post-action printing
							x = next_column_x;
							y = next_column_y;
							column = selectColumnName(x);
							ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
						}
						
					}
					else if (x < 26) { // z ve x e basýlýyken cursorun hareketi
					
						ConsoleHelper.print(" ", x, y);
						
						int right_number_size = play_ground.sizeNumber(selectColumnName(x+6)); // y nin konumunun belirlenmesi
					
						y = right_number_size + 2;
						x += 6;
					
						ConsoleHelper.setColor(Color.WHITE, Color.YELLOW);
						ConsoleHelper.print(" ", x, y);
						ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
					}
					break;
	
				case KeyEvent.VK_LEFT: // Move Left
	
					if (!flag_z && !flag_b && x > 2) {
						
						boolean flag = true;
						int next_column_x = x - 6, next_column_y = y;
						int next_column_size = play_ground.sizeNumber(selectColumnName(next_column_x));
						
						// cursorun hareket edebileceði kontrol edilir.
						while (true) {
							
							if (next_column_size == 0 && next_column_x > 2) { // gidilecek columnun boþ olma durumu
								
								next_column_x -= 6; // bir sonraki columna ilerlenir.
								next_column_size = play_ground.sizeNumber(selectColumnName(next_column_x));
							} 
							else if(next_column_size != 0 && next_column_size < y){ // column un uzunluðunun y den küçük olma durumu
								next_column_y = next_column_size + 1;
								flag = true;
								break;
							}
							else if (next_column_size != 0) { // column un uzunluðunun y'ye eþit olma ve büyük olma durumu.
								next_column_y = y;
								flag = true;
								break;
							}
							else { // diðer durumlar
								
								flag = false;
								break;
							}
						}
						
						if (flag) { // cursor hareketinin gerçekleþtirilmesi.
							
							// Pre-movement wipe
							String column = selectColumnName(x);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
							
							// Post-action printing
							x = next_column_x;
							y = next_column_y;
							column = selectColumnName(x);
							ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
						}
					}
					else if(x > 2) { // z ve x e basýlýyken cursorun hareketi
						
						ConsoleHelper.print(" ", x, y);
						
						int left_number_size = play_ground.sizeNumber(selectColumnName(x-6)); // y nin konumunun belirlenmesi
						
						y = left_number_size + 2;
						x -= 6;
						
						ConsoleHelper.setColor(Color.WHITE, Color.YELLOW);
						ConsoleHelper.print(" ", x, y);
						ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
					}
					break;
					
				case KeyEvent.VK_Z:
					
					if (!flag_b) {
						
						if (!flag_z) { // seçim yapma
							
							String column = selectColumnName(x);
							selected_number = play_ground.selectNumber(column, x, y);
							zx = x; zy = y;
							if (selected_number == null) { 
								mesage++;
								ConsoleHelper.print("                                                      ", 32, 2);
								ConsoleHelper.print(mesage + "- Selected partition is not in order",33, 2);
							} 
							else {
								flag_z = true;
								
								//
								int number_size = play_ground.sizeNumber(selectColumnName(x)); // y nin yerinin belirlenmesi
								y = number_size + 2; 
								
								// cursorun sayý taþýma konumuna getirilmesi
								ConsoleHelper.setColor(Color.WHITE, Color.YELLOW);
								ConsoleHelper.print(" ", x, y);
								ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							}
							
						} 
						else { // seçimden vazgeçme durumu
							
							ConsoleHelper.print(" ", x, y);
							
							x = zx; y = zy;
							ConsoleHelper.setCursorPosition(x, y);
							
							flag_z = false;
						}
					}
					break;
					
				case KeyEvent.VK_X:
					
					if (flag_z) { // yer deðiþtilecek sayýlarýn yerleþtirilmesi
						if (x == zx) {
							mesage++;
							ConsoleHelper.print("                                                      ", 32, 2);
							ConsoleHelper.print(mesage + "- Cannot be placed in the same location",33, 2);
						} 
						else {
							
							String column = selectColumnName(x);
									
							if (play_ground.lastValue(column) != -1 ) { // sütunda sayý olma durumu
								
								int the_difference = play_ground.lastValue(column) - selected_number.getNumber(); 
								
								if (the_difference == 1 || the_difference == 0 || the_difference == -1) { 
									
									// sayýlarýn eski konumundan siler 
									deleteColumnFromScreen(zx, play_ground.sizeNumber(selectColumnName(zx)));
									play_ground.deletionProcess(selectColumnName(zx), zy);
									play_ground.printSingleColumn(selectColumnName(zx), zx, 2);
									
									// numberlarý yeni yerine ekler ve ekrana yazdýrýr
									deleteColumnFromScreen(x, play_ground.sizeNumber(selectColumnName(x)));
									play_ground.addNumberNode(column, selected_number);
									play_ground.printSingleColumn(selectColumnName(x), x, 2);
									
									// hamle sayýsýný arttýrýr ve gösterir.
									number_of_moves ++;
									ConsoleHelper.print("   ", 48, 11);
									ConsoleHelper.print(number_of_moves, 48, 11);
									
									// Konsolun yeni yerine taþýr ve gösterir.
									column = selectColumnName(x);
									ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
									ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
									ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
									
									if (play_ground.sizeNumber(selectColumnName(zx)) == 10) { // sayýlarýn alýndýðý sütunun kontrolü
										
										column = selectColumnName(zx);
										scoring(column); // TODO
									} 
									else if(play_ground.sizeNumber(selectColumnName(x)) == 10) { // sayýlarýn konulduðu sütunun kontrolü
										
										column = selectColumnName(x);
										scoring(column); // TODO
									}
									
									
									flag_z = false;
								}
								else {
									
									mesage++;
									ConsoleHelper.print("                                                      ", 32, 2);
									ConsoleHelper.print(mesage + "- Doesn't follow the rule",33, 2);
								}
							} 
							else if(selected_number.getNumber() == 10 || selected_number.getNumber() == 1) { // sütunun boþ olma durumu
								
								// sayýlarý eski konumundan siler
								deleteColumnFromScreen(zx, play_ground.sizeNumber(selectColumnName(zx)));
								play_ground.deletionProcess(selectColumnName(zx), zy);
								play_ground.printSingleColumn(selectColumnName(zx), zx, 2);
								
								// numberlarý yeni yerine ekler ve ekrana yazdýrýr
								deleteColumnFromScreen(x, play_ground.sizeNumber(selectColumnName(x)));
								play_ground.addNumberNode(column, selected_number);
								play_ground.printSingleColumn(selectColumnName(x), x, 2);
								
								// hamle sayýsýný arttýrýr ve gösterir.
								number_of_moves ++;
								ConsoleHelper.print("   ", 48, 11);
								ConsoleHelper.print(number_of_moves, 48, 11);
								
								// Konsolun yeni yerine taþýr ve gösterir.
								column = selectColumnName(x);
								ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
								ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
								ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
								
								if (play_ground.sizeNumber(selectColumnName(zx)) == 10) { // sayýlarýn alýndýðý sütunun kontrolü
									
									column = selectColumnName(zx);
									scoring(column); // TODO
								} 
								else if(play_ground.sizeNumber(selectColumnName(x)) == 10) { // sayýlarýn konulduðu sütunun kontrolü
									
									column = selectColumnName(x);
									scoring(column); // TODO
								}
								
								flag_z = false;
							}
							else {
								
								mesage++;
								ConsoleHelper.print("                                                      ", 32, 2);
								ConsoleHelper.print(mesage + "- The part to be added must start with 1 or 10",33, 2);
								
							}
						}
					}
					else if(flag_b) { // box taki sayýnýn yerleþtirilmesi
						
						String column = selectColumnName(x);
						
						if (play_ground.lastValue(column) != -1 ) { // sütunun sayý olma durumu
							
							int the_difference = play_ground.lastValue(column) - box_transport_node.getNumber();
							
							if (the_difference == 1 || the_difference == 0 || the_difference == -1) {
								
								// box taki sayýyý yeni yerine ekler ve ekrana yazdýrýr
								deleteColumnFromScreen(x, play_ground.sizeNumber(selectColumnName(x)));
								play_ground.addNumberNode(column, box_transport_node);
								play_ground.printSingleColumn(selectColumnName(x), x, 2);
								
								//Box ýn üstünü kapatýr
								ConsoleHelper.setColor(Color.RED);
								ConsoleHelper.print("##", 32, 8);
								ConsoleHelper.setColor(Color.WHITE);
								
								// Konsolun eski yerindeki renklendirmeyi siler
								column = selectColumnName(bx);
								ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
								ConsoleHelper.print(play_ground.theValueOfTheLocation(column, bx, by), bx, by);
								ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
								
								// hamle sayýsýný arttýrýr ve gösterir.
								number_of_moves ++;
								ConsoleHelper.print("   ", 48, 11);
								ConsoleHelper.print(number_of_moves, 48, 11);
								
								// Konsolun yeni yerine taþýr ve gösterir.
								column = selectColumnName(x);
								ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
								ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
								ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
								
								if (play_ground.sizeNumber(selectColumnName(bx)) == 10) { // sayýlarýn alýndýðý sütunun kontrolü
									
									column = selectColumnName(bx);
									scoring(column); // TODO
								} 
								else if(play_ground.sizeNumber(selectColumnName(x)) == 10) { // sayýlarýn konulduðu sütunun kontrolü
									
									column = selectColumnName(x);
									scoring(column); // TODO
								}
								
								flag_b = false;
								box_transport_node = null;
								
							}
							else {
								
								mesage++;
								ConsoleHelper.print("                                                      ", 32, 2);
								ConsoleHelper.print(mesage + "- Doesn't follow the rule",33, 2);
							}
						}
						else if(box_transport_node.getNumber() == 10 || box_transport_node.getNumber() == 1) { // sütunun boþ olma durumu.
							
							// box taki sayýyý yeni yerine ekler ve ekrana yazdýrýr
							deleteColumnFromScreen(x, play_ground.sizeNumber(selectColumnName(x)));
							play_ground.addNumberNode(column, box_transport_node);
							play_ground.printSingleColumn(selectColumnName(x), x, 2);
							
							//Box ýn üstünü kapatýr
							ConsoleHelper.setColor(Color.RED);
							ConsoleHelper.print("##", 32, 8);
							ConsoleHelper.setColor(Color.WHITE);
							
							// Konsolun eski yerindeki renklendirmeyi siler
							column = selectColumnName(bx);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, bx, by), bx, by);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							
							// hamle sayýsýný arttýrýr ve gösterir.
							number_of_moves ++;
							ConsoleHelper.print("   ", 48, 11);
							ConsoleHelper.print(number_of_moves, 48, 11);
							
							// Konsolun yeni yerine taþýr ve gösterir.
							column = selectColumnName(x);
							ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
							ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							
							if (play_ground.sizeNumber(selectColumnName(bx)) == 10) { // sayýlarýn alýndýðý sütunun kontrolü
								
								column = selectColumnName(bx);
								scoring(column); // TODO
							} 
							else if(play_ground.sizeNumber(selectColumnName(x)) == 10) { // sayýlarýn konulduðu sütunun kontrolü
								
								column = selectColumnName(x);
								scoring(column); // TODO
							}
							
							flag_b = false;
							box_transport_node = null;
						}
						else {
							
							mesage++;
							ConsoleHelper.print("                                                      ", 32, 2);
							ConsoleHelper.print(mesage + "- The part to be added must start with 1 or 10",33, 2);
							
						}
					}
					else {
						
						mesage++;
						ConsoleHelper.print("                                                      ", 32, 2);
						ConsoleHelper.print(mesage + "- No selection made",33, 2);
					}
					
					
					break;
					
				case KeyEvent.VK_B:
					
					if (!flag_z) {
						
						if (!box.isEmpty() && box_transport_node == null) { // boxtan sayý çýkarma
							
							box_transport_node = boxOperation();
							
							bx = x; by = y;
							
							flag_b = true;
							
							int number_size = play_ground.sizeNumber(selectColumnName(x));
							y = number_size + 2; 
							
							//Box daki sayýnýn renklendirilmesi
							ConsoleHelper.print("  ", 32, 8);
							ConsoleHelper.setColor(Color.WHITE, Color.BLUE);
							ConsoleHelper.print(box_transport_node.getNumber(), 32, 8);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							
							//Cursorun taþýma pozisyonuna getirilmesi ve renklendirilmesi
							ConsoleHelper.setColor(Color.WHITE, Color.YELLOW);
							ConsoleHelper.print(" ", x, y);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							
							
						}
						else if ( !box.isEmpty() && !flag_b && box_transport_node != null) { // boxta açýlmýþ sayý olma durumu.
							
							bx = x; by = y;
							
							flag_b = true;
							
							int number_size = play_ground.sizeNumber(selectColumnName(x));
							y = number_size + 2; 
							
							//Box daki sayýnýn renklendirilmesi
							ConsoleHelper.print("  ", 32, 8);
							ConsoleHelper.setColor(Color.WHITE, Color.BLUE);
							ConsoleHelper.print(box_transport_node.getNumber(), 32, 8);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							
							//Cursorun taþýma pozisyonuna getirilmesi ve renklendirilmesi
							ConsoleHelper.setColor(Color.WHITE, Color.YELLOW);
							ConsoleHelper.print(" ", x, y);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
						}
						else if (box_transport_node != null){ // seçilen sayýnýn iptal durumu
							
							// Taþýma pozisyonundaki cursorun renginin silinmesi
							ConsoleHelper.print(" ", x, y);
							
							//Box daki sayýnýn renklendirilmesi
							ConsoleHelper.print("  ", 32, 8);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							ConsoleHelper.print(box_transport_node.getNumber(), 32, 8);
							ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
							
							//cursorun eski konumuna getirilmesi
							x = bx; y = by;
							ConsoleHelper.setCursorPosition(x, y);
							
							flag_b = false;
						}	
					}
				break;
					
				case KeyEvent.VK_ESCAPE:
					
					the_end = true;

				default:
					break;
				}
			}
		}
		
		if (number_of_moves != 0) {
			
			HighScoreTable.savePlayerScore(score, number_of_moves);
		}
	}
	
	public static SingleLinkedList fillingTheBox() { // Random filling of the box
		
		SingleLinkedList sll = new SingleLinkedList();
		int random_number;
		int[] numbers_array = {5,5,5,5,5,5,5,5,5,5}; // The array that holds the numbers
		
		for (int i = 0; i < 50;) {
			
			random_number = r.nextInt(10); // Random selection of numbers
			
			if (numbers_array[random_number] != 0) {
				
				numbers_array[random_number]--;
				i++;
				sll.addNode((random_number+1)); // The selected numbers are thrown into the box
			}
		}
		return sll;
	}

	public static void gameScreen() { // Printing the game start screen
		
		// Printing the columns
		ConsoleHelper.setColor(Color.RED);
		ConsoleHelper.print("  C1    C2    C3    C4    C5 ", 0, 0);
		ConsoleHelper.print(" ----  ----  ----  ----  ----", 0, 1);
		ConsoleHelper.setColor(Color.WHITE);
		
		// Printing the box
		ConsoleHelper.setColor(Color.RED);
		ConsoleHelper.print("+--+", 31, 7);
		ConsoleHelper.print("|##|", 31, 8);
		ConsoleHelper.print("+--+", 31, 9);
		ConsoleHelper.print("Number of cards: ", 31, 10);
		ConsoleHelper.print("Number of moves: ", 31, 11);
		ConsoleHelper.print("Score of player: ", 31, 12);
		ConsoleHelper.setColor(Color.WHITE);
		ConsoleHelper.print("  ", 48, 10);
		ConsoleHelper.print(box.size(), 48, 10);
		ConsoleHelper.print("   ", 48, 11);
		ConsoleHelper.print(number_of_moves, 48, 11);
		ConsoleHelper.print("    ", 48, 11);
		ConsoleHelper.print(score, 48, 12);
		
		ConsoleHelper.setColor(Color.BLUE);
		ConsoleHelper.print("Info Box", 32, 0);
		ConsoleHelper.print("*__--__--__--__--__--__--__--__--__--__--__--__--__--__*", 31, 1);
		ConsoleHelper.print("|                                                      |", 31, 2);
		ConsoleHelper.print("|                                                      |", 31, 3);
		ConsoleHelper.print("|                                                      |", 31, 4);
		ConsoleHelper.print("*__--__--__--__--__--__--__--__--__--__--__--__--__--__*", 31, 5);
		ConsoleHelper.setColor(Color.WHITE);
		
		// Starting point of cursor
		ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
		String column = selectColumnName(x);
		ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
		ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
	}

	public static NumberNodeMll boxOperation() {
		
		SingleLinkedList sll = new SingleLinkedList();
		
		sll = box.cutOff(1); // The element is removed from the box and kept in sll
		
		NumberNodeMll new_node = new NumberNodeMll((Integer)sll.initialValue());
		
		ConsoleHelper.print("  ", 32, 8); // Deletes previous post
		ConsoleHelper.print(String.valueOf(new_node.getNumber()), 32, 8);
		
		ConsoleHelper.print("  ", 48, 10); // Deletes previous post
		ConsoleHelper.print(String.valueOf(box.size()), 48, 10);
		return new_node;
	}
	
	public static void placingStarterCards() {
		
		for (int i = 2; i < 27; i+=6) {
			
			transport_sll = box.cutOff(6);
			String column_name = selectColumnName(i);
			
			ConsoleHelper.print("  ", 48, 10); // Deletes previous post
			ConsoleHelper.print(String.valueOf(box.size()), 48, 10);
			
			for (int j = 1; j < 7; j++) {
				
				SingleLinkedList card = transport_sll.cutOff(1);
				play_ground.addNumber(column_name, (Integer) card.subtructInitialValue());
			}
			
			int x = columnXLocation(column_name);
			int y = 2;
			
			play_ground.printSingleColumn(column_name, x, y);
		}
	}
	
	public static MultiLinkedList creationOfColumns() {
		
		MultiLinkedList mll = new MultiLinkedList();
		
		for (int i = 2; i < 27; i+=6) {
			
			String column_name = selectColumnName(i);
			mll.addColumn(column_name);
		}
		
		return mll;
	}
	
	public static String selectColumnName(int a) {
		
		String column_name = null;
		
		switch (a) {
		case 2:
			column_name = "C1";
			break;
		case 8:
			column_name = "C2";
			break;
		case 14:
			column_name = "C3";
			break;
		case 20:
			column_name = "C4";
			break;
		case 26:
			column_name = "C5";
			break;
		}
		
		return column_name;
	}

	public static int columnXLocation(String column_name) {
		
		int x = 0;
		
		switch (column_name) {
		case "C1":
			x = 2;
			break;

		case "C2":
			x = 8;
			break;
			
		case "C3":
			x = 14;
			break;
			
		case "C4":
			x = 20;
			break;
		
		case "C5":
			x = 26;
			break;
		}
		return x;
	}

	public static void deleteColumnFromScreen(int x, int size) {
		
		int y = 2;
		
		for (int i = 0; i < size; i++) {
			
			ConsoleHelper.print("  ", x, y);
			y++;
		}
	}
	
	public static boolean scoring(String column_name) { // 
		
		boolean flag = false, flag1 = false;
		
		if (play_ground.firstValue(column_name) == 10) { 
			
			flag = play_ground.columnCheckingAndDeletion(column_name, 1);
		}
		else if(play_ground.firstValue(column_name) == 1) { 
			
			flag = play_ground.columnCheckingAndDeletion(column_name, -1);
		}
		
		if (flag) {
			
			// Skorun ekrana yazdýrýlmasý
			score += 1000;
			ConsoleHelper.print("    ", 48, 12);
			ConsoleHelper.print(score, 48, 12);
			
			// Sütunun ekrandan silinmesi
			deleteColumnFromScreen(columnXLocation(column_name), 10);
			
				for (int i = 2; i < 27; i+=6) {
					
					if (play_ground.sizeNumber(selectColumnName(i)) != 0) {
						
						x = i; y = 2;
						flag1 = true;
						break;
					}
			}
				
			if (flag1) {
			
				// Konsolu yeni yerine taþýr ve gösterir.
				String column = selectColumnName(x);
				ConsoleHelper.setColor(Color.WHITE, Color.GREEN);
				ConsoleHelper.print(play_ground.theValueOfTheLocation(column, x, y), x, y);
				ConsoleHelper.setColor(Color.WHITE, Color.BLACK);
			}	
		}
		
		return flag1;
	}
}
