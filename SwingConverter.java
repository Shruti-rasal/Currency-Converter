import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SwingConverter extends JFrame implements ActionListener {

    
    private static final double USD_TO_EUR = 0.92;
    private static final double USD_TO_JPY = 156.96;
    private static final double USD_TO_INR = 83.56;

    
    private JTextField amountField;
    private JComboBox<String> sourceCurrency, targetCurrency;
    private JButton convertButton;
    private JLabel resultLabel;

  
    private Map<String, String> currencySymbols = new HashMap<>();

    public SwingConverter() {
        setTitle("Currency Converter ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout(10, 10));

       
        getContentPane().setBackground(Color.black);

       
        currencySymbols.put("USD", "US"); 
        currencySymbols.put("EUR", "EU");
        currencySymbols.put("JPY", "JP");
        currencySymbols.put("INR", "IN");

       
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(new Color(220, 230, 240));
        
        JLabel titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.red); 
        topPanel.add(titleLabel);
    

        add(topPanel, BorderLayout.NORTH);

       
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 15, 15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 15, 20));
        centerPanel.setBackground(Color.black);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 20));
        amountLabel.setForeground(Color.white); 
        amountField = new JTextField("0.0");
        amountField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fromLabel.setForeground(Color.white); 
        
        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 20));
        toLabel.setForeground(Color.white); // Change font color to white


        String[] currencies = {"USD", "EUR", "JPY", "INR"};
        sourceCurrency = new JComboBox<>(currencies);
        targetCurrency = new JComboBox<>(currencies);
        sourceCurrency.setFont(new Font("Arial", Font.PLAIN, 15));
        targetCurrency.setFont(new Font("Arial", Font.PLAIN, 15));

        convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(0, 150, 255));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Arial", Font.BOLD, 15));
        convertButton.setFocusPainted(true);
        convertButton.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 200)));
        
        
        resultLabel = new JLabel("Result: 0.00");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.black); 
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(amountLabel);
        centerPanel.add(amountField);
        centerPanel.add(fromLabel);
        centerPanel.add(sourceCurrency);
        centerPanel.add(toLabel);
        centerPanel.add(targetCurrency);
        centerPanel.add(new JLabel(""));
        centerPanel.add(convertButton);
        add(centerPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.white); 
        bottomPanel.add(resultLabel);
        add(bottomPanel, BorderLayout.SOUTH);

       
        convertButton.addActionListener(this);

   
JMenuBar menuBar = new JMenuBar();


JMenu editMenu = new JMenu("Edit");
JMenuItem clearAmount = new JMenuItem("Clear Amount");
JMenuItem clearResult = new JMenuItem("Clear Result");

clearAmount.addActionListener(ev -> amountField.setText(""));
clearResult.addActionListener(ev -> resultLabel.setText("Result: 0.00"));

editMenu.add(clearAmount);
editMenu.add(clearResult);


JMenu helpMenu = new JMenu("Help");
JMenuItem instructions = new JMenuItem("Instructions");
JMenuItem about = new JMenuItem("About");

instructions.addActionListener(ev -> JOptionPane.showMessageDialog(
        this,
        "1. Enter the amount.\n2. Select source and target currency.\n3. Click Convert.",
        "Instructions",
        JOptionPane.INFORMATION_MESSAGE
));

about.addActionListener(ev -> JOptionPane.showMessageDialog(
        this,
        "Currency Converter v1.0\n"
      + "Developed by: Tejaswini Thombare & Shruti Rasal\n"
      + "Institute: Government Polytechnic, Pune\n"
      + "Description:\n"
      + "A simple desktop application built with Java Swing\n"
      + "that allows conversion between USD, EUR, JPY, and INR.\n\n"
      + "Future Update: Live exchange rates coming soon!",
        "About",
        JOptionPane.INFORMATION_MESSAGE
));

helpMenu.add(instructions);
helpMenu.add(about);


JMenu settingsMenu = new JMenu("Settings");
JMenuItem setDefaultCurrency = new JMenuItem("Set Default Currency");

setDefaultCurrency.addActionListener(ev -> {
    sourceCurrency.setSelectedItem("USD");
    targetCurrency.setSelectedItem("INR");
    JOptionPane.showMessageDialog(this, "Default set to USD → INR");
});

settingsMenu.add(setDefaultCurrency);

 
JMenu toolsMenu = new JMenu("Tools");


JMenuItem copyResult = new JMenuItem("Copy Result");
copyResult.addActionListener(ev -> {
    String result = resultLabel.getText();
    java.awt.datatransfer.StringSelection selection =
            new java.awt.datatransfer.StringSelection(result);
    java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    JOptionPane.showMessageDialog(this, "Result copied to clipboard!");
});


toolsMenu.add(copyResult);



menuBar.add(editMenu);
menuBar.add(helpMenu);
menuBar.add(settingsMenu);
menuBar.add(toolsMenu);

setJMenuBar(menuBar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String source = (String) sourceCurrency.getSelectedItem();
                String target = (String) targetCurrency.getSelectedItem();

                double amountInUSD = 0.0;
                double convertedAmount = 0.0;

                
                switch (source) {
                    case "USD": amountInUSD = amount; break;
                    case "EUR": amountInUSD = amount / USD_TO_EUR; break;
                    case "JPY": amountInUSD = amount / USD_TO_JPY; break;
                    case "INR": amountInUSD = amount / USD_TO_INR; break;
                }

                
                switch (target) {
                    case "USD": convertedAmount = amountInUSD; break;
                    case "EUR": convertedAmount = amountInUSD * USD_TO_EUR; break;
                    case "JPY": convertedAmount = amountInUSD * USD_TO_JPY; break;
                    case "INR": convertedAmount = amountInUSD * USD_TO_INR; break;
                }

                
                resultLabel.setText(String.format("Result: %.2f %s %s", convertedAmount, currencySymbols.get(target), target));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid amount!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingConverter::new);
    }
}