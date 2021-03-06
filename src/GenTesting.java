import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class GenTesting {
	
	public ArrayList<Conversation> threads = new ArrayList<>();
	
	public String findThreads(String line){
		
		int lineLength = line.length();
		boolean inMessage = false;
		int messageStart = -1;
		int i = 0;
		int lastIndex = -1;
		while(i<lineLength-21){
			if(!inMessage){
				if(line.substring(i, i+20).equals("<div class=\"thread\">")){
					messageStart = i+20;
					i+=20;
					inMessage = true;
				}
			}else{
				if(line.substring(i, i+10).equals("</p></div>")){
					Conversation thread = new Conversation(line.substring(messageStart,i));
					if(thread.getMessages().size()!=0){
						threads.add(thread);
					}	
					lastIndex = i+10;
//					System.out.println(line.substring(messageStart,i));
					inMessage = false;
				}
			}
			i++;
		}
		if(lastIndex!=-1){
			return line.substring(lastIndex);
		}else{
			return line;
		}
	}
	
	public ArrayList<Conversation> removeDuplicates(ArrayList<Conversation> conversations){
		ArrayList<ArrayList<String>> names = new ArrayList<>();
		ArrayList<Conversation> newConversations = new ArrayList<>();
		for(Conversation c : conversations){
			Collections.sort(c.users);
		}
		for(Conversation c : conversations){
			if(names.contains(c.users)){
				for(Conversation newC : newConversations){
					if(newC.users.equals(c.users)){
						newC.addConversation(c);
						break;
					}
				}
			}else{
				names.add(c.users);
				newConversations.add(c);
			}
		}
		return newConversations;
	}
	
	public String loadFile(String fileName){
	String content = "";
	    try {
	    	File fileDir = new File(fileName);
	    	BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
	        String str;
//	        for(int i = 0; i<150;i++){
//	        	str = in.readLine();
//	        	content += str;
//	        	content = findThreads(content);
//	        }
	        int i = 0;
	        while((str = in.readLine()) != null){
	        	//System.out.println(i++);
	        	content += str;
	        	content = findThreads(content);
	        }
	        in.close();
	    } catch (IOException e) {
	    	System.err.println("Error in loading file");
	    }
    return content;
	}
	
	public ArrayList<Conversation> getThreads() {
		return threads;
	}
	
	public static void main(String[] args) {
		String path = "C:\\Users\\bensb\\OneDrive\\Documents\\messages.htm";
//		String path = "C:\\Users\\Ole Johan\\Desktop\\facebook-olejomi\\html\\messages.htm";
		GenTesting test = new GenTesting();
		String content = test.loadFile(path);
		test.threads = test.removeDuplicates(test.threads);
		
		for(Conversation conv : test.getThreads()){
			System.out.println(conv);
		}
		
//		HelpMethods.printSortedMap(AnalysisTools.totalWordCountByPerson(test.getThreads()));
	}
	
}
