package easy;

public class Utopian_Tree {
	
	static int utopianTree(int n) {
		int treeheight = 1;
		for (int i = 1; i <= n; i ++ ) {
			if (i % 2 != 0)
				treeheight *= 2;
			else
				treeheight++;
		}
		return treeheight;
    }
	
}
