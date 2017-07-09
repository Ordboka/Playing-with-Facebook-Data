import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisTools {
	
	public static HashMap<String, Integer> specificWordCounter(ArrayList<Conversation> convoList, String word) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		String content;
		String user;
		
		word = word.toLowerCase();
		
		for(Conversation convo : convoList) {
			for(Message message : convo.messages) {
				content = message.content;
				content = content.toLowerCase();
				user = message.user;
				
				
				int i = 0;
				Pattern p = Pattern.compile(word);
				Matcher m = p.matcher(content);
				while (m.find()) {
					i++;
				}
				
				if(i!=0) {
					if(map.containsKey(user)) {
						map.put(user, i+map.get(user));
					}
					else {
						map.put(user, i);
					}
				}
			}
		}
		return map;
	}
	
	public static void specificWordDisplay(ArrayList<Conversation> convoList, String word) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		String content;
		String user;
		
		word = word.toLowerCase();
		
		for(Conversation convo : convoList) {
			for(Message message : convo.messages) {
				content = message.content;
				content = content.toLowerCase();
				user = message.user;
				
				
				int i = 0;
				Pattern p = Pattern.compile(word);
				Matcher m = p.matcher(content);
				while (m.find()) {
					i++;
				}
				
				if(i!=0) {
					System.out.println(message.date+" [" + user + "] " + message.content);
					if(map.containsKey(user)) {
						map.put(user, i+map.get(user));
					}
					else {
						map.put(user, i);
					}
				}
			}
		}
	}
	
	
	
	
	public static HashMap<String, Integer> test(ArrayList<Conversation> convoList) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		
		String user;
		for(Conversation convo : convoList) {
			for(Message message : convo.messages) {
				user = message.user;
				if(map.containsKey(user)) {
					map.put(user, map.get(user)+1);
				}
				else {
					map.put(user, 1);
				}	
			}
			
		}
		
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
