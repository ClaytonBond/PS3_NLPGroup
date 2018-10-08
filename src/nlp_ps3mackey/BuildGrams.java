package nlp_ps3mackey;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BuildGrams {
	
	public HashMap<String, NGram> nGrams = new HashMap<>();
	
	public BuildGrams(String inFile) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new FileReader(inFile));
		String a = br.readLine();
		String b = br.readLine();
		String c = br.readLine();
		
		nGrams.put(a, new NGram());
		addGram(b);
		nGrams.get(a).addSub(b);
		addGram(c);
		nGrams.get(b).addSub(c);
		nGrams.get(a).subGrams.get(b).addSub(c);
		
		a = b;
		b = c;
		
		while((c = br.readLine()) != null) {
			addGram(c);
			nGrams.get(b).addSub(c);
			nGrams.get(a).subGrams.get(b).addSub(c);
			a = b;
			b = c;
		}
		
		br.close();
	}
	
	public void addGram(String key) {
		NGram gram;
		
		if (nGrams.containsKey(key)) {
			gram = nGrams.get(key);
			gram.count++;
		} else {
			nGrams.put(key, new NGram());
		}
	}
	
	public int getUniCount (String word) {
		if (nGrams.containsKey(word)) {
			return nGrams.get(word).count;
		} else {
			return 0;
		}
	}
	
	public int getBiCount(String word1) {
		
		if(nGrams.containsKey(word1)) {
			return nGrams.get(word1).subCount;
		} else {
			return 0;
		}
	}
	
	public int getBiCount(String word1, String word2) {
		int count = 0;
		
		if(nGrams.containsKey(word1)) {
			if (nGrams.get(word1).subGrams.containsKey(word2)) {
				count = nGrams.get(word1).subGrams.get(word2).count;
			}
		}
		
		return count;
		
	}
	
	public int getTriCount(String word1, String word2) {
		int count = 0;
		
		if(nGrams.containsKey(word1)) {
			if (nGrams.get(word1).subGrams.containsKey(word2)) {
				count = nGrams.get(word1).subGrams.get(word2).subCount;
			}
		}
		
		return count;
	}
	
	public int getTriCount(String word1, String word2, String word3) {
		int count = 0;
		
		if(nGrams.containsKey(word1)) {
			if (nGrams.get(word1).subGrams.containsKey(word2)) {
				if (nGrams.get(word1).subGrams.get(word2).subGrams.containsKey(word3)) {
					count = nGrams.get(word1).subGrams.get(word2).subGrams.get(word3).count;
				}
			}
		}
		
		return count;
	}
	
	public double getLogProb(String word) {
		return Math.log(getProb(word));
	}
	
	public double getProb(String word) {
		return (double)getUniCount(word) / nGrams.size();
	}
	
	public double getProb(String word1, String word2) {

		double biCount = getBiCount(word1,word2) + 1.0;
		int w1Count = getBiCount(word1) + nGrams.size();
		return (double)biCount / w1Count;
	}
	
	public double getProb(String word1, String word2,String word3) {
		double triCount = getTriCount(word1,word2, word3);
		if(triCount == 0){
			return (double)getProb(word2,word3);
		}
		return (double)triCount / getTriCount(word1,word2);
	}
	
	public String getNextWord() {
		String maxKey = null;
		double maxProb = 0;
		
		for(Map.Entry<String, NGram> entry : nGrams.entrySet()) {
			if (getProb(entry.getKey())> maxProb) {
				maxProb = getProb(entry.getKey());
				maxKey   = entry.getKey();
			}
		}
		
		return maxKey;
	}
	
	public String getNextWord(String word1) {
		String maxKey = null;
		double maxProb = 0;
		
		if(nGrams.containsKey(word1)) {
			for(Map.Entry<String, NGram> entry : nGrams.get(word1).subGrams.entrySet()) {
				if (getProb(word1, entry.getKey()) > maxProb) {
					maxProb = getProb(word1, entry.getKey());
					maxKey   = entry.getKey();
				}
			}
		} else {
			return getNextWord();
		}
		
		return maxKey;
	}
	
	public String getNextWord(String word1, String word2) {
		String maxKey = null;
		double maxProb = 0;
		
		if(nGrams.containsKey(word1)) {
			if(nGrams.get(word1).subGrams.containsKey(word2)) {
				for(Map.Entry<String, NGram> entry : nGrams.get(word1).subGrams.get(word2).subGrams.entrySet()) {
					if (getProb(word1, word2, entry.getKey()) > maxProb) {
						maxProb = getProb(word1, word2, entry.getKey());
						maxKey   = entry.getKey();
					}
				}
			} else {
				return getNextWord(word2);
			}
		} else {
			return getNextWord();
		}
		return maxKey;
	}
}