package musicFileCleaner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;



public class fileReader {

	public static void main(String[] args) {

		String dirname = args[0];
		if(dirname.isEmpty() || dirname == null) {
			System.out.println("Please provide directory path!");
			return;
		}
		File dir = new File(dirname);
		File[] files = dir.listFiles();

		for (File file : files) {
			String fname = file.getName();
			if (fname.contains("(") || fname.contains("[")) {
				
				Path yourFile = Paths.get(dirname + "/" + fname);
				try {
					Files.move(yourFile, yourFile.resolveSibling(removeBrackets(fname)), REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println(file.getName());
			}
		}
	}

	public static String removeBrackets(String str) {

		if (str.contains("(") || str.contains(")")) {
			str = removeRoundBrackets(str);
		}
		if (str.contains("[") || str.contains("]")) {
			str = removeSquareBrackets(str);
		}

		return str.replaceAll("\\s+", " ");

	}

	public static String removeRoundBrackets(String str) {

		if (bracketsBalanced(str)) {
			int startIndex = str.indexOf("(");
			int endIndex = str.indexOf(")");
			String chop = str.substring(startIndex, (endIndex + 1));
			String innerChop = chop.substring(1, chop.length() - 1);
			if (innerChop.contains("(")) {
				// this part is for second... level brackets
				endIndex = str.indexOf(")", endIndex + 1);
				while (!bracketsBalanced(chop)) {
					chop = str.substring(startIndex, (endIndex += 1));
				}
			}
			str = str.replace(chop, "");
			if (str.contains("(") || str.contains(")")) {
				str = removeRoundBrackets(str);
			}
		} else {

		}
		return str;
	}

	public static String removeSquareBrackets(String str) {
		if (bracketsBalanced(str)) {
			int startIndex = str.indexOf("[");
			int endIndex = str.indexOf("]");
			String chop = str.substring(startIndex, (endIndex + 1));
			String innerChop = chop.substring(1, chop.length() - 1);
			if (innerChop.contains("[")) {
				// this part is for second... level brackets
				endIndex = str.indexOf("]", endIndex + 1);
				while (!bracketsBalanced(chop)) {
					chop = str.substring(startIndex, (endIndex += 1));
				}
			}
			str = str.replace(chop, "");
			if (str.contains("[") || str.contains("]")) {
				str = removeSquareBrackets(str);
			}
		} else {

		}
		return str;
	}

	public static boolean bracketsBalanced(String str) {
		boolean balanced = false;
		HashMap<Character, Integer> bracketFreq = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				if (bracketFreq.get('(') == null) {
					bracketFreq.put('(', 0);
				} else {
					bracketFreq.put('(', (bracketFreq.get('(') + 1));
				}
			}
			if (str.charAt(i) == ')') {
				if (bracketFreq.get(')') == null) {
					bracketFreq.put(')', 0);
				} else {
					bracketFreq.put(')', (bracketFreq.get(')') + 1));
				}
			}
		}

		if (bracketFreq.get('(') == bracketFreq.get(')')) {
			balanced = true;
		}
		return balanced;
	}

}
