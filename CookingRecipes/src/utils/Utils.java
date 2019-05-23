package utils;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utils {
	public static Scanner sc=new Scanner(System.in);
	public static Calendar calendar=Calendar.getInstance();
	
	public static boolean containsCaseInsensitive(String source, String input) {
		return Pattern.compile(Pattern.quote(input),Pattern.CASE_INSENSITIVE).matcher(source).find();
	}

	
	public static boolean isStringCorrect(String input, String regX) {
		Pattern pattern = Pattern.compile(regX);
		return pattern.matcher(input).matches();
	}

}
