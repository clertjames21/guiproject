# guiproject
Program Purpose
A Java Swing GUI application that lets users:

Add diary entries
View a specific entry by ID
View all entries
Exit the program
//////

package Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DiarySystem extends javax.swing.JFrame {
    private static final String FILE_NAME = "diaries.txt";

    public DiarySystem() {
        setTitle("Diary System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnAdd = new JButton("Add Diary");
        JButton btnView = new JButton("View Diary");
        JButton btnViewAll = new JButton("View All Diaries");
        JButton btnExit = new JButton("Exit");

        btnAdd.addActionListener(e -> addDiary());
        btnView.addActionListener(e -> viewDiary());
        btnViewAll.addActionListener(e -> viewAllDiaries());
        btnExit.addActionListener(e -> System.exit(0));

        add(btnAdd);
        add(btnView);
        add(btnViewAll);
        add(btnExit);
    }

    private void addDiary() {
        JTextField idField = new JTextField(5);
        JTextField dateField = new JTextField(5);
        JTextArea contentArea = new JTextArea(3, 12);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScroll = new JScrollPane(contentArea);
        contentScroll.setPreferredSize(new Dimension(180, 75));

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));

        panel.add(new JLabel("ID:"));
        panel.add(idField);

        panel.add(new JLabel("Date:"));
        panel.add(dateField);

        panel.add(new JLabel("Content:"));
        panel.add(contentScroll);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Diary Entry", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String date = dateField.getText().trim();
            String content = contentArea.getText().trim();

            if (id.isEmpty() || date.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(id + ";" + date + ";" + content);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Diary added successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving diary: " + ex.getMessage());
            }
        }
    }

    private void viewDiary() {
        String searchId = JOptionPane.showInputDialog(this, "Enter Diary ID to View:");
        if (searchId == null || searchId.isEmpty()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                if (parts[0].equals(searchId)) {
                    JOptionPane.showMessageDialog(this, "ID: " + parts[0] +
                            "\nDate: " + parts[1] +
                            "\nContent: " + parts[2]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Diary not found.");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading diary: " + ex.getMessage());
        }
    }

    private void viewAllDiaries() {
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                output.append("ID: ").append(parts[0])
                      .append("\nDate: ").append(parts[1])
                      .append("\nContent: ").append(parts[2])
                      .append("\n----------------------\n");
            }

            if (output.length() == 0) {
                output.append("No diary entries found.");
            }

            JTextArea textArea = new JTextArea(output.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350, 200));
            JOptionPane.showMessageDialog(this, scrollPane, "All Diaries", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DiarySystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(() -> {
            DiarySystem diaryApp = new DiarySystem();
            diaryApp.setVisible(true);
        });
    }
}

          

![image alt](https://github.com/clertjames21/guiproject/blob/c97e90170f67cf20879b7ae6b14ccc0640433528/Screenshot%202025-05-11%20132015.png)
![image alt](https://github.com/clertjames21/guiproject/blob/0d71a491e13de7daf4e6646ee0d3ca188185f1ad/Screenshot%202025-05-11%20132030.png)
![image alt](https://github.com/clertjames21/guiproject/blob/37478a06b9cb68d14193de0fc538baedc7b0775e/Screenshot%202025-05-11%20132047.png)
![image alt](https://github.com/clertjames21/guiproject/blob/42966bee35a0a4986ad66f8e177946ca645ca15f/Screenshot%202025-05-11%20132104.png)
![image alt](https://github.com/clertjames21/guiproject/blob/5e867106bc7124f621bfbda4383f9d50f819bf32/Screenshot%202025-05-11%20132120.png)
![image alt](https://github.com/clertjames21/guiproject/blob/ee32fc8d020b2055314e6bf3ac4f9303e25ab31d/Screenshot%202025-05-11%20132204.png)
![image alt](https://github.com/clertjames21/guiproject/blob/4a5900ee4d4dfabf8ea9c6efc9c5d5c6f38fa3c6/Screenshot%202025-05-11%20132233.png)
![image alt](https://github.com/clertjames21/guiproject/blob/2fcf3ce5c595faa75607bfc48c4aa97975a98e81/Screenshot%202025-05-11%20132338.png)
![image alt](https://github.com/clertjames21/guiproject/blob/c2faeeb8967d32620460e4dc2510084ba9dca5a7/Screenshot%202025-05-11%20132313.png)
![image alt](https://github.com/clertjames21/guiproject/blob/098a74289ae37815b21c2ae37c37e84f1427aa95/Screenshot%202025-05-11%20132356.png)
