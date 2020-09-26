package Java_Streams;

import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 	One core properties of streams are: Intermediate operations are lazy.
 * 	Each element is processed one by one, passed down into the operation pipeline one by one,
 * 	without completing any operation first
 * 
 * 	Say we have 3 operations: A B C
 * 	and 3 elements: 1 2 3.
 * 
 * 	Instead of having all 1 2 3 being operated by A, then B, then C, how streams work are:
 * 	1 will be operated A, B, C, then only B will be operated A, B, C, and only 3 comes last
 */

public class Stream_3_Processing_Order {

/*
 * 	PRESENCE OF TERMINAL OPERATION
 * 
 *  Intermediate operation will only execute when the terminal operation is present. Otherwise, no
 * 	data will be processed.
 */
static void S3_Example1() {
	
	int[] arr = {1,2,3,4,5};
	
	IntStream is = IntStream.of(arr)
			.filter(x -> x > 1)
			.map(x -> x + 3);		//	NO elements will be processed here. arr will still be 1,2,3,4,5
	
	is.forEach( System.out::println);   //Only at this line, the filter, map and forEach will be executed!
}



/*
 *  TERMINATION IN THE MIDDLE
 *  
 *  The workflow of java streams allow for performance optimizations! As soon as an operation is
 *  terminatable, the rest of the data won't even be processed! This decreases overall workload and
 *  thus, better performance
 */
static void S3_Example2() {
	AtomicInteger iteration = new AtomicInteger(0);
	
	IntStream.range(1, 50)
		.peek( x -> System.out.print(iteration.getAndAdd(1) + " ") )    //peek() is like forEach, except 
																		//it is not terminal operation
		.anyMatch( x -> x > 25);		//	As soon as 26 hits this line, the stream is closed!
}



/*
 * 	DECREASE WORKLOAD
 * 
 * 	Imagine we have processed a ton of data, only to realized then that half of the data needs to be 
 * 	discarded?
 * 	A rule of thumb in functional programming is to filter and discard unwanted data prior to processing
 * 	those. In Java, always filter() before performing other operations.
 */
static void S3_Example3() {
	
	int[] arr = {2,63,10,65, 93, 100, 24};
	
	System.out.println( 
		Arrays.stream(arr)
			.filter(x -> x > 25)		//Those elements that are less than 25 will be blocked by this line
										//Therefore they will not be involved in subsequent operations
			.summaryStatistics()
	);
}






/*
 *	A LITTLE EXCEPTION
 *
 *	All of the operations are lazy, except for some...
 *
 *	Intermediate operations like sorted() are special intermediate operations. It is a stateful operation,
 *	which requires maintaining a state to operate. All data must be all gathered before this sorted() function
 *	does it work, then only the pipeline operation continues.
 *
 *	sorted() is executed horizontally, and will effect all elements passed from the prior operation. Therefore
 *	still, sorted() shall be done after filtering or discarding unwanted datas, to boost performance!
 */
static void S3_Example4() {
	String[] arr = {"Apple", "Banana", "Cherry", "Durian", "Edamane" };
	
	List<String> li = Arrays.stream( arr )
		.filter( x -> !x.startsWith("E") )
		.peek( x -> System.out.println("Filtered: " + x) )
		.map( x -> x.toLowerCase() )
		.peek( x -> System.out.println("Lowercased: " + x) )
		.sorted( (x,y) -> {											//You will see all elements are sorted
			System.out.println("Sorting " + x + " and " + y);		//at once, not like prior "one element
			return x.compareTo(y);									//at a time" mode
		})
		.collect( Collectors.toList() );
	
	System.out.println( li );
}




public static void main(String[]args) {
	S3_Example4();
}
	
}
