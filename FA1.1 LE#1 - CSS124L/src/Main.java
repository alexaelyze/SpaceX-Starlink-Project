/*
Alexandra Elyze Villar
FOPI01 - CSS124L
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField satelliteIdField, satelliteNameField, longitudeField, latitudeField, elevationField;
    private JButton findSatelliteButton, saveButton, updateButton, deleteButton, clearAllButton;
    private DefaultTableModel tableModel;
    private JTable dataTable;
    private JComboBox<String> healthStatusComboBox;

    public Main() {
        setTitle("SpaceX Starlink Project");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panels with different layouts
        JPanel mainPanel = new JPanel(new GridLayout(6, 1));
        JPanel satelliteIdPanel = createFlowPanel("Satellite ID", 20);
        JPanel satelliteNamePanel = createBorderPanel("Satellite Name", 20);
        JPanel locationPanel = createGridPanel("Longitude", "Latitude", 20, 5);
        JPanel additionalInfoPanel = createAdditionalInfoPanel("Elevation", "Health Status", 20, 5);
        JPanel buttonPanel = createButtonPanel();

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Satellite ID");
        tableModel.addColumn("Satellite Name");
        tableModel.addColumn("Longitude");
        tableModel.addColumn("Latitude");
        tableModel.addColumn("Elevation");
        tableModel.addColumn("Health Status");

        dataTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(dataTable);

        // Clear All Button
        clearAllButton = new JButton("Clear All");
        clearAllButton.setPreferredSize(new Dimension(200, 30));
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllFields();
            }
        });

        // Add components to panels
        mainPanel.add(satelliteIdPanel);
        mainPanel.add(satelliteNamePanel);
        mainPanel.add(locationPanel);
        mainPanel.add(additionalInfoPanel);
        mainPanel.add(buttonPanel);

        // Add panels to the frame
        add(mainPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(clearAllButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createFlowPanel(String labelText, int textFieldWidth) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLUE); // Set text color to blue
        satelliteIdField = new JTextField(textFieldWidth);
        findSatelliteButton = new JButton("Find Satellite");

        panel.add(label);
        panel.add(satelliteIdField);
        panel.add(findSatelliteButton);

        return panel;
    }

    private JPanel createBorderPanel(String labelText, int textFieldWidth) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLUE); // Set text color to blue
        satelliteNameField = new JTextField(textFieldWidth);

        panel.add(label, BorderLayout.WEST);
        panel.add(satelliteNameField, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createGridPanel(String label1, String label2, int textFieldWidth, int gap) {
        JPanel panel = new JPanel(new GridLayout(1, 4, gap, 0));

        JLabel labelLongitude = new JLabel(label1);
        labelLongitude.setForeground(Color.BLUE); // Set text color to blue
        longitudeField = new JTextField(textFieldWidth);

        JLabel labelLatitude = new JLabel(label2);
        labelLatitude.setForeground(Color.BLUE); // Set text color to blue
        latitudeField = new JTextField(textFieldWidth);

        panel.add(labelLongitude);
        panel.add(longitudeField);
        panel.add(labelLatitude);
        panel.add(latitudeField);

        return panel;
    }

    private JPanel createAdditionalInfoPanel(String label1, String label2, int textFieldWidth, int gap) {
        JPanel panel = new JPanel(new GridLayout(1, 4, gap, 0));

        JLabel labelElevation = new JLabel(label1);
        labelElevation.setForeground(Color.BLUE); // Set text color to blue
        elevationField = new JTextField(textFieldWidth);

        JLabel labelHealthStatus = new JLabel(label2);
        labelHealthStatus.setForeground(Color.BLUE); // Set text color to blue
        healthStatusComboBox = new JComboBox<>(new String[]{"Very Good", "Good", "Bad", "Very Bad"});

        panel.add(labelElevation);
        panel.add(elevationField);
        panel.add(labelHealthStatus);
        panel.add(healthStatusComboBox);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        saveButton = new JButton("Save");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        // Set preferred size for the buttons
        saveButton.setPreferredSize(new Dimension(200, 30));
        updateButton.setPreferredSize(new Dimension(200, 30));
        deleteButton.setPreferredSize(new Dimension(200, 30));
        findSatelliteButton.setPreferredSize(new Dimension(200, 30));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSatellite();
            }
        });

        panel.add(saveButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        return panel;
    }

    private void clearAllFields() {
        satelliteIdField.setText("");
        satelliteNameField.setText("");
        longitudeField.setText("");
        latitudeField.setText("");
        elevationField.setText("");
        healthStatusComboBox.setSelectedIndex(0); // Set the default selection
    }

    private void saveSatellite() {
        // Add a row to the table when the Save button is clicked
        Object[] data = {
                satelliteIdField.getText(),
                satelliteNameField.getText(),
                longitudeField.getText(),
                latitudeField.getText(),
                elevationField.getText(),
                healthStatusComboBox.getSelectedItem()
        };
        tableModel.addRow(data);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}