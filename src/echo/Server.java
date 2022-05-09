package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {

		ServerSocket ServerSocket = new ServerSocket();

		// ServerSocket.bind(ip,port);
		// ServerSocket.bind(new InetSocketAddress(ip, port));
		// ip 검색 --> 명령 프롬프트 ipconfig --> IPv4 주소 체크 / mas : ifconfig
		// ip는 .이 있어서 문자열이라서 " " 안에 작성
		// en1 : 192.168.35.88
		// "127.0.0.1", 5000
		ServerSocket.bind(new InetSocketAddress("127.0.0.1", 10001)); // port번호는 개인이 설정

		// accept() : 허락할꺼야
		System.out.println("<서버시작>");
		System.out.println("==============================");
		System.out.println("[연결을 기다리고 있습니다.]");

		// 반복 시작////////////
		while (true) {
			// 쓰레드 사용
			Socket socket = ServerSocket.accept();

			// 쓰레드 - 출장보내기(출장나가서 스트림봅강하고 대화해라)
			Thread thread = new ServerThread(socket);
			thread.start();
		}

		// 쓰레드

		// 반복 종료 ///////////////////////////////////
		/*
		 * System.out.println("==============================");
		 * System.out.println("<서버종료>");
		 * 
		 * br.close(); bw.close(); socket.close(); ServerSocket.close();
		 */

	}

}