
package nlp_ps3mackey;

public class GUI extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GUI() {
        initComponents();
        Prediction.setVisible(false);
        Phrase2.setVisible(false);
        Prediction2.setVisible(false);
        Probability.setVisible(false);
    }

    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Phrase = new javax.swing.JLabel();
        Prediction = new javax.swing.JLabel();
        Phrase2 = new javax.swing.JLabel();
        Prediction2 = new javax.swing.JLabel();
        Probability = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(100, 100, 0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        Phrase.setText("Initializing voce...");

        Prediction.setForeground(new java.awt.Color(153, 153, 153));
        Prediction.setText("..");

        Phrase2.setText("Pending");
        
        Prediction2.setForeground(new java.awt.Color(153, 153, 153));
        Prediction2.setText("Pending");

        Probability.setText("Probability");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Probability)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Phrase2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Prediction2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Phrase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Prediction)))
                .addContainerGap(398, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Phrase)
                    .addComponent(Prediction, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Phrase2)
                    .addComponent(Prediction2))
                .addGap(18, 18, 18)
                .addComponent(Probability)
                .addGap(177, 177, 177))
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]){
        
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    private javax.swing.JLabel Phrase;
    private javax.swing.JLabel Phrase2;
    private javax.swing.JLabel Prediction;
    private javax.swing.JLabel Prediction2;
    private javax.swing.JLabel Probability;
    private javax.swing.JPanel jPanel2;

    
    public void setText(String phrase, String prediction){
        Phrase.setText("Trigram: " + phrase);
        Prediction.setText(prediction);
        Prediction.setVisible(true);
    }
    
    public void setProb(Double prob) {
    	Probability.setText("log[ Pr(X) ] = " + (prob) + "%");
    	Probability.setVisible(true);
    }
    
    public void setText2(String phrase2, String prediction2) {
    	Phrase2.setText("Bigram:  " + phrase2);
    	Prediction2.setText(prediction2);
    	Phrase2.setVisible(true);
    	Prediction2.setVisible(true);
    }
}
