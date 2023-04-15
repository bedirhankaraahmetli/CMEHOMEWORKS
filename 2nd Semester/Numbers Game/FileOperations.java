import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

	public static DoubleLinkedList readFile (String location) throws IOException{
		
		FileReader fileReader = new FileReader(location);
		BufferedReader br = new BufferedReader(fileReader);
	
		DoubleLinkedList file_dll = new DoubleLinkedList();
		String line; String[] name_and_score;

		while ((line = br.readLine()) != null) {
			
			name_and_score = line.split("#");
			Player p = new Player() ;
			p.setPlayer_name(name_and_score[0]);
			p.setPlayer_score(Double.parseDouble(name_and_score[1]));
			file_dll.sortAddDll(p);
		}
		br.close();
		
		return file_dll;
	}

	public static void writeFile(String location, DoubleLinkedList dll) throws IOException {
		
		File file = new File(location);

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
       
        String str = dll.preparationForWriteFile();
        bWriter.write(str);
        bWriter.close();
	}
}

