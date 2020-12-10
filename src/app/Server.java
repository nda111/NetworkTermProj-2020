package app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import data.ERequest;
import data.User;
import interaction.IResponse;
import interaction.response.EchoResponse;


public class Server {
	
	public static final HashMap<ERequest, IResponse> Responses = new HashMap<>(); // ���� ����
	
	public static final HashMap<String, User> Users = new HashMap<>(); // ����� ����Ʈ
	
	public static ServerSocket server = null;
	
	public static void main(String[] args) {
		
		initResponse();
		loadUserInfo();
		
		try {

			// ��Ʈ�ѹ��� �޾ƿ´�
			String Port = null;
			File file = new File("./server_info.txt");
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			int port = Integer.parseInt(scanner.nextLine());
			scanner.close();

			server = new ServerSocket(port);
			System.out.println("�����غ�Ϸ�");

			while (true) {

				Socket client = server.accept();
				
				ServerResponser handler = new ServerResponser(client); // ������ ����
				handler.start(); // ������ ����
			} // while
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	private static void initResponse() {
		
		// Simple Echo request
		Responses.put(ERequest.ECHO, new EchoResponse());
	}
	
	private static void loadUserInfo() {
		
		File dir = new File(User.UsersPath);
		File[] userFiles = dir.listFiles();
		
		for (File file : userFiles) {
			
			try {
				Scanner reader = new Scanner(file);
				StringBuilder json = new StringBuilder();
				
				while (reader.hasNextLine()) {
					
					json.append(reader.nextLine());
					json.append('\n');
				}
				reader.close();
				
				User user = User.parseJsonOrNull(json.toString());
				if (user == null) {
					
					throw new Exception("Failed to load user");
				} else {
					
					Users.put(user.uid, user);
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}