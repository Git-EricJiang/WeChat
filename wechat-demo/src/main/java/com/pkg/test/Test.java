package com.pkg.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

//	public static void main(String[] args) {
//		 String []cmds = {"curl", "-i", "-w", "状态%{http_code}；DNS时间%{time_namelookup}；"  
//	                + "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"  
//	                + "总时间%{time_total}","http://www.baidu.com"};  
//	        ProcessBuilder pb=new ProcessBuilder(cmds);  
//	        pb.redirectErrorStream(true);  
//	        Process p;  
//	        try {  
//	            p = pb.start();  
//	            BufferedReader br=null;  
//	            String line=null;  
//	              
//	            br=new BufferedReader(new InputStreamReader(p.getInputStream()));  
//	            while((line=br.readLine())!=null){  
//	                    System.out.println("\t"+line);  
//	            }  
//	            br.close();  
//	        } catch (IOException e) {  
//	            // TODO Auto-generated catch block  
//	            e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		URL url = null;
		try {
			url = new URL("http://stackoverflow.com");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {
		        System.out.println(line);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void name() {
		   String username="myusername";
		    String password="mypassword";
		    String url="https://www.example.com/xyz/abc";
		       String[] command = {"curl", "-u" ,"Accept:application/json", username, ":" , password , url};
		        ProcessBuilder process = new ProcessBuilder(command); 
		        Process p;
		        try
		        {
		            p = process.start();
		             BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
		                StringBuilder builder = new StringBuilder();
		                String line = null;
		                while ( (line = reader.readLine()) != null) {
		                        builder.append(line);
		                        builder.append(System.getProperty("line.separator"));
		                }
		                String result = builder.toString();
		                System.out.print(result);

		        }
		        catch (IOException e)
		        {   System.out.print("error");
		            e.printStackTrace();
		        }
	}
}
