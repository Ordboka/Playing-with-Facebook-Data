import java.util.ArrayList;
import java.util.HashMap;

public class HelpMethods {
	public static void printSortedMap(HashMap<String, Integer> map){
		ArrayList<String> keys = new ArrayList<>();
		keys.addAll(map.keySet());
		ArrayList<Integer> values = new ArrayList<>();
		values.addAll(map.values());
		for(int i = 0; i<keys.size();i++){
			int highest = -1;
			int index = i;
			for(int j = i; j<keys.size();j++){
				if(values.get(j)>highest){
					highest = values.get(j);
					index = j;
				}
			}
			String tempString = keys.get(i);
			int tempInt = values.get(i);
			keys.set(i, keys.get(index));
			values.set(i, values.get(index));
			keys.set(index, tempString);
			values.set(index, tempInt);
		}
		for(int i = 0; i<keys.size();i++){
			System.out.println(keys.get(i)+ " " + values.get(i));
		}
	}
}
