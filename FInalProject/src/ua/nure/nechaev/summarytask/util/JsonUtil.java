package ua.nure.nechaev.summarytask.util;

import java.util.List;

/**
 * Utility class for transforming string containers to JSON string
 * 
 * @author Maks
 *
 */
public class JsonUtil {
	private static String separator = ", ";

	/**
	 * Transform List<String>
	 * 
	 * @param List<String> - list of string to convert
	 * @return String representing List in valid JSON format
	 */
	public static String toJSON(List<String> places) {
		if (places == null || places.size() == 0) {
			return "[ ]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (String place : places) {
			sb.append("\"").append(place).append("\"").append(separator);
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(" ]");
		return sb.toString();
	}

}
