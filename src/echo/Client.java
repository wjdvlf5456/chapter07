package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
//import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("============================");
		
		System.out.println("[서버에 연결을 요청합니다]");
		socket.connect(new InetSocketAddress("192.168.188.23", 10001));
		
		System.out.println("[서버에 연결 되었습니다.]");
		
		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();	// new 불필요, 알아서 세팅되어있음
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		// 메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		
		// 스캐너
		// Scanner sc = new Scanner(System.in);
		
		// 스캐너 대용 (키보드 연결 / 학습용)
		InputStream in = System.in;
		InputStreamReader sisr = new InputStreamReader(in, "UTF-8");
		BufferedReader sbr = new BufferedReader(sisr);
		
		// 반복문 적용
		while(true) {
			// 키보드 입력값
			// String str = sc.nextLine();
			
			String str = sbr.readLine();
			
			if(str.equals("/q")) {
				break;
			}else if(str.equals("-help")) {
				System.out.println("how can i help you");
				
			}
			
			
			// 메세지 보내기
			bw.write(str);
			// 현재 정보량이 매우 적어 전송이 되지 않음(손님을 더 기다리는 중)
			bw.newLine();
			bw.flush();	// 강제 송출
			
			// 메세지 받기
			String reMsg = br.readLine();
			System.out.println("server: [" + reMsg + "]");
			
		}
		
		System.out.println("=============================");
		//System.out.println("<클라이언트 종료>");
		
		// println 대용(학습용)
		OutputStream out = System.out;
		OutputStreamWriter posw = new OutputStreamWriter(out);
		BufferedWriter pbw = new BufferedWriter(posw);
		
		pbw.write("<클라이언트 종료>");
		pbw.newLine();
		pbw.flush();
		
		//sc.close();
		sbr.close();
		br.close();
		bw.close();
		socket.close();
		
		
		
	}

}