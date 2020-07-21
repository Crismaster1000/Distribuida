package rest01.client;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Cliente01 {

	public static void main(String[] args) throws Exception {
		
		URL url = new URL("http://127.0.0.1:8080/rest01/hola/stephano");
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod( "GET" );
		connection.setRequestProperty( "Accept", "application/json" );
		
		int httpCode = connection.getResponseCode();
		
		if(httpCode != HttpURLConnection.HTTP_OK) {
			System.err.println(" Error " + httpCode );
			return;
		}
		
		//commons-io
		//http 200: ok
		InputStream is = connection.getInputStream();
		
		String json = IOUtils.toString(  is , Charset.defaultCharset() );
		System.out.println( " datos devueltos:  " + json);
		
		// trabajamos con JSON usando la dependencia antes puesta 
		ObjectMapper mapper = new ObjectMapper();
		Persona per = mapper.readValue( json , Persona.class);
		
		System.out.println( "nombre " + per.getNombre());
		System.out.println( "mensaje " + per.getMensaje());
		

	}

}
