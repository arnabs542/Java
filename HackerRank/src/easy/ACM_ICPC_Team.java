package easy;

//https://www.hackerrank.com/challenges/acm-icpc-team/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	There is probably no work around here. Have to use algorithm of time complexity O(n2 * m) where m is number of topics and n is the players count
 * 
 */

public class ACM_ICPC_Team {

	static int[] acmTeam(String[] topic) {
		
		int count = 0;
		int maxtopics = 0;
		for (int i = 0; i < topic.length; i ++ ) {
			for (int j = i + 1; j < topic.length; j ++ ) {
				int localCount = 0;
				for (int t = 0; t < topic[0].length(); t ++ ) {
					if (topic[i].charAt(t) + topic[j].charAt(t) >= '1' + '0') localCount++;
				}
				if (localCount > maxtopics) {
					count = 1;
					maxtopics = localCount;
				}
				else if (localCount == maxtopics) count++;
			}
		}
		
		return new int[] {maxtopics, count};
	}
}
