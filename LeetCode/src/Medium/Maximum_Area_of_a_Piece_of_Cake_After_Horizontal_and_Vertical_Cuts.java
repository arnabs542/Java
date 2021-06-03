package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
/*
 * 	This is a Array problem
 * 	PS: I came up with the solution while bathing. Nice place to think of solution
 * 
 * 	The problem might seem a bit difficult, but after realizing one fact, it seems easy now:
 * 		>	Let's perform all verticalCuts on the cake first. What will you be left with?
 * 
 * 	The answer is stripes of cakes! If we were to take largest area, of course it is the one
 * 	with the largest gap between cuts!
 * 	Say I cut the cake at [1,3,8,10], the largest stripe is of width 5, because 8-3 is 5.
 * 	
 * 	To determine the width of largest gap cake, simply sort the cuts, and you can calculate the gaps
 * 	fairly easily by a linear iteration. Don't forget the both ends!
 * 
 * 	Now, forget about other stripes and focus only on the largest stripe of cake. Now I'll perform all horizontal
 * 	cuts. As you can see, now it is easy to find out the maximum block cake!
 */

public class Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts {
	
	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		final int lenH = horizontalCuts.length, lenV = verticalCuts.length;
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);
		
		int maxVert = 0, maxHorz = 0;
		
		for (int i = 0; i < lenH - 1; ++i)
			maxHorz = Math.max(maxHorz, horizontalCuts[i+1] - horizontalCuts[i]);
		for (int i = 0; i < lenV - 1; ++i)
			maxVert = Math.max(maxVert, verticalCuts[i+1] - verticalCuts[i]);
		
		//	Don't forget both ends.
		maxHorz = Math.max(horizontalCuts[0] - 0, maxHorz);
		maxHorz = Math.max(h - horizontalCuts[lenH-1], maxHorz);
		maxVert = Math.max(verticalCuts[0] - 0, maxVert);
		maxVert = Math.max(w - verticalCuts[lenV-1], maxVert);
		
		return (int)( (long)maxVert * maxHorz % 1000000007l);
    }
	
}
