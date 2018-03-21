package CongDiem2;

import java.util.Scanner;

public class Test {

	private static TrieNode curNode;

	public static void insertWord(TrieNode root, String word) { // create tree
		char[] letters = word.toCharArray();
		curNode = root;
		for (int i = 0; i < letters.length; i++) {
			if (curNode.links[letters[i] - 97] == null) {
				curNode.links[letters[i] - 97] = new TrieNode(letters[i]);
			}
			curNode = curNode.links[letters[i] - 97];
		}
		curNode.fullWord = true;
	}

	public static boolean find(TrieNode root, String word) { // search
		char[] letters = word.toCharArray();
		curNode = root;
		for (int i = 0; i < letters.length; i++) {
			if (curNode.links[letters[i] - 97] == null) {
				return false;
			}
			curNode = curNode.links[letters[i] - 97];
		}
		return true;
	}

	public static void print(TrieNode root, int level, char[] branch, String search) { // print result
		if (root == null)
			return;
		for (int i = 0; i < root.links.length; i++) {
			branch[level] = root.letter;
			print(root.links[i], level + 1, branch, search);
		}
		if (root.fullWord) {
			System.out.print(search);
			for (int i = 1; i <= level; i++)
				System.out.print(branch[i]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TrieNode tree = new TrieNode('\0');
		String[] words = { "an", "and", "ant", "add", "all", "allot", "alloy", "aloe", "are", "ate", "heart", "hear",
				"heard", "be", "bee", "born", "father", "mother", "daughter", "under", "uncle", "unknown", "know",
				"knee", "knife", "interesting", "interested", "am", "is", "are", "test", "demo", "was", "were" };
		for (int i = 0; i < words.length; i++) {
			insertWord(tree, words[i]);
		}

		System.out.print("Input a word: ");
		String search = sc.nextLine();
		sc.close();
		if (find(tree, search)) {
			System.out.println("The word was found!");
			print(curNode, 0, new char[50], search);
		} else {
			System.out.println("The word was NOT found!");
		}
	}
}

class TrieNode {
	char letter;
	TrieNode[] links;
	boolean fullWord;

	public TrieNode(char letter) {
		this.letter = letter;
		fullWord = false;
		links = new TrieNode[26];
	}
}
