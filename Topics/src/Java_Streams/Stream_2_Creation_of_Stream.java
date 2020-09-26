package Java_Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.*;

public class Stream_2_Creation_of_Stream {
	
/*
 * 	Stream.of()
 * 
 * 	The most easiest way to create a stream is to use its class function Stream.of(), 
 *  which takes in variable length arguments, and return a stream
 */
static void S2_Example1() {
	Stream.of("a1", "a2", "a3")
		.findFirst()
		.ifPresent( x -> System.out.println("Stream of: " + x) );
}


/*	
 * 	List.stream()
 * 
 * 	Java had implemented stream into Collections interface. Therefore we can create a stream
 * 	directly from a List, or equivalent
 */
static void S2_Example2() {
	String[] str = {"a1", "a2", "a3" };
	
	Arrays.asList( str )
		.stream()
		.findAny()
		.ifPresent( x -> System.out.println("List stream: " + x) );
	
	Set<String> set = new HashSet<>( Arrays.asList(str) );
	set.stream()
		.findAny()
		.ifPresent( x -> System.out.println("Set Stream: " + x) );
}


/*
 * 	Arrays.stream()
 * 
 * 	To stream an array, we could just use the Arrays class's stream() function
 * 	The Array's stream() function has been overloaded so for primitive type arrays, it will return
 * 	the stream specialized for that primitive type, like double, int, long
 */
static void S2_Example3() {
	int[] arr = {1,2,3,4,5,6,7,8,9,0};
	
	int sum = Arrays.stream( arr )
		.filter(x -> x > 5)
		.sum();				//Only exist in IntStream!
	
	System.out.println("Sum is " + sum);
}


/*
 * 	Primitive Type Streams
 * 
 * 	Java provides specialized stream for 3 primitive types: Double, Int, Long.
 * 	Those streams, apart from functioning like exactly normal streams, have some added functionalities
 * 	like summaryStatistics(), sum(), max() etc...
 * 
 * 	Do note that except summaryStatistics(), sum() and max() etc are intermediate operations, returning
 * 	once again another stream
 * 	
 * 	>	IntStream
 * 	>	LongStream
 * 	>	DoubleStream
 */
static void S2_Example4() {
	
	IntStream.range(1, 50)			//Stream from 1 to 9. 10 is exclusive
		.skip(25)					//skip() skips over specified number of iterations
		.forEach(x -> System.out.print(x + " ") );
	System.out.println();
	
	int[] a = {1,2,3,4,5};
	
	System.out.println(
		IntStream.of(a)				//Arrays can also be streamed using their specialized stream
			.summaryStatistics()	
									//THose streams has a special function summaryStatistics
									//which show some basic statistics of the numbers
	);
	
	double[] arr = {1.2, 3.4, 6.4};
	
	System.out.println( 
		DoubleStream.of(arr)
			.summaryStatistics() 
	);
}




/*
 * 	Files.lines()
 * 
 * 	We can create streams from text files. This requires us to use the function Files.lines()
 * 	which we have to pass in the path object, then it will return a Stream of strings.
 * 
 * 	Since it involves IO operation, it will require us to either declare a 'throw IOException', or
 * 	just wrap it in try catch
 */
static void S2_Example5() throws IOException {
	//	By default from Eclipse, '.' is Topics directory
	Path path = Paths.get("./src/Java_Streams/bands.txt");
	
	Files.lines( path )
		.filter(x -> x.length() > 10 )
		.forEach( System.out::println );
}




/*
 * 	CSV Files As Well!
 * 
 * 	Using the above technique, handling CSV files can be done in a much similar fashion, just
 * 	need to split the comma (,)
 */
static void S2_Example6() throws IOException {
	Path path = Paths.get("./src/Java_Streams/data.csv");
	
	Files.lines( path )
		.map(str -> str.split(",") )		//Split one line of csv string into an array of string
		.filter(arr -> arr.length == 3)		//Filter bad data
		.mapToDouble(arr -> Double.parseDouble( arr[2] ) )		//Obtain each CGPA
		.average()									//Remember average() is intermediate operation, not terminal
		.ifPresent( avg -> System.out.printf("Average CGPA of students: %.2f", avg) );
}
	
 public static void main(String[]args) throws IOException {
	 S2_Example5();
 }
	

}
