import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GenTesting {
	
	public ArrayList<Convorsation> threads = new ArrayList<>();
	
	public String findThreads(String line){
		ArrayList<Convorsation> messageThreads = new ArrayList<>();
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
					Convorsation thread = new Convorsation(line.substring(messageStart,i));
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
	
	public ArrayList<Convorsation> getThreads() {
		return threads;
	}
	
	public static void main(String[] args) {
		String path = "C:\\Users\\bensb\\OneDrive\\Documents\\messages.htm";
		GenTesting test = new GenTesting();
		String content = test.loadFile(path);
		for(Convorsation thread : test.getThreads()){
			System.out.println(thread);
		}
		//System.out.println(test.getThreads());
	}
	
}
