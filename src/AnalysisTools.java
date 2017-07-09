import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisTools {
	
	public HashMap<String, Integer> specificWordCounter(ArrayList<Conversation> convoList, String word) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		String content;
		String user;
		
		for(Conversation convo : convoList) {
			for(Message message : convo.messages) {
				content = message.content;
				user = message.user;
				
				int i = 0;
				Pattern p = Pattern.compile(word);
				Matcher m = p.matcher(content);
				while (m.find()) {
					i++;
				}
				
				if(map.containsKey(user)) {
					map.put(user, i+map.get(user));
				}
				else {
					map.put(user, i);
				}
			}
		}
		return map;
	}
}