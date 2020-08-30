package utilsPackage;


public class VerificationUtils extends CommonUtils {
	
	//public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
	
	public static boolean equalFields(String name, Integer actual, Integer expected) {
		if (actual == expected) {
			stepInfo("EQUAL PARAMETER - " + name + " \"" + actual
					+ "\" = \"" + expected + "\"");
			return true;
		} 
		else {
			stepInfo("DIFFERENT PARAMETER - " + name + " \"" + actual
					+ "\" = \"" + expected + "\"");
			return false;
		}
	}
	
	public static boolean containsString(String name, String actual, String expected) {
		if (actual.contains(expected)) {
			stepInfo("EQUAL PARAMETER - " + name + " \"" + actual
					+ "\" contains \"" + expected + "\"");
			return true;
		} 
		else {
			stepInfo("DIFFERENT PARAMETER - " + name + " \"" + actual
					+ "\" not contains \"" + expected + "\"");
			return false;
		}
	}
	
	public static boolean equalBooleanData(String name, boolean actual, boolean expected) {
		if (actual==expected) {
			stepInfo("EQUAL PARAMETER - " + name + " \"" + actual
					+ "\" contains \"" + expected + "\"");
			return true;
		} 
		else {
			stepInfo("DIFFERENT PARAMETER - " + name + " \"" + actual
					+ "\" not contains \"" + expected + "\"");
			return false;
		}
	}
	
	public static boolean equalIntegerData(String name, int actual, int expected) {
		if (actual==expected) {
			stepInfo("EQUAL PARAMETER - " + name + " \"" + actual
					+ "\" contains \"" + expected + "\"");
			return true;
		} 
		else {
			stepInfo("DIFFERENT PARAMETER - " + name + " \"" + actual
					+ "\" not contains \"" + expected + "\"");
			return false;
		}
	}

}
