
package nlp_ps3mackey;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class BuildGrammarFile {
    
    public static void BuildFile(String file) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter write = new PrintWriter(System.getProperty("user.dir") + "\\lib\\gram\\" + "grammar.gram");
        String line;

        HashSet<String> validWords = new HashSet<>();
        HashSet<String> filter = new HashSet<>();
        
        BufferedReader dictRead = new BufferedReader(new FileReader("vocabulary.txt"));
        while((line = dictRead.readLine())!= null){
            validWords.add(line);
        }
        dictRead.close();
     
        write.println("grammar PS3;");
        write.print("public <PS3> = (");
        
        //Only add valid words
        while((line = br.readLine()) != null) {	
            if(validWords.contains(line.toUpperCase())){
                filter.add(line.toUpperCase());
            }
        }
        
        //Add 10% of tokens
        int count = 0;
        for(String word : filter){
        	if(count % 10 == 0) {
        		write.print(word.toUpperCase() + " | ");
        		count *= 10;
        	}
        	
            count++;
        }
        
        
        write.println("QUIT ) * ;");
        write.close();
        br.close();
    }

}
