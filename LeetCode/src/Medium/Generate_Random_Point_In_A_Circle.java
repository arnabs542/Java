package Medium;

//https://leetcode.com/problems/generate-random-point-in-a-circle/
/*
 * 	This is simply math probabilistic problem
 * 
 * 	You might immediately come up with a solution that just uses random() on both x and y, or
 * 	you might be more clever to simulate the circle process, by generating random angle and length.
 * 	However, those will fail to give random points distributed normally on the circle
 * 
 * 	What the question wants, is that the points generated to be preferrably equidistant from other
 * 	close points. When view the circle, the points should look uniform, and not clustered on one place
 * 
 * 	Why using pure random fails? Recall that the further the circle is from the center, the larger the
 * 	circumference. You might flatten the circumference into a straight line. Now, To have a uniformly
 * 	plotted points, the further away the circle, the more points has to be plotted!
 * 
 * 	Thus, if we can't use pure random(), what can we do? Recall square root function! In square root
 * 	of 0 to 1, The distribution starts out sparse, then only getting more frequent when approaching 1.
 * 
 * 	Normal random: * * * * * * * * * * * * * * * *
 * 	Sqrt random:   *     *     *   *   * * * * * *
 * 
 * 	Thus, we can use that property on our side to generate less points close to circle's center, and more
 * 	points near the outside of the circle!
 */

public class Generate_Random_Point_In_A_Circle {
	
	class Solution {
		double radius;
		double x_center;
		double y_center;
		
		public Solution(double radius, double x_center, double y_center) {
			this.radius = radius;
			this.x_center = x_center;
			this.y_center = y_center;
		}
		
		public double[] randPoint() {
			double length = Math.sqrt(Math.random() ) * radius;
			double angle = Math.random() * Math.PI;
			double offsetx = Math.sin( angle ) * length;
			double offsety = Math.cos( angle ) * length;
			
			return new double[] {x_center + offsetx, y_center + offsety};
		}
	}

}
