package utils;

public class Constants {
	public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	public static final String PASSWOR_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&\\.\\,])[A-Za-z\\d@$!%*#?&\\.\\,]{8,15}$";
	public static final String PICTURE_URL_REGEX = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)";
	public static final String USER_HEADER = String.format("%8.8s|%15.15s|%15.15s|%30.30s|%15.15s|%6.6s|%9.9s|%30.30s|%20.20s|%20.20s", "ID", "FirstName", "LastName", "e-Mail", "username", "gender", "role", "description", "created", "modified");
	public static final String USER_STRING_FORMAT = "%8.8s|%15.15s|%15.15s|%30.30s|%15.15s|%6.6s|%9.9s|%30.30s|%20.20s|%20.20s";
	public static final String CATEGORY_HEADER = String.format("%8.8s|%15.15s|%50.50s|%20.20s|%20.20s|%20.20s", "ID", "name", "descriptions", "Tags", "created", "modified");
	public static final String COMMENT_FORMAT ="%15.15s|%40.40s|%15.15s|%15.15s";
	public static final String COMMENT_HEADER = String.format(COMMENT_FORMAT, "Author", "Comment", "Added", "URL");
	public static final String RECIPE_FORMAT = "%15.15s|%15.15s|%25.25s|%35.35s|%15.15s";
	public static final String RECIPE_HEADER = String.format(RECIPE_FORMAT, "Author", "Category", "Title", "Description", "Created");
	public static final Long minCountForAll = (long) 0;
}