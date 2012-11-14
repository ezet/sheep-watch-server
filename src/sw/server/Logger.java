package sw.server;

public class Logger {

	public static void log(String string) {
		System.out.println(string);
	}
	
	public static void log(Exception e) {
		System.out.println(e);
	}
}
