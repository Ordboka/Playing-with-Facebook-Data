import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Conversation {
	public ArrayList<Message> messages;
	public ArrayList<String> users;
	public HashMap<String, Integer> userWordCountMap;
	
	public Conversation(String conversation){
		messages  = new ArrayList<Message>();
		userWordCountMap = new HashMap<String, Integer>();
		conversation+= "</p>";
		findMessages(conversation);
		identifyUsers(messages);
	}
	
	private void identifyUsers(ArrayList<Message> messages) {
		users = (ArrayList<String>) messages.stream().map(m -> m.user).distinct().collect(Collectors.toList());
	}

	public void findMessages(String line){
		int lineLength = line.length();
		boolean inMessage = false;
		int messageStart = -1;
		int i = 0;
		String user;
		while(i<lineLength-3){
			if(!inMessage){
				if(line.substring(i, i+21).equals("<div class=\"message\">")){
					messageStart = i+21;
					i+=21;
					inMessage = true;
				}
			}else{
				if(line.substring(i, i+4).equals("</p>")){
					Message msg = new Message(line.substring(messageStart,i));
					messages.add(msg);
					
					user = msg.user;
					if(userWordCountMap.containsKey(user)) {
						userWordCountMap.put(user, userWordCountMap.get(user)+1);
					}
					else {
						userWordCountMap.put(user, 1);
					}	
					inMessage = false;
				}
			}
			i++;
		}
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	public void addConversation(Conversation c){
		System.out.println(c.users);
		System.out.println(this.messages.size() + " "+ c.messages.size() + " " + (this.messages.size()+ c.messages.size()));
		this.messages.addAll(c.messages);
		System.out.println(this.messages.size());
		for(String name : this.userWordCountMap.keySet()){
			System.out.println(name + " " + this.userWordCountMap.get(name) + " " + c.userWordCountMap.get(name));
			this.userWordCountMap.put(name, this.userWordCountMap.get(name)+c.userWordCountMap.get(name));
			System.out.println(this.userWordCountMap.get(name));
		}
	}
	
	@Override
	public String toString() {
		return userWordCountMap.toString();
	}
}
