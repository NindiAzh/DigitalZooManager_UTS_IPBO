package com.digitalzoo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ZooFrame extends JFrame {
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final JTextField nameField = new JTextField(18);
    private final JTextField ageField = new JTextField(18);
    private final JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Generic Mammal", "Generic Bird"});
    private final JTextField furColorField = new JTextField(18);
    private final JCheckBox canFlyBox = new JCheckBox("Can Fly?");
    private final JLabel furColorLabel = new JLabel("Fur Color:");
    private final JTextArea logArea = new JTextArea(12, 43);
    private final JLabel totalLabel = new JLabel("Total animals: 0");

    public ZooFrame() {
        super("Digital Zoo Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add a New Animal"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        addFormRow(formPanel, c, 0, new JLabel("Name:"), nameField);
        addFormRow(formPanel, c, 1, new JLabel("Age:"), ageField);
        addFormRow(formPanel, c, 2, new JLabel("Animal Type:"), typeCombo);
        addFormRow(formPanel, c, 3, furColorLabel, furColorField);
        addFormRow(formPanel, c, 4, new JLabel("Bird Attribute:"), canFlyBox);

        JButton addButton = new JButton("Add Animal");
        addButton.addActionListener(e -> addAnimal());
        typeCombo.addActionListener(e -> updateSpecificFields());

        JPanel top = new JPanel(new BorderLayout(4, 4));
        top.add(formPanel, BorderLayout.CENTER);
        JPanel actionRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionRow.add(totalLabel);
        actionRow.add(addButton);
        top.add(actionRow, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);

        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setMargin(new Insets(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Zoo Activity Log"));
        add(scrollPane, BorderLayout.CENTER);

        updateSpecificFields();
        pack();
        setMinimumSize(new Dimension(520, 530));
        setLocationRelativeTo(null);
    }

    private void addFormRow(JPanel panel, GridBagConstraints c, int row, JComponent label, JComponent input) {
        c.gridx = 0;
        c.gridy = row;
        c.weightx = 0;
        panel.add(label, c);
        c.gridx = 1;
        c.weightx = 1;
        panel.add(input, c);
    }

    private void updateSpecificFields() {
        boolean isMammal = typeCombo.getSelectedIndex() == 0;
        furColorLabel.setEnabled(isMammal);
        furColorField.setEnabled(isMammal);
        canFlyBox.setEnabled(!isMammal);
    }

    private void addAnimal() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        if (name.isEmpty()) {
            showError("Name cannot be empty.");
            nameField.requestFocusInWindow();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age < 0) {
                showError("Age must be zero or greater.");
                ageField.requestFocusInWindow();
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Age must be a valid whole number.");
            ageField.requestFocusInWindow();
            return;
        }

        Animal animal;
        String type;
        if (typeCombo.getSelectedIndex() == 0) {
            String furColor = furColorField.getText().trim();
            if (furColor.isEmpty()) {
                showError("Fur Color cannot be empty for a mammal.");
                furColorField.requestFocusInWindow();
                return;
            }
            animal = new Mammal(name, age, furColor);
            type = "Mammal";
        } else {
            animal = new Bird(name, age, canFlyBox.isSelected());
            type = "Bird";
        }

        animals.add(animal);
        logArea.append("Added a new " + type + "! Info: " + animal.getInfo()
                + ". Sound: " + animal.makeSound() + "\n\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
        totalLabel.setText("Total animals: " + animals.size());

        nameField.setText("");
        ageField.setText("");
        furColorField.setText("");
        canFlyBox.setSelected(false);
        nameField.requestFocusInWindow();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Invalid Input", JOptionPane.WARNING_MESSAGE);
    }
}
