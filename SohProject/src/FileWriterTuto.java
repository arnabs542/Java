import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterTuto {

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);

		String input = scan.nextLine();
		String toWrite = input;

		while (!input.equals("")) {
			toWrite = toWrite.concat("\n");
			input = scan.nextLine();
			toWrite = toWrite.concat(input);
		}

		//Alternatively, use File as argument in the FileWriter constructor
		FileWriter fw = new FileWriter("D:\\My Desktop\\Javatext.txt");
		fw.write(toWrite);
		fw.close();
		System.out.println("File written!");
	}

}
