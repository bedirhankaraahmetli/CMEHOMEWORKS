import java.util.Scanner;

public class Millionaire {

//returns a boolean value if the word is in array or not
public static boolean isStringInArray(String word, String[] words) {
	for(int i = 0; i < words.length; i++) {
		if(word.equalsIgnoreCase(words[i])) {
			return true;
		}
	}
	return false;

}

public static boolean isDigitOrLetter(char chr) {
	return Character.isDigit(chr) || Character.isAlphabetic(chr);
}

public static String insertCharIntoString(String str,String chr,int index) {
	return str.substring(0, index) + chr + str.substring(index);
}

public static String[] spellChecker(String[] dictionary, String[] q_words){
    String[] arr1 = dictionary; //Dictionary
    String[] arr2 = q_words; //Q-word

    Scanner scanner = new Scanner(System.in);

	for(int j = 0; j < arr2.length; j++){ //Iterate for every question word

		boolean word_escape = false;

		for (int k = 0; k < arr1.length; k++) {
			if(arr2[j].equalsIgnoreCase(arr1[k])){
				word_escape = true;
				break;
			}
		}

		String str = arr2[j].replaceAll("[0-9A-Z ]", " ");
		if(str.substring(0, 1).equals(" "))
			word_escape = true;

		if(!word_escape){
			for(int i = 0; i < arr1.length; i++){ //Iterate for every dictionary element
				int diff = 0;
				char wrong_char1 = ' '; //Placeholder letters
				char wrong_char2 = ' ';



				char[] word1 = arr1[i].toCharArray(); //Dictionary
				char[] word2 = arr2[j].toCharArray(); //Q-words
				int length = word1.length;



				if(word1.length > word2.length){ //Swap the length value in order to escape out of bound errors
					length = word2.length;
				}

				diff += Math.abs(word1.length - word2.length); //Add the difference in letter count

				for(int k = 0; k < length; k++){

					if(word1[k] != word2[k]){
						diff += 1;
						if(wrong_char1 == ' '){ //Save the letters that doesn't match with the dictionary
							wrong_char1 = Character.toLowerCase(word1[k]);
						}
						else{
							wrong_char2 = Character.toLowerCase(word2[k]);
						}

					}
				}

				if(arr1[i].endsWith(arr2[j]) && arr1[i].length() - arr2[j].length() == 1){
					System.out.println("Would you like to change " + arr2[j] + " to " + arr1[i] + " (y/n)");
					String answer = scanner.nextLine();
					if(answer.equalsIgnoreCase("y")){
						arr2[j] = arr1[i];
						break;
					}

				}
				else if(diff == 1){
					System.out.println("Would you like to change " + arr2[j] + " to " + arr1[i] + " (y/n)");
					String answer = scanner.nextLine();
					if(answer.equalsIgnoreCase("y")){
						arr2[j] = arr1[i];
						break;
					}
				}
				else if(diff == 2 && wrong_char1 == wrong_char2){
					System.out.println("Would you like to change " + arr2[j] + " to " + arr1[i] + " (y/n)");
					String answer = scanner.nextLine();
					if(answer.equalsIgnoreCase("y")){
						arr2[j] = arr1[i];
						break;
					}
				}
			}
		}

		

	}


    return arr2;
}

//gives random int number between bounds
public static int randomInt(int min, int max) {
	if(min == max) {
		return min;
	}
	else {
		return (int) (Math.random()*(max-min)+min);
	}
}

public static Participant[] get_participants(String participant_txt) {
	//array for each line in participants.txt
	String[] text_lines = FileOperations.arrayCreater(participant_txt);

	int participant_number = text_lines.length;

	Participant[] participants = new Participant[participant_number];

	for (int i = 0; i < participant_number; i++) {
		String[] temp = text_lines[i].split("#");
		participants[i] = new Participant(
				i,		//id
				temp[0], 	//name
				temp[1], 	//birthdate
				temp[2], 	//number a
				temp[3], 	//adress b
				false); 

	}
	return participants;

}



public static Question[] get_questions(String stop_word_txt, String question_txt) {
	//array for stop words
	String[] stop_words = FileOperations.arrayCreater(stop_word_txt);

	//array for each line in questions.txt
	String[] text_lines = FileOperations.arrayCreater(question_txt);

	int question_number = text_lines.length;

	//array for questions using by Question class
	Question[] questions = new Question[question_number];

	//assigning questions information from text_lines
	for (int i = 0; i < question_number; i++) {

		//splits each line to pieces
		String[] temp = text_lines[i].split("#");

		//assign question text to a variable
		String text_str = temp[1];


		//create a array for words
		String[] new_text_words_array = text_str.replaceAll("[^A-Za-z0-9ı ]", " ").split(" ");

		//select and keep the words
		String words_str = "";

		//select and keep the key words for using for word cloud
		String key_words_str = "";

		for (int j = 0; j < new_text_words_array.length; j++) {
			String word = new_text_words_array[j];

			if(!word.equals("")) {
				words_str += word + "-";
			}
		}

		String[] words_array = words_str.split("-");

		String[] editted_words_array = spellChecker(FileOperations.arrayCreater("dictionary.txt"), words_array);

		for (int j = 0; j < editted_words_array.length; j++) {
			String word = editted_words_array[j];
			boolean is_word_stop_word = isStringInArray(word, stop_words);
			if(!is_word_stop_word && !word.equals("")) {
				key_words_str += word + "-";
			}
		}
		String[] key_words_array = key_words_str.split("-");		 
		String question_text = "";
		for(int g= 0; g<editted_words_array.length; g++) {
			question_text+= editted_words_array[g];
		}
		
		String punctuations = "";
		for(int c = 0; c < text_str.length(); c++) {
			if(!isDigitOrLetter(text_str.charAt(c))) {
				punctuations += String.valueOf(text_str.charAt(c)) + c + "#";
			}
		}
		
		String[] punctuations_arr = punctuations.split("#");
		
		for(int a= 0; a < punctuations_arr.length; a++) {
			String chr = String.valueOf(punctuations_arr[a].charAt(0));
			int index = Integer.parseInt(punctuations_arr[a].substring(1));
			question_text = insertCharIntoString(question_text, chr, index);
		}
		

		questions[i] = new Question(
				i,		//id
				temp[0], 	//category
				question_text, 	//text
				temp[2], 	//choice a
				temp[3], 	//choice b
				temp[4], 	//choice c
				temp[5], 	//choice d
				temp[6], 	//correct answer
				Character.getNumericValue(temp[7].charAt(0)), 	//difficulty
				false, // is asked
				editted_words_array, //words in text
				key_words_array);	//key words in text
	}

	return questions;
}

public static int get_question_id(int Difficulty, String key_word,Question[] questions) {
	int id = 0;
	for (int i = 0; i < 1; i++) {
		int random_question = randomInt(0, questions.length-1);
		Question question = questions[random_question];
		if(isStringInArray(key_word, question.getKeyWords()) && 
				question.getDifficulty() == Difficulty &&
				question.getIsAsked() == false) {
			id = question.getID();
			
		}
		else {
			i--;
		}
	}
	return id;
}

public static String[] generate_word_cloud(int Difficulty, Question[] questions){


    Question[] temp_questions = questions;

    String[] temp_word_cloud = new String[15];

    for (int i = 0; i < 15; i++) {
        int random_question = randomInt(0, temp_questions.length-1);
        int random_key_word = randomInt(0, temp_questions[random_question].getKeyWords().length-1);
        String key_word = temp_questions[random_question].getKeyWords()[random_key_word];
        boolean flag = true;
        for (int j = 0; j < key_word.length(); j++) {
            if (Character.isDigit(key_word.charAt(j))) {
                flag = false;
            }
        }
        if(!isStringInArray(key_word, temp_word_cloud) && 
                !key_word.equals("") && 
                temp_questions[random_question].getDifficulty() == Difficulty &&
                temp_questions[random_question].getIsAsked() == false &&
                flag) 
        {
            temp_word_cloud[i] = key_word;
        }
        else {
            i--;
        }
    }

    return temp_word_cloud;
}

public static int getIndexOfArray(String word,String[] array) {
	for (int i = 0; i < array.length; i++) {
		if(word.equals(array[i])) {
			return i;
		}
	}
	return -1;
}

public static void print_statics(String[] statics, Question[] questions, Participant[] participants) {
	
	//the most succesfull contestant
	String[] contestants = new String[100];
	int last_int_1 = 0;
	int[] correct_answer_number = new int[100];
	int[] ages = new int[100];
	
	//the most correctly and badly answered category
	String[] categories = new String[100];
	int last_int_2 = 0;
	int[] correctly_answered = new int[100];
	int[] badly_answered = new int[100];
	
	//correct answer number per ages
	int under_30_total = 0;
	int under_30_num = 0;
	double under_30_avg = 0;
	int higher_50_total = 0;
	int higher_50_num = 0;
	double higher_50_avg = 0;
	int between_30_50_total = 0;
	int between_30_50_num = 0;
	double between_30_50_avg = 0;
	
	//the city that has the most contestant number
	String[] cities = new String[100];
	int last_int_4 = 0;
	int[] contestant_number = new int[100];	
	
	for (int i = 0; i < statics.length; i++) {
		String[] round_static = statics[i].split("#");
		int question_id = Integer.valueOf(round_static[0]);
		int contestant_id = Integer.valueOf(round_static[1]);
		int isAnsweredCorrectly = Integer.valueOf(round_static[2]);
		
		String contestant = participants[contestant_id].getName();
		String city = participants[contestant_id].getAdress().split(";")[3];
		int birth_year = Integer.valueOf(participants[contestant_id].getBirthDate().substring(6));
		int age = 2022 - birth_year;
		
		String category = questions[question_id].getCategory();
		//counting contestant number per city
		  if (!isStringInArray(city, cities)) {
	            cities[last_int_4] = city;
	            contestant_number[last_int_4] ++;
	            last_int_4++;
	        }
	        else if (!isStringInArray(contestant, contestants)) {
	            int index = getIndexOfArray(city, cities);
	            contestant_number[index] ++;
	        }
				
		//counting contestants correct answers
		if (!isStringInArray(contestant, contestants)) {
			contestants[last_int_1] = contestant;
			ages[last_int_1] = age;
			if (isAnsweredCorrectly == 1) {
				correct_answer_number[last_int_1] ++;
			}
			last_int_1++;
		}
		else {
			if (isAnsweredCorrectly == 1) {
				int index = getIndexOfArray(contestant, contestants);
				correct_answer_number[index] ++;
			}

		}
		
		//counting correct and wrong answers per category
		if(isStringInArray(category, categories)) {
			int index = getIndexOfArray(category, categories);
			if (isAnsweredCorrectly == 1) {
				correctly_answered[index] ++;
			}
			else {
				badly_answered[index] ++;
			}
		}
		else{
			categories[last_int_2] = category;
			if (isAnsweredCorrectly == 1) {
				correctly_answered[last_int_2] ++;
			}
			else {
				badly_answered[last_int_2] ++;
			}
			last_int_2++;
		}
		
		
		
	}
	
	//determining the most successfull contestant
	int max = -1;
	int index_1 = 0;
	for (int j = 0; j < correct_answer_number.length; j++) {
		if (correct_answer_number[j] > max) {
			max = correct_answer_number[j];
			index_1 = j;
		}
	}
	
	//determining the most correctly answered category
	max = -1;
	int index_2 = 0;
	for (int j = 0; j < correctly_answered.length; j++) {
		if (correctly_answered[j] > max) {
			max = correctly_answered[j];
			index_2 = j;
		}
	}
	
	//determining the most badly answered category
	max = -1;
	int index_3 = 0;
	for (int j = 0; j < badly_answered.length; j++) {
		if (badly_answered[j] > max) {
			max = badly_answered[j];
			index_3 = j;
		}
	}

	//determining the city that has the most highest number of participants
	max = -1;
	int index_4 = 0;
	for (int j = 0; j < contestant_number.length; j++) {
		if (contestant_number[j] > max) {
			max = contestant_number[j];
			index_4 = j;
		}
	}
	
	//determining average correct answer per ages
	for (int i = 0; i < correct_answer_number.length; i++) {
		if (ages[i] > 50) {
			higher_50_num ++;
			higher_50_total += correct_answer_number[i];
		}
		else if (ages[i] <= 50 && ages[i] > 30) {
			between_30_50_num ++;
			between_30_50_total += correct_answer_number[i];
		}
		else if(ages[i] <= 30 && ages[i] > 0){
			under_30_num ++;
			under_30_total += correct_answer_number[i];
		}
	}
	
	under_30_avg = (double) under_30_total/under_30_num;
	String str_u3a = String.valueOf(under_30_avg).substring(0, 3);
	between_30_50_avg = (double) between_30_50_total/between_30_50_num;
	String str_b35a = String.valueOf(between_30_50_avg).substring(0, 3);
	higher_50_avg = (double) higher_50_total/higher_50_num;
	String str_h5a = String.valueOf(higher_50_avg).substring(0, 3);
	
	System.out.println("The most successful contestant: " + contestants[index_1]);
	System.out.println("The category with the most correctly answered: " + categories[index_2]);
	System.out.println("The category with the most badly answered: " + categories[index_3]);
	System.out.println("Age<=30  " + str_u3a + "	30<Age<=50  " + str_b35a + "	Age>50  " + str_h5a);
	System.out.println("The city with the highest number of participants: " + cities[index_4]);
}

}