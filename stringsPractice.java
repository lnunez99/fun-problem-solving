import java.util.*;
import java.lang.Math;
public class stringsPractice {


	/*  Implement a solution to check if a string has all unique characters.

	 *  Solution:
	 *  I can iterate through the string and remember if I have seen it before.
	 *  Each time I read a character, I can check if it has been seen.
	 *  Once a character has been repeated, I can stop right there instead
	 *  of waiting to check at the end.
	 *  I can do this with a HashMap of <Character, Boolean> pairs.
	 *  Where the Boolean value refutes/supports the statement "this character has been seen".
	 *
	 *  1. Take in the string
	 *  	Strings of length 1 are unique - pass.
	 *  	Strings of length 2 can have its characters compared with brute force.
	 *  		if s[0] == s[1] ==> false
	 *
	 *  Time to iterate:
	 *  2. Check if the map contains the character at index i.
	 *  	If  NOT , add it with 'true'
	 *  	Else
	 *  		it has been read before, return false.
	 *   */
	public static Boolean isUnique(String s) {

		int length = s.length();
		HashMap<Character, Boolean> charRecord = new HashMap<Character, Boolean>();

		if (length == 0)
			return false;
		else if (length == 1)
			return true;
		else if (length == 2) {
			if (s.charAt(0) != s.charAt(1))
				return false;
		}

		for (int i = 0; i < length; i++)
		{
			if (!charRecord.containsKey(s.charAt(i)))
				charRecord.put(s.charAt(i), true);
			else
				return false;
		}
		return true;

	}

	/* oneOrZero
	 *
	 *
	 * There are 3 types of edits that can be performed on strings;
	 * insert
	 * remove
	 * replace
	 *
	 * Given two strings write a function to check if they are one edit
	 * or zero edits away
	 *
	 * e.g
	 * pale, ple -> True
	 * pales, pale ->  true
	 * pale, bale -> true
	 * pale, bake -> false
	 *

	 * PLAN:
	 *
	 * Get the set of s1's characters s1 = {p a l e}
	 * set of s2's s2 = {p l e}
	 * {p l e}
	 * The intersection of s1 and s2 should be different by one (in size)
	 *
	 * In the case of a differing length like
	 * pale
	 * bkl
	 * {b a l e} {b k l}
	 * { b l}
	 * int = 1 in length.
	 *
	 *
	 */
	public static Boolean oneOrZero(String s1, String s2) {

		Set<Character> set1 = new HashSet<Character>();
		Set<Character> set2 = new HashSet<Character>();

		for(int i = 0; i < s1.length(); i++)
			set1.add(s1.charAt(i));

		for(int i = 0; i < s2.length(); i++)
			set2.add(s2.charAt(i));

		// Two equal strings require no edits.
		if (s1.equals(s2))
			return true;

		// Find the difference in length.
		int deltaLength = Math.abs(s1.length() - s2.length());

		/* Determine one edit away situation
		 * based on a length difference of 1.*/

		if ( deltaLength == 1)
		{
			if (set1.size() > set2.size())
			{
				set1.retainAll(set2); // set1 becomes the intersection.
				if (Math.abs(set1.size() - set2.size()) != 0) return false;
			}
			else if (set1.size() < set2.size()) {
				set2.retainAll(set1);	// set2 becomes the intersection.
				if (Math.abs(set1.size() - set2.size()) != 0) return false;
			}
		}
		/* Equal length situation:
		 * 	Must only differ by one character.*/
		else if (deltaLength == 0){
			set1.retainAll(set2); // intersection
			if (Math.abs(set1.size() - set2.size()) != 1)
				return false;
		}
		else
			return false; // Too great of a difference.

		return true;
	}

	public static void main(String[] args) {

		String s = "pale";
		String s1 = "bake";
		System.out.println(oneOrZero(s, s1));
		System.out.println(isUnique(s));
		System.out.println(isUnique("not unique"));


	}

}
