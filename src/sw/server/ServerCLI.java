package sw.server;

import java.util.Scanner;

import sw.server.db.DBConnection;
import sw.server.db.EventDao;
import sw.server.simulator.MessageSimulator;

/**
 * The commands line interface for the server and simulator. Handles user input and output.
 * 
 * @author Lars Kristian
 * 
 */
public class ServerCLI {

	private Server server;
	private Thread serverThread;
	private MessageSimulator simulator;
	private Thread simThread;
	private boolean run;

	/**
	 * Startup
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ServerCLI sc = new ServerCLI();
		sc.run();
	}

	/**
	 * Starts the server, simulator and UI, handles input and requests operations
	 */
	public void run() {
		server = new Server(this, new EventDao());
		simulator = new MessageSimulator(this, server.getBuffer());
		startServer();
		// startSimulator();
		Scanner scanner = new Scanner(System.in);
		try {
			run = true;
			while (run) {
				prompt();
				String str = scanner.nextLine();
				if (str.equals("server start")) {
					if (serverThread == null || !serverThread.isAlive())
						startServer();
				} else if (str.equals("server stop")) {
					if (serverThread != null && serverThread.isAlive())
						stopServer();
				} else if (str.equals("sim start")) {
					if (simThread == null || !simThread.isAlive())
						startSimulator();
				} else if (str.equals("sim stop")) {
					if (simThread != null && simThread.isAlive())
						stopSimulator();
				} else if (str.equals("exit") && str.equals("quit")) {
					stopServer();
					run = false;
				} else if (str.equals("status")) {
					print("Server: " + (serverThread != null && serverThread.isAlive() ? "running" : "stopped"));
					print("Simulator: " + (simThread != null && simThread.isAlive() ? "running" : "stopped"));
				} else if (str.equals("help")) {
				} else if (str.equals("sim alarm")) {
					System.out.println("Enter RFID: ");
					int rfid = scanner.nextInt();
					scanner.nextLine();
					simulator.alarm(rfid);
				} else if (str.equals("sim reset")) {
					simulator.reset();
				} else {
					System.out.println("invalid command, try 'help' for help.");
				}
			}
		} catch (Exception e) {
			scanner.close();
		}
	}

	/**
	 * Prints a default CLI prompt
	 */
	public void prompt() {
		System.out.print("sheepwatch:> ");
	}

	/**
	 * Prints a message to the UI
	 * 
	 * @param msg String to print
	 */
	public void print(String msg) {
		System.out.println(msg);
	}

	/**
	 * Starts the server thread
	 */
	public void startServer() {
		print("Starting server...");
		serverThread = new Thread(server);
		serverThread.setDaemon(true);
		serverThread.start();
	}

	/**
	 * Starts the simulator thread
	 */
	public void startSimulator() {
		if (serverThread.isAlive()) {
			print("Starting simulator...");
			simThread = new Thread(simulator);
			simThread.setDaemon(true);
			simThread.start();
		}
	}

	/**
	 * Stops the server thread
	 */
	public void stopServer() {
		stopSimulator();
		print("Stopping server...");
		serverThread.interrupt();
		server.stop();
	}

	/**
	 * Stops the simulator thread
	 */
	public void stopSimulator() {
		print("Stopping simulator...");
		simThread.interrupt();
		simulator.stop();
	}
}
