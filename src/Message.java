import java.util.HashMap;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

public class Message {
	String user;
	String date;
	String content;
	private final int USERSTARTINDEX = 47, METALENGTH = 26, CONTENTTAGLENGHT=22;
	private static HashMap<String, String> idList = new HashMap<>();
	
	public Message(String message){
		int userLength = findEnd(message.substring(USERSTARTINDEX));
		user = message.substring(USERSTARTINDEX, USERSTARTINDEX+userLength);
		if(Character.isDigit(user.charAt(0))){
			user = convertEmailToName(user);
		}
		int dateLength = findEnd(message.substring(USERSTARTINDEX+userLength+METALENGTH));
		date = message.substring(USERSTARTINDEX+userLength+METALENGTH, USERSTARTINDEX+userLength+METALENGTH+dateLength);
		content = message.substring(USERSTARTINDEX+userLength+METALENGTH+dateLength+CONTENTTAGLENGHT);
	}

	private int findEnd(String message){
		int length = 0;
		while(true){
			try{
			if(message.charAt(length)=='<'){
				break;
			}}catch(Exception e){
				System.out.println(message);
			}
			length++;
		}
		return length;
	}

	public String convertEmailToName(String email) {
		if(idList.containsKey(email)){
			return idList.get(email);
		}
		String id = "";
		for(int i = 0; i<email.length();i++){
			if(!Character.isDigit(email.charAt(i))){
				id=email.substring(0,i);
				break;
			}
		}
        String token = "EAACEdEose0cBALVudWbyTWIXaei4gyO7WQWklwUGUn9obuZA5LK3LgpzqnPcXQVJTpZAZB53CKuHbZBYkDaJOiT5uN40evT5ZA9gOo17ZCr4s0NcSPtZBQHzuCu3iDdzf4xMmwMKVR6S50rh6pc5cyR2WZAojeyALBmkHvmz7RtdqXkuc888ZCvxBIdXik12NTE4ZD";
        FacebookClient fbClient = new DefaultFacebookClient(token, Version.LATEST);
        try{
        	User friend = fbClient.fetchObject(id, User.class);
        	idList.put(email, friend.getName());
        	return friend.getName();
        }catch(Exception e){
        	System.out.println(id);
        	idList.put(email, "");
        }
        return "";
    }

}
