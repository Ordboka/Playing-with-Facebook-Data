import java.util.ArrayList;
import java.util.stream.Collectors;

public class Conversation {
	public ArrayList<Message> messages;
	public ArrayList<String> users;
	
	public Conversation(String conversation){
		messages  = new ArrayList<Message>();
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
					inMessage = false;
				}
			}
			i++;
		}
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return users + " " + messages.size();
	}
}
