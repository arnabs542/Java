package Misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedWriter;

public class CSVFiles {
	
	public static void main(String[]args) throws IOException {
		//Opens the csv file prepared in the following path
		File file = new File("src/Resources/Crimes.csv");
		//Use a bufferedReader as our reader instead of Scanner as it is more efficient. BufferedReader does not do parsing of the data
		//while Scanner does
		BufferedReader br = new BufferedReader( new FileReader(file) );
		
		//Creates a stream of strings out of bufferedReader and map each of them so that each line get split into array of individual values
		List<String[]> arr = br.lines().skip(1).map(str -> str.split(",") ).collect( Collectors.toList() );
		br.close();
		
		//df is the date format used in the original read csv file
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm");
		//dfTo is the date format that we will convert to when writing the file
		DateFormat dfTo = new SimpleDateFormat("dd/MMM/yyyy hh.mm a");
		//For each of the string array (containing all data), take the date value and convert them to the new date format
		arr.forEach( s -> {
			try {
				s[0] = dfTo.format( df.parse(s[0] ) );
			} catch (ParseException e) {
				System.out.println("Parsing failed. Date format error");
			}
		});
		
		//Open a bufferedWriter to write a text file. It will consist of formatted date time, crime description and location
		BufferedWriter bw = new BufferedWriter( new FileWriter("src/Resources/CrimesV2.0.txt") );
		for (String[] s: arr) {
			bw.write("At " + s[0] + ", " + s[5] + "\t\t" + s[1] );
			//bufferedWriter does not include new line by itself so we will put that ourselves
			bw.newLine();
		}
		bw.close();
	}
}
