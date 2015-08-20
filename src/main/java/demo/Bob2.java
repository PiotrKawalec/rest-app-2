package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@RestController
public class Bob2 {
	
	@HystrixCommand(fallbackMethod = "defaultFallback2")
    @RequestMapping(value="/", method=RequestMethod.GET)
	public String response() throws IOException, InterruptedException {
		//uncomment next line and comment following to break service 2 
    	//String htmlResponse = "Service 2 response";
    	String htmlResponse = httpGet("http://127.0.0.1:9993");
		return htmlResponse;					 						 
	}
    
    public String defaultFallback2() {
		//System.out.println("Could not connect to service 3");
        return "Could not connect to service 3";
	}
    
    public static String httpGet(String urlStr) throws IOException {
  	  URL url = new URL(urlStr);
  	  HttpURLConnection conn =
  	      (HttpURLConnection) url.openConnection();

  	  if (conn.getResponseCode() != 200) {
  	    throw new IOException(conn.getResponseMessage());
  	  }

  	  // Buffer the result into a string
  	  BufferedReader rd = new BufferedReader(
  	      new InputStreamReader(conn.getInputStream()));
  	  StringBuilder sb = new StringBuilder();
  	  String line;
  	  while ((line = rd.readLine()) != null) {
  	    sb.append(line);
  	  }
  	  rd.close();

  	  conn.disconnect();
  	  return sb.toString();
  	}
}
