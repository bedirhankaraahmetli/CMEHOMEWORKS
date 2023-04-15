import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class FileOperations {

	public static String[] arrayCreater(String file_name){


		String[] array = new String[371000]; //Define variable 

		try{
			BufferedReader reader = new BufferedReader(new FileReader(file_name));
			String line = reader.readLine();
			int counter = 0;

			while(line != null){ //Add the current line into list until there is no new line
				array[counter] = line;
				line = reader.readLine();
				counter++;
			}
			reader.close();
		}
		catch(IOException e){ //Exception handling
		}

		int line_count = 0; //Determine the size of new array
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null){
				break;
			}
			line_count++;
		}

		String[] cleaned_array = new String[line_count]; 

		for (int i = 0; i < cleaned_array.length; i++) { //Transfer the array values
			cleaned_array[i] = array[i];
		}
		return cleaned_array;
	}

	public static String[][] arrayDismantler(String[] array_1d){
		String[][] array_2d = new String[array_1d.length][8];

		for(int i = 0; i < array_1d.length; i++){ //Loop until i reaches the question quantity
			String[] question = array_1d[i].split("#"); //Stripe the question from its seperator

			for(int j = 0; j < 8; j++){
				array_2d[i][j] = question[j]; //Fill the 2d array
			}
		}
		return array_2d;
	}

	public static String[][] arrayDismantler(String file_name){

		String[] array_1d = arrayCreater(file_name);
		String[][] array_2d = new String[array_1d.length][8];

		for(int i = 0; i < array_1d.length; i++){ //Loop until i reaches the question quantity

			String[] question = array_1d[i].split("#"); //Stripe the question from its seperator

			for(int j = 0; j < question.length; j++){
				array_2d[i][j] = question[j]; //Fill the 2d array
			}
		}
		return array_2d;
	}

	public static void writeToFile(String file_name, String text){
		try{
            File writer = new File(file_name);
			writer.createNewFile();
            FileWriter fw = new FileWriter(file_name, true);
            fw.write(text +"\n");
            fw.close();

        }
        catch(IOException e){
            System.out.println("A file error occured");
        }
	}

}

/*

! Bu Class'� kullanmak i�in dosyay� proje klas�r�ne koyman�z yeterli. Fonksiyonlar� ba�ka bir dosyadan �a��rmak i�in fonksiyonun ba��na
	"FileOperations." koyabilirsiniz


Fonksiyonlar:

> public static String[] arrayCreater(String file_name)
		*Herhangi bir text dosyas�n� okuyup her sat�r farkl� bir element olacak �ekilde string array'i olu�turur.

> public static String[][] arrayDismantler(String[] array_1d)
		*Bir boyutlu bir array'in i�indeki stringleri ay�ra�lar�ndan(#) ay�rarak 2 boyutlu bir array haline getirir.

		*array[soru_s�ras�][0] = Category
		*array[soru_s�ras�][1] = Text
		*array[soru_s�ras�][2] = Choice1
		*array[soru_s�ras�][3] = Choice2
		*array[soru_s�ras�][4] = Choice3
		*array[soru_s�ras�][5] = Choice4
		*array[soru_s�ras�][6] = CorrectAnswer
		*array[soru_s�ras�][7] = Difficulty

> public static String[][] arrayDismantler(String file_name)
		*arrayDismantler'in file_name ile �al��an hali.

*/