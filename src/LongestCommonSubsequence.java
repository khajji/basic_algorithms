import java.util.ArrayList;
//This is an implementation of the Longest Common Subsequence Algorithm (LCS)
//Runtime: O(n*m) where n is the length of string 1 and m is length of string 2.
public class LongestCommonSubsequence {
	/**
	 * 
	 * @param a 
	 * @param b
	 * @return the longest common subequence between a and b. Example: a= "abcdaf", b="acbcf" --> result = "abcf"
	 */
	public static ArrayList<Character> lcs (String a, String b){
		int[][] lcs = new int[a.length()+1][b.length()+1];//The first column and row represent the longest common sequence when we have 0 characters		
		for (int i = 1; i < lcs.length; i++) {
			for (int j = 1; j < lcs[0].length; j++) {
				if (a.charAt(i-1)==b.charAt(j-1)) {			
					lcs[i][j]=lcs[i-1][j-1]+1;//if we have a common char then the longest subsequence is the longuest subsequence of the two strings without this character + 1 
				} else {
					lcs[i][j]= Math.max(lcs[i-1][j], lcs[i][j-1]); //the longest subsequence is the maximum between the longest subsequence of each of the two strings without the last character
				}
			}
		}	
		return collect(b, lcs, a.length(), b.length());		
	}
	
	private static ArrayList<Character> collect(String b, int[][]lcs, int i, int j){
		if (lcs[i][j]==0) {//we reached the end
			return new ArrayList<Character>();
		} else if (lcs[i][j]==lcs[i-1][j]){
			return collect(b, lcs, i-1, j);
		} else if (lcs[i][j]==lcs[i][j-1]){
			return collect(b,lcs,i,j-1);
		} else{//if the number came from the upper corner, it means that this character was selected in the optimal solution
			ArrayList<Character> temp = collect(b,lcs,i-1,j-1);
			temp.add(b.charAt(j-1));
			return temp;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LongestCommonSubsequence.lcs("abcdaf", "acbcf"));

	}

}
