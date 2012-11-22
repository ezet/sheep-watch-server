package sw.server;

/**
 * 
 * Performs simple logging and debugging
 * 
 * @author Lars Kristian
 * 
 */
public class Logger {

	public static void log(String string) {
		System.out.println(string);
	}

	public static void log(Exception e) {
		e.printStackTrace(System.out);
	}

	public static void debug(Exception e) {
		if (Config.DEBUG) {
			e.printStackTrace(System.out);
		}
	}

	public static void debug(String string) {
		if (Config.DEBUG) {
			System.out.println(string);
		}
	}

}
