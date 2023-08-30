package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// TCP 소켓 프로그래밍 - 서버 소켓
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress("192.168.188.23", 10001)); // (ip주소, port)
		
		System.out.println("<서버 시작>");
		System.out.println("============================================================");
		System.out.println("[연결을 기다리고 있습니다.]");
		
		Socket socket = serverSocket.accept();	// 소켓 객체 생성 command
		System.out.println("[클라이언트가 연결되었습니다]");
		
		// 메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		// 클라이언트 while에 대응하여 반복 수령 필요함
		while(true) {
			// 메세지 받기
			String msg = br.readLine();
			
			
			
			if(msg==null) {
				break;
			}
			
			System.out.println("최정필:" + msg);
			// 메세지 보내기
			bw.write(msg);
			bw.newLine();
			bw.flush();
		}
		
		
		System.out.println("============================================================");
		System.out.println("<서버 종료>");
		bw.close();
		br.close();
		socket.close();
		serverSocket.close();
		
	}

}