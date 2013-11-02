package com.fromdev.automation.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StringUtil {
	public static final String SPECIAL_CHAR_PATTERN = "[-+.^:,]";
	private static String[] sw = { "a", "about", "above", "above", "across",
			"after", "afterwards", "again", "against", "all", "almost",
			"alone", "along", "already", "also", "although", "always", "am",
			"among", "amongst", "amoungst", "amount", "an", "and", "another",
			"any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are",
			"around", "as", "at", "back", "be", "became", "because", "become",
			"becomes", "becoming", "been", "before", "beforehand", "behind",
			"being", "below", "beside", "besides", "between", "beyond", "bill",
			"both", "bottom", "but", "by", "call", "can", "cannot", "cant",
			"co", "con", "could", "couldnt", "cry", "de", "describe", "detail",
			"do", "done", "down", "due", "during", "each", "eg", "eight",
			"either", "eleven", "else", "elsewhere", "empty", "enough", "etc",
			"even", "ever", "every", "everyone", "everything", "everywhere",
			"except", "few", "fifteen", "fify", "fill", "find", "fire",
			"first", "five", "for", "former", "formerly", "forty", "found",
			"four", "from", "front", "full", "further", "get", "give", "go",
			"had", "has", "hasnt", "have", "he", "hence", "her", "here",
			"hereafter", "hereby", "herein", "hereupon", "hers", "herself",
			"him", "himself", "his", "how", "however", "hundred", "ie", "if",
			"in", "inc", "indeed", "interest", "into", "is", "it", "its",
			"itself", "keep", "last", "latter", "latterly", "least", "less",
			"ltd", "made", "many", "may", "me", "meanwhile", "might", "mill",
			"mine", "more", "moreover", "most", "mostly", "move", "much",
			"must", "my", "myself", "name", "namely", "neither", "never",
			"nevertheless", "next", "nine", "no", "nobody", "none", "noone",
			"nor", "not", "nothing", "now", "nowhere", "of", "off", "often",
			"on", "once", "one", "only", "onto", "or", "other", "others",
			"otherwise", "our", "ours", "ourselves", "out", "over", "own",
			"part", "per", "perhaps", "please", "put", "rather", "re", "same",
			"see", "seem", "seemed", "seeming", "seems", "serious", "several",
			"she", "should", "show", "side", "since", "sincere", "six",
			"sixty", "so", "some", "somehow", "someone", "something",
			"sometime", "sometimes", "somewhere", "still", "such", "system",
			"take", "ten", "than", "that", "the", "their", "them",
			"themselves", "then", "thence", "there", "thereafter", "thereby",
			"therefore", "therein", "thereupon", "these", "they", "thickv",
			"thin", "third", "this", "those", "though", "three", "through",
			"throughout", "thru", "thus", "to", "together", "too", "top",
			"toward", "towards", "twelve", "twenty", "two", "un", "under",
			"until", "up", "upon", "us", "very", "via", "was", "we", "well",
			"were", "what", "whatever", "when", "whence", "whenever", "where",
			"whereafter", "whereas", "whereby", "wherein", "whereupon",
			"wherever", "whether", "which", "while", "whither", "who",
			"whoever", "whole", "whom", "whose", "why", "will", "with",
			"within", "without", "would", "yet", "you", "your", "yours",
			"yourself", "yourselves", "the" };
	private static Set stopWrods = new HashSet(Arrays.asList(sw));

	public static Set extractTags(String text) {
		Set tags = extractWrods(text);
		tags.removeAll(stopWrods);
		return tags;

	}

	public static Set extractWrods(String text) {
		text = text.replaceAll(SPECIAL_CHAR_PATTERN, "");
		text = text.toLowerCase();
		String[] wordArray = text.split(" ");

		return new HashSet(Arrays.asList(wordArray));
	}

	public static String getRedirectedUrl(String link) {

		try {
			URLConnection con = new URL(link).openConnection();
			// System.out.println("orignal url: " + con.getURL());
			con.connect();
			// System.out.println("connected url: " + con.getURL());
			InputStream is = con.getInputStream();
			// System.out.println("redirected url: " + con.getURL());
			is.close();
			return removeUrlParams(con.getURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String removeUrlParams(String url) {
		int idx = url.indexOf("?utm_source=feedburner");
		if (idx > 0) {
			return url.substring(0, idx);
		} else {
			return url;
		}

	}

	public static boolean notNullOrEmpty(String s) {
		return !( s == null || "".equals(s));
	}

	public static String[] splitOrDefault(String s, String[] old) {
		if (notNullOrEmpty(s)) {
			return s.split("\n");
		}
		return old;
	}

	public static String readRemoteFile(String fileUrl) {
		URL url;
		try {
			url = new URL(fileUrl);

			Scanner s = new Scanner(url.openStream());
			return s.useDelimiter("\\Z").next();
		} catch (Exception e) {
			System.out.println("Error reading remote file " + fileUrl + " " + e.getMessage());
		}
		return "";
	}

	public static void main(String[] args) {
		// System.out.println(removeUrlParams("http://net.tutsplus.com/tutorials/tools-and-tips/two-factor-auth-using-authy/"));
		// String s =
		// readRemoteFile("http://www.puzzlers.org/pub/wordlists/pocket.txt");
		String s = readRemoteFile("https://raw.github.com/fromdev/fromdev-static/gh-pages/release/web-dev-feeds.txt");
		// System.out
		// .println(s);
		String [] old = {""};
		String[] list = splitOrDefault(s, old);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}

	}
}
