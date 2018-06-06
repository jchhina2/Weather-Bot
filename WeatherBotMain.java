public class WeatherBotMain {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		WeatherBot bot = new WeatherBot();
		
		bot.setVerbose(true);
		
		bot.connect("irc.freenode.net");
		
		bot.joinChannel("#weatherbot");

	}

}
