/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questionpostinggui2;

/**
 *
 * @author salsh
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONQuestionSearchGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField searchField;
    private JTextArea resultArea;

    public JSONQuestionSearchGUI() {
        setTitle("Question Search");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(new JScrollPane(resultArea));

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

   @Override
    public void actionPerformed(ActionEvent e) {
        String searchTerm = searchField.getText().toLowerCase();
        String fileName = "questions.json";
        List<String> results = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName)) {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(reader);

            for (Object obj : array) {
                JSONObject question = (JSONObject) obj;
                String questionText = question.get("question").toString().toLowerCase();

                // Check if the question text contains the search term
                boolean found = true;
                for (int i = 0; i < searchTerm.length(); i++) {
                    if (!questionText.contains(String.valueOf(searchTerm.charAt(i)))) {
                        found = false;
                        break;
                    }
                }

                if (found) {
                    results.add(question.get("question").toString());
                }
            }

            // Display the results in the GUI
            if (results.isEmpty()) {
                resultArea.setText("No results found for \"" + searchTerm + "\".");
            } else {
                resultArea.setText("Results for \"" + searchTerm + "\":\n\n");
                for (String result : results) {
                    resultArea.append(result + "\n\n");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JSONQuestionSearchGUI();
    }
}
