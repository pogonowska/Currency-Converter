package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Window implements ActionListener {
    private CurrencyFileReader reader;
    private CurrencyConverter converter;
    private static JTextField inputBox;
    private static JTextField outputBox;
    private JComboBox currencies;

    Window(String filePath){
        reader = new CurrencyFileReader(filePath);
        converter = new CurrencyConverter();
        createWindow();
    }

    private void createWindow() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame);
        frame.setSize(340, 170);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }

    private void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.createLabel(panel, "Amount: ", 20, 20, 90, 20);
        this.createLabel(panel, "From: ", 120, 20, 90, 20);
        this.createLabel(panel, "To: ", 220, 20, 90, 20);
        this.createLabel(panel, "EUR (Euro): ", 120, 50, 90, 20);
        this.setCurrenciesComboBox(panel,220, 50, 90, 20);
        this.setInputBox(panel,20, 50, 90, 20);
        this.setOutputBox(panel,20, 90, 170, 20);
        this.createButton(panel, "Convert", 200, 90, 90, 20);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void createLabel(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel label =new JLabel(text);
        label.setBounds(x, y, width, height);
        panel.add(label);
    }

    private void createButton(JPanel panel, String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(this);
        panel.add(button);
    }

    private void setCurrenciesComboBox(JPanel panel, int x, int y, int width, int height) {
        this.currencies = new JComboBox((Vector) this.reader.readCurrencies());
        this.currencies.setBounds(x, y, width, height);
        panel.add(currencies);
    }

    private void setInputBox(JPanel panel, int x, int y, int width, int height) {
        this.inputBox = new JTextField();
        this.inputBox.setBounds(x, y, width, height);
        panel.add(this.inputBox);
    }

    private void setOutputBox(JPanel panel, int x, int y, int width, int height) {
        this.outputBox = new JTextField();
        this.outputBox.setBounds(x, y, width, height);
        this.outputBox.setEditable(false);;
        panel.add(this.outputBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String curr = String.valueOf(this.getCurrency());
            double rate = this.reader.readRate(curr);
            outputBox.setText(this.inputBox.getText() + " EUR = " + String.valueOf(converter.convert(Double.parseDouble(inputBox.getText()), rate)) + " " + curr);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Enter double type");
            this.inputBox.setText("");
        }
    }

    private Object getCurrency(){
        if (currencies.getSelectedIndex() != -1)
            return currencies.getItemAt(currencies.getSelectedIndex());
        else
            return currencies.getItemAt(0);
    }
}