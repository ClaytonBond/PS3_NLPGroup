package nlp_ps3mackey;

public class Main {

    public static void main(String[] args) throws Exception {
        
        GUI gui = new GUI();
        gui.setVisible(true);
        
        String file = "output/final.txt";
		
        BuildGrammarFile.BuildFile(file);
        BuildGrams gramController = new BuildGrams(file);
        
        voce.SpeechInterface.init("lib", false, true, "lib\\gram", "grammar");
        gui.setText("Listening...","");
        boolean quit = false;
        while(!quit)
        {
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
            }

            while (voce.SpeechInterface.getRecognizerQueueSize() > 0)
            {
                String s = voce.SpeechInterface.popRecognizedString();
                String[] split;
                double totalProb = 0;
                try{
                    split = s.split(" ");
                }catch(Exception e){
                    s += voce.SpeechInterface.popRecognizedString();
                    split = s.split(" ");
                }
                
                if(split.length > 2)
                    s = split[0] + " " + split[1];
                
                String phrase = s;
                String prediction = "";

                String[] words = phrase.split(" ");

                String a,b,c;

                a = "";
                b = "";
                try{
                a = words[words.length-2];
                b = words[words.length-1];
                }catch(Exception e){
                    
                }


                for (int i = 0; i < 10; i++) {
                        c = gramController.getNextWord(a,b);
                        prediction += (c + " ");
                        a = b;
                        b = c;
                }
                
                gui.setText(phrase, prediction);
                split = (phrase + " " + prediction).split(" ");
                for(String word : split) {
                	totalProb += gramController.getLogProb(word);
                }
                
                prediction = "";
                for (int i = 0; i < 10; i++) {
                    b = gramController.getNextWord(a,b);
                    prediction += (b + " ");
                    a = b;
                }
                
                gui.setText2(phrase, prediction);
                split = (phrase + " " + prediction).split(" ");
                for(String word : split) {
                	totalProb += gramController.getLogProb(word);
                }
                
                //Prob for trigrams + bigrams
                gui.setProb(totalProb);
            }
        }
    }
    
}
