
package projectt;

/**
 *
 * @author nina
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class QuestionPostingGUI extends JFrame implements ActionListener {
    
    private JTextField questionTextField;
    private JButton postButton;
    
    public QuestionPostingGUI() {
        setTitle("Question Posting");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        
        JLabel questionLabel = new JLabel("Enter your question:");
        inputPanel.add(questionLabel);
        
        questionTextField = new JTextField(20);
        inputPanel.add(questionTextField);
        
        postButton = new JButton("Post");
        postButton.addActionListener(this);
        inputPanel.add(postButton);
        
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        QuestionPostingGUI gui = new QuestionPostingGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == postButton) {
            String question = questionTextField.getText();
            if (!question.isEmpty()) {
                System.out.println("Question posted: " + question);
                // Here you can add code to post the question to a database or a server
            } else {
                System.out.println("Error: Question cannot be empty.");
            }
        }
    }
}
