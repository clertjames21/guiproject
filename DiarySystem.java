/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author dell
 */
public class DiarySystem extends javax.swing.JFrame {
    private static final String FILE_NAME = "diaries.txt";

    /**
     * Creates new form DiarySystem
     */
   public DiarySystem() {
        setTitle("Diary System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
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
    JTextField idField = new JTextField(5); // Smallest width for ID
    JTextField dateField = new JTextField(5); // Smallest width for Date
    JTextArea contentArea = new JTextArea(3, 12); // Larger content field for readability
    contentArea.setLineWrap(true);
    contentArea.setWrapStyleWord(true);
    JScrollPane contentScroll = new JScrollPane(contentArea);
    contentScroll.setPreferredSize(new Dimension(180, 75)); // Reasonable content area width

    JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5)); // Tight, compact layout

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DiarySystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiarySystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiarySystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiarySystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            DiarySystem diaryApp = new DiarySystem();
            diaryApp.setVisible(true);
        });
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

