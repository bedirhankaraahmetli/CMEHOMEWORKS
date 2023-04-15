import java.util.*;
import java.awt.Color;
import java.io.File;

import enigma.core.Enigma;

public class Game {
	
	
	
	
	public static void main(String[] args) throws InterruptedException 
		{

					enigma.console.Console cn = Enigma.getConsole("console", 200, 50, 15);
					EnigmaWrapper wrapper = new EnigmaWrapper(cn);
					InfoBox box = new InfoBox(cn);
					Thread thread = new Thread(box);
					String[] statics = {}; 
					Scanner scanner = new Scanner(System.in);
					Question[] questions = new Question[0];
					Participant[] participants = new Participant[0];
					File file = new File("result.txt");
					file.delete();
					boolean flag = true;
					wrapper.consoleColor(Color.orange, Color.black);
					wrapper.splashScreen();
					wrapper.clearConsole();
					do {
						
						
							int difficulty = 1;
							wrapper.encodeWriting("***** Menu *****\r\n"
									+ "1.Load questions\r\n"
									+ "2.Load participants\r\n"
									+ "3.Start competition \r\n"
									+ "4.Show statistics\r\n"
									+ "5.Exit\r\n"
									+ "\n" +
									"Please make a choice: ");
							int choice = scanner.nextInt();
							wrapper.clearConsole();

							switch (choice) {
							case 1: {

								System.out.print("Please enter the file name to load for questions: ");
								String fileName_1 = scanner.next();
								wrapper.clearConsole();
								System.out.println("The file is loaded.");
								questions = Millionaire.get_questions("stop_words.txt", fileName_1);

								String[] categories = new String[questions.length];
								int index = 0;
								for (int i = 0; i < questions.length; i++) {
									categories[index] = questions[i].getCategory();
									for (int j = 0; j < categories.length; j++) {
										if (j != index && categories[index].equals(categories[j])) {
											categories[j] = null;
										}
									}
								index++;


									}
								System.out.println("Category\t\t\tThe Number of Questions");
								System.out.println("--------\t\t\t-----------------------");

								int count;

								for (String category : categories) {
									count = 0;
									if (category == null)
										continue;
									for (Question quest : questions) {
										if (quest.getCategory().equals(category))
											count++;
									}
									System.out.print(category);
									for (int i = 0; i < 30-category.length(); i++) {
										System.out.print(" ");
									}
									System.out.println(count);

								}
								System.out.println();



								int diffcounter1 = 0;
								int diffcounter2 = 0;
								int diffcounter3 = 0;
								int diffcounter4 = 0;
								int diffcounter5 = 0;
								for (int i = 0; i < questions.length; i++) {

									if (questions[i].getDifficulty() == 1) {
										diffcounter1 ++;
									}else if (questions[i].getDifficulty() == 2) {
										diffcounter2 ++;
									}
									else if (questions[i].getDifficulty() == 3) {
										diffcounter3 ++;
									}
									else if (questions[i].getDifficulty() == 4) {
										diffcounter4 ++;
									}
									else if (questions[i].getDifficulty() == 5) {
										diffcounter5 ++;
									}
								}
								System.out.println();
								System.out.println("Difficulty level \t The number of questions");
								System.out.println("---------------- \t -----------------------");
								System.out.println("\t\t1 \t\t\t\t    " + diffcounter1);
								System.out.println("\t\t2 \t\t\t\t    " + diffcounter2);
								System.out.println("\t\t3 \t\t\t\t    " + diffcounter3);
								System.out.println("\t\t4 \t\t\t\t    " + diffcounter4);
								System.out.println("\t\t5 \t\t\t\t    " + diffcounter5);
								System.out.println();
								System.out.print("Do you want to load participants?(Y for Yes, N for No): ");
								String continue_ = scanner.next();
								wrapper.clearConsole();
								if (continue_.equalsIgnoreCase("y")) {
									choice = 2;
								}else {
									break;
								}


							}
							case 2: {
								System.out.print("Please enter the file name to load for participants: ");
								String fileName_2 = scanner.next();
								wrapper.clearConsole();
								participants = Millionaire.get_participants(fileName_2);
								System.out.println("The file is loaded.");
								System.out.println();
								System.out.print("Do you want to start competition?(Y for Yes, N for No): ");
								String continue_ = scanner.next();
								wrapper.clearConsole();
								thread.start();
								if (continue_.equalsIgnoreCase("y")) {
									choice = 3;
								}else {
									break;
								}
							}
							case 3: {

								if (questions.length == 0 || participants.length == 0) {
	                                System.out.println("First load the files, please.\n");
	                                break;
	                            }	
								
								String contuniue = "y";
								while(contuniue.equalsIgnoreCase("y")) {
									String  contestant = participants[0].Name;
									box.setIs50Used(false);
									box.setIsDoubleDipUsed(false);
									int random_contestant = Millionaire.randomInt(0, participants.length);
									while(participants[random_contestant].getisPlayed() == false) {
										contestant = participants[random_contestant].Name;
										participants[random_contestant].isPlayed = true;
									}

									System.out.println();
									box.setMoney(0);
									File temp = new File("temp.txt");
									temp.delete();
									for (int i = 1; i < 6; i++) {
										difficulty = i;
										System.out.println("Contestant: " + contestant);
										System.out.println("-----------------------------------------------------------");
										String[] word_Cloud = Millionaire.generate_word_cloud(difficulty, questions);
										System.out.println("Word Cloud:\n\n");
										for (int j = 0; j < word_Cloud.length; j++) {

												System.out.print(word_Cloud[j] + "\t");

										}

										System.out.println();
										System.out.println("> Enter your selection: ");
										String selection = scanner.next();
										wrapper.clearConsole();

										box.setThreadInterrupt(true);

										int id = 0;
										boolean is_time_run_out = false;		
										id = Millionaire.get_question_id(difficulty, selection, questions);
										System.out.println("Q" + i + ") " + questions[id].getText());
										System.out.println("A) " + questions[id].getChoiceA());
										System.out.println("B) " + questions[id].getChoiceB());
										System.out.println("C) " + questions[id].getChoiceC());
										System.out.println("D) " + questions[id].getChoiceD());

										System.out.println("Enter your choice (E:Exit): ");
										box.setThreadInterrupt(true);
										String answer = scanner.next();
										box.setThreadInterrupt(false);

										String temps[][] = FileOperations.arrayDismantler("temp.txt");
										for (int j = 0; j < temps.length; j++) {
											if(temps[j][0].equals("is_time_run_out")){
												is_time_run_out = true;
											}
										}


										if(is_time_run_out){
											Thread.sleep(1000);
											wrapper.clearConsole();
											break;
										}

										if (answer.equalsIgnoreCase("%50") && box.getIs50Used() == false) {
											int random = Millionaire.randomInt(1, 3);
											box.setIs50Used(true);
											if (questions[id].getCorrectAnswer().equalsIgnoreCase("a")) {
												
												switch (random) {
												case 1: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) " + questions[id].getChoiceA());
													System.out.println("B) " + questions[id].getChoiceB());
													System.out.println("C) ");
													System.out.println("D) ");

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);
													break;
												}
												case 2: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) " + questions[id].getChoiceA());
													System.out.println("B) ");
													System.out.println("C) " + questions[id].getChoiceC());
													System.out.println("D) ");

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
												case 3: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) " + questions[id].getChoiceA());
													System.out.println("B) ");
													System.out.println("C) ");
													System.out.println("D) " + questions[id].getChoiceD());

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
											}
											}else if (questions[id].getCorrectAnswer().equalsIgnoreCase("b")) {
												switch (random) {
												case 1: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) " + questions[id].getChoiceA());
													System.out.println("B) " + questions[id].getChoiceB());
													System.out.println("C) ");
													System.out.println("D) ");

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
												case 2: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) ");
													System.out.println("B) " + questions[id].getChoiceB());
													System.out.println("C) " + questions[id].getChoiceC());
													System.out.println("D) ");

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
												case 3: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) ");
													System.out.println("B) " + questions[id].getChoiceB());
													System.out.println("C) ");
													System.out.println("D) " + questions[id].getChoiceD());

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
											}
											}else if (questions[id].getCorrectAnswer().equalsIgnoreCase("c")) {
												switch (random) {
												case 1: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) " + questions[id].getChoiceA());
													System.out.println("B) ");
													System.out.println("C) " + questions[id].getChoiceC());
													System.out.println("D) ");

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);
													break;
												}
												case 2: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) ");
													System.out.println("B) " + questions[id].getChoiceB());
													System.out.println("C) " + questions[id].getChoiceC());
													System.out.println("D) ");

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);
													break;
												}
												case 3: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) ");
													System.out.println("B) ");
													System.out.println("C) " + questions[id].getChoiceC());
													System.out.println("D) " + questions[id].getChoiceD());

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
											}
											}else if (questions[id].getCorrectAnswer().equalsIgnoreCase("d")) {
												switch (random) {
												case 1: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) " + questions[id].getChoiceA());
													System.out.println("B) ");
													System.out.println("C) ");
													System.out.println("D) " + questions[id].getChoiceD());

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);			
													break;
												}
												case 2: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) ");
													System.out.println("B) " + questions[id].getChoiceB());
													System.out.println("C) ");
													System.out.println("D) " + questions[id].getChoiceD());

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
												case 3: {
													wrapper.clearConsole();
													System.out.println("Q" + i + ") " + questions[id].getText());
													System.out.println("A) ");
													System.out.println("B) ");
													System.out.println("C) " + questions[id].getChoiceC());
													System.out.println("D) " + questions[id].getChoiceD());

													System.out.println("Enter your choice (E:Exit): ");
													box.setThreadInterrupt(true);
													answer = scanner.next();
													box.setThreadInterrupt(false);	
													break;
												}
											}
											}


										}else if (answer.equalsIgnoreCase("%50") && box.getIs50Used() == true) {
											System.out.println("You can't use %50 twice!!\n");
											System.out.println("Next contestant? (Y/N)");
											contuniue = scanner.next();
											wrapper.clearConsole();
											break;
											
										}
										if (answer.equalsIgnoreCase("double") && box.getIsDoubleDipUsed() == false) {
											answer = " ";
											box.setIsDoubleDipUsed(true);
											wrapper.clearConsole();
											System.out.println("Q" + i + ") " + questions[id].getText());
											System.out.println("A) " + questions[id].getChoiceA());
											System.out.println("B) " + questions[id].getChoiceB());
											System.out.println("C) " + questions[id].getChoiceC());
											System.out.println("D) " + questions[id].getChoiceD());

											System.out.println("Enter your choice (E:Exit): ");
											box.setThreadInterrupt(true);
											
											answer = scanner.next();
											box.setThreadInterrupt(false);
											if (!answer.equalsIgnoreCase(questions[id].getCorrectAnswer())) {
												if (answer.equalsIgnoreCase("a")) {
													questions[id].setChoiceA("");
												}else if (answer.equalsIgnoreCase("b")) {
													questions[id].setChoiceB("");
												}else if (answer.equalsIgnoreCase("c")) {
													questions[id].setChoiceC("");
												}else if (answer.equalsIgnoreCase("d")) {
													questions[id].setChoiceD("");
												}
												wrapper.clearConsole();
												System.out.println("Q" + i + ") " + questions[id].getText());
												System.out.println("A) " + questions[id].getChoiceA());
												System.out.println("B) " + questions[id].getChoiceB());
												System.out.println("C) " + questions[id].getChoiceC());
												System.out.println("D) " + questions[id].getChoiceD());

												System.out.println("Enter your second choice (E:Exit): ");
												box.setThreadInterrupt(true);
												answer = scanner.next();
												box.setThreadInterrupt(false);
											}


										}else if (answer.equalsIgnoreCase("double") && box.getIsDoubleDipUsed() == true) {
											System.out.println("You can't use double dip twice!!\n");
											System.out.println("Next contestant? (Y/N)");
											contuniue = scanner.next();
											wrapper.clearConsole();
											break;
										}
										if (answer.equalsIgnoreCase(questions[id].getCorrectAnswer())) {
											System.out.println("Correct answer!");
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											String result = questions[id].getID() + "#" + participants[random_contestant].ID + "#" + 1; 
											FileOperations.writeToFile("result.txt", result);
											if (i == 1) {
												box.setMoney(20);
											}else if (i == 2) {
												box.setMoney(100);
											}else if (i == 3) {
												box.setMoney(250);
											}else if (i == 4) {
												box.setMoney(500);
											}else if (i == 5) {
												box.setMoney(1000);
												System.out.println("Congratulations! You won $1,000,000! Game Over!");
												System.out.println("Next contestant? (Y/N)");
												contuniue = scanner.next();
												break;
											}
										}else if (answer.equalsIgnoreCase("e") || answer.equals(" ")) {
											wrapper.clearConsole();
											if (i == 1) {
												System.out.println("The game is over! You won $0");
											}else if (i == 2) {
												System.out.println("The game is over! You won $20,000");
											}else if (i == 3) {
												System.out.println("The game is over! You won $100,000");
											}else if (i == 4) {
												System.out.println("The game is over! You won $250,000");
											}else if (i == 5) {
												System.out.println("The game is over! You won $500,000");
											}
											System.out.println("Next contestan? (Y/N)");
											contuniue = scanner.next();
											if (contuniue.equalsIgnoreCase("n")) {
												choice = 4;
												break;
											}else if (contuniue.equalsIgnoreCase("y")) {
												choice = 3;
												break;
											}
											
											

										}else if (!(answer.equalsIgnoreCase(questions[id].getCorrectAnswer())) ||
													!(answer.equalsIgnoreCase("%50")) ||
													!(answer.equalsIgnoreCase("double dip"))){
											String result = questions[id].getID() + "#" + participants[random_contestant].ID + "#" + 0; 
											FileOperations.writeToFile("result.txt", result);
											if(!box.getIsTimeRunOut())
												System.out.println("Wrong Answer! GAME OVER!");
											if (i == 3 || i == 4) {
												System.out.println("You won $100,000!");
											}else if (i == 5) {
												System.out.println("You won $500,000");
											}
											System.out.println("Next contestant? (Y/N)");
											contuniue = scanner.next();
											if (contuniue.equalsIgnoreCase("n")) {
												System.out.print("Do you want to see statictis?(Y for Yes, N for No): ");
												String continue_ = scanner.next();
												wrapper.clearConsole();
												if (continue_.equalsIgnoreCase("y")) {
													break;
												}else {
													choice = 4;
												}
												
											}else {
												wrapper.clearConsole();
												break;
											}
											
										}

										wrapper.clearConsole();
									}

								}		

							}
							case 4: {
								if (questions.length == 0 || participants.length == 0) {
	                                System.out.println("First load the files, please.\n");
	                                break;
	                            }
								statics = FileOperations.arrayCreater("result.txt");
								Millionaire.print_statics(statics, questions, participants);
								System.out.println();
								System.out.print("Do you want to return to menu?(Y for Yes, N for No): ");
								String continue_ = scanner.next();
								wrapper.clearConsole();
								if (continue_.equalsIgnoreCase("n")) {
									choice = 5;
								}else {
									break;
								}
							}
							case 5: {
								scanner.close();
								flag = false;
							}

							}
						
						

						} while (flag);

				}
		}