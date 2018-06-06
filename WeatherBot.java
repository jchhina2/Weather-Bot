import org.jibble.pircbot.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class WeatherBot extends PircBot {
	//String channel; String sender; String login; String hostname; String message;
	
	public static Map<String, Object> jsonToMap(String str)
	{
		Map<String, Object> map = new Gson().fromJson(str,
				new TypeToken<HashMap<String,Object>>() {}.getType());
		return map;
	}
	public WeatherBot()
	{
		this.setName("Weather bot says: ");
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
		
		
		String API_KEY = "5e971c66769095ef1f7ea34ff507998f";
		String cityName = "";
		String LOCATION = "ALLEN,US";
		String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION
				+ "&appid=" + API_KEY + "&units=imperial";
		try{
			StringBuilder result = new StringBuilder();
			URL url = new URL(URL_STRING);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			
			while((line = rd.readLine()) != null)
			{
				result.append(line);
			}
			rd.close();
			//System.out.println(result);
			//sendMessage(channel,sender + "Result: " + result);
			
			Map<String,Object> respMap = jsonToMap(result.toString());
			Map<String,Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String,Object> windMap = jsonToMap(respMap.get("wind").toString());
			
			
			
			if(((message.toLowerCase().contains("allen")) || (message.toLowerCase().contains("75013"))) && 
					(message.toLowerCase().contains("temperature")))
			{
				sendMessage(channel,sender + ": Current Temperature in Allen, Texas: " + mainMap.get("temp") + "F");
			}
			
			if(((message.toLowerCase().contains("allen")) || (message.toLowerCase().contains("75013"))) && 
					(message.toLowerCase().contains("humidity")))
			{
				sendMessage(channel,sender + ": Current Humidity in Allen, Texas: " + mainMap.get("humidity") + "%");
			}
			else if(message.toLowerCase().contains("orlando"))
			{
				
				OrlandoWeather(channel, sender, login, hostname, message);
			}
			else if (message.toLowerCase().contains("dallas"))
			{
				String message2 = message;
				SetWeather(channel, sender, login, hostname, message,message2);
			}
			else
			{
				String message2 = message;
				SetWeather(channel, sender, login, hostname, message,message2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	public void OrlandoWeather(String channel, String sender, String login, String hostname, String message)
	{
		String API_KEY = "5e971c66769095ef1f7ea34ff507998f";
		String cityName = "";
		String LOCATION = "ORLANDO,US";
		String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION
				+ "&appid=" + API_KEY + "&units=imperial";
		try{
			StringBuilder result = new StringBuilder();
			URL url = new URL(URL_STRING);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			
			while((line = rd.readLine()) != null)
			{
				result.append(line);
			}
			rd.close();
			//System.out.println(result);
			//sendMessage(channel,sender + "Result: " + result);
			
			Map<String,Object> respMap = jsonToMap(result.toString());
			Map<String,Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String,Object> windMap = jsonToMap(respMap.get("wind").toString());
			sendMessage(channel,sender + ": Current Temperature in Orlando, Florida: " + mainMap.get("temp") + "F");
			sendMessage(channel,sender + ": Current Humidity in Orlando, Florida: " + mainMap.get("humidity") + "%");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	public void SetWeather(String channel, String sender, String login, String hostname, String message, String city)
	{
		String API_KEY = "5e971c66769095ef1f7ea34ff507998f";
		String cityName = city.toUpperCase();
		String LOCATION = cityName + ",US";
		String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION
				+ "&appid=" + API_KEY + "&units=imperial";
		try{
			StringBuilder result = new StringBuilder();
			URL url = new URL(URL_STRING);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			
			while((line = rd.readLine()) != null)
			{
				result.append(line);
			}
			rd.close();
			//System.out.println(result);
			//sendMessage(channel,sender + "Result: " + result);
			
			Map<String,Object> respMap = jsonToMap(result.toString());
			Map<String,Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String,Object> windMap = jsonToMap(respMap.get("wind").toString());
			sendMessage(channel,sender + ": Current Temperature in " + cityName +  ", United States: " + mainMap.get("temp") + "F");
			sendMessage(channel,sender + ": Current Humidity in " + cityName +  ", United States: " + mainMap.get("humidity") + "%");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}


}
