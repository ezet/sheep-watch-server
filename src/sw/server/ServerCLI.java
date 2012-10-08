package sw.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import sw.server.db.DBConnection;
import sw.server.db.MessageDao;
import sw.server.simulator.InputSimulator;

public class ServerCLI {

	private Server server;
	private Thread serverThread;
	private InputSimulator simulator;
	private Thread simThread;
	private boolean run;

	public static void main(String[] args) {
		ServerCLI sc = new ServerCLI();
//		 sc.init();
		sc.run();
	}

	public void init() {
	}

	public void run() {
		server = new Server(this, new MessageDao(new DBConnection()));
		simulator = new InputSimulator(this, server.getBuffer());
		startServer();
		startSimulator();

		// init input simulator

		Scanner scanner = new Scanner(System.in);
		run = true;
		while (run) {
			prompt();
			String str = scanner.nextLine();
			if (str.equals("server start")) {
				if (!serverThread.isAlive())
					startServer();
			} else if (str.equals("server stop")) {
				if (serverThread.isAlive())
					stopServer();
			} else if (str.equals("sim start")) {
				if (!simThread.isAlive())
					startSimulator();
			} else if (str.equals("sim stop")) {
				if (simThread.isAlive())
					stopSimulator();
			} else if (str.equals("exit") && str.equals("quit")) {
				stopServer();
				run = false;
			} else if (str.equals("status")) {
				print("Server: " + (serverThread.isAlive() ? "running" : "stopped"));
				print("Simulator: " + (simThread.isAlive() ? "running" : "stopped"));
			} else if (str.equals("help")) {
			} else if(str.equals("sim alert")) {
				simulator.alert();
			} else
				System.out.println("invalid command, try 'help' for help.");
		}
	}

	private void prompt() {
		System.out.print("sheepwatch:> ");
	}

	public void print(String msg) {
		System.out.println(msg);
	}

	public void startServer() {
		print("Starting server...");
		serverThread = new Thread(server);
		serverThread.setDaemon(true);
		serverThread.start();
	}

	public void startSimulator() {
		if (serverThread.isAlive()) {
			print("Starting simulator...");
			simThread = new Thread(simulator);
			simThread.setDaemon(true);
			simThread.start();
		}
	}

	public void stopServer() {
		stopSimulator();
		print("Stopping server...");
		serverThread.interrupt();
		server.stop();
	}

	public void stopSimulator() {
		print("Stopping simulator...");
		simThread.interrupt();
		simulator.stop();
	}
}
