package nlp_ps3mackey;

import java.util.HashMap;

public class NGram {
	public HashMap<String, NGram> subGrams;
	public int count;
	public int subCount;
	
	public NGram() {
		count = 1;
		subCount = 0;
		subGrams = new HashMap<>();
	}
	
	public void addSub(String key) {
		NGram hold;
		if (subGrams.containsKey(key)) {
			hold = subGrams.get(key);
			hold.count++;
			subCount++;
		} else {
			subGrams.put(key, new NGram());
			subCount++;
		}
	}
}