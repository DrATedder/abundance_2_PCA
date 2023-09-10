import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

public class abundance_2_PCA {
    private JFrame frame;
    private JPanel panel;
    private JTextField inputFolderField;
    private JTextField metadataFileField;
    private JTextField outputLocationField;
    private JRadioButton pca2DButton;
    private JRadioButton pca3DButton;
    private JCheckBox showVarianceCheckbox;
    private JTextArea outputTextArea;

    public abundance_2_PCA() {
        frame = new JFrame("abundance_2_PCA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        inputFolderField = new JTextField("Input Folder");
        metadataFileField = new JTextField("Metadata File");
        outputLocationField = new JTextField("Output Location");
        pca2DButton = new JRadioButton("2D PCA");
        pca3DButton = new JRadioButton("3D PCA");
        showVarianceCheckbox = new JCheckBox("Show Variance");
        outputTextArea = new JTextArea(5, 20);

        ButtonGroup pcaGroup = new ButtonGroup();
        pcaGroup.add(pca2DButton);
        pcaGroup.add(pca3DButton);

        JButton browseInputButton = new JButton("Browse Input");
        JButton browseMetadataButton = new JButton("Browse Metadata");
        JButton chooseOutputLocationButton = new JButton("Choose Output Location");
        JButton generatePCAButton = new JButton("Generate PCA");
        JButton authorInfoButton = new JButton("Author Information");
        JButton produceAnotherPCAButton = new JButton("Produce Another PCA");
        JButton exitButton = new JButton("Exit");

        browseInputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    inputFolderField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        browseMetadataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    metadataFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        chooseOutputLocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    outputLocationField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        generatePCAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputFolder = inputFolderField.getText();
                String metadataFile = metadataFileField.getText();
                String outputLocation = outputLocationField.getText();
                String pcaType = pca2DButton.isSelected() ? "2D" : "3D";
                String showVariance = showVarianceCheckbox.isSelected() ? "true" : "false";

                // Execute the Python script with selected options
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder("python", "abundance_PCA_3D_variance.py", inputFolder, outputLocation, metadataFile, pcaType, showVariance);
                    processBuilder.redirectErrorStream(true);
                    Process process = processBuilder.start();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    StringBuilder output = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }

                    process.waitFor();

                    // Display PDF location or message
                    String pdfLocation = outputLocation + File.separator + "pca_plot_" + pcaType.toLowerCase() + ".pdf";
                    outputTextArea.setText("PDF Location:\n" + pdfLocation);

                    System.out.println(output.toString());
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        authorInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Author: Dr. Andrew Tedder\nUniversity of Bradford", "Author Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        produceAnotherPCAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputFolderField.setText("");
                metadataFileField.setText("");
                outputLocationField.setText("");
                pca2DButton.setSelected(false);
                pca3DButton.setSelected(false);
                showVarianceCheckbox.setSelected(false);
                outputTextArea.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(inputFolderField);
        panel.add(browseInputButton);
        panel.add(metadataFileField);
        panel.add(browseMetadataButton);
        panel.add(outputLocationField);
        panel.add(chooseOutputLocationButton);
        panel.add(pca2DButton);
        panel.add(pca3DButton);
        panel.add(showVarianceCheckbox);
        panel.add(outputTextArea);
        panel.add(generatePCAButton);
        panel.add(authorInfoButton);
        panel.add(produceAnotherPCAButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new abundance_2_PCA();
            }
        });
    }
}
