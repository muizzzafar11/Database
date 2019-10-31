import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
 * Gui
 */
public class Gui {

    private int x, y;
    private String addBar = "";
    private String getDialogboxId;

    private JFrame frame;
    private Container contentPane;

    private JTextField[] textFieldArrayInput = new JTextField[12];
    private JTextField[] textFieldArrayOutput = new JTextField[13];

    private String[] textArray = new String[textFieldArrayInput.length + 1];

    // Constructor for setting the size of the main display frame
    public Gui(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Initializing the textField arrays
    private void initializeThing() {
        for (int i = 0; i < textFieldArrayInput.length; i++) {
            textFieldArrayInput[i] = new JTextField();
        }
        for (int i = 0; i < textFieldArrayOutput.length; i++) {
            textFieldArrayOutput[i] = new JTextField();
        }
    }

    // Making the frame and setting its properties
    public void makeFrame() {
        initializeThing();
        frame = new JFrame("Database App");
        frame.setVisible(true);
        frame.setSize(this.x, this.y);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        Specifications();
    }

    // setting the internal properties of the frame
    private void Specifications() {
        contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new BorderLayout(6, 6));
        mainDisplay(contentPane);

    }

    // Setting the properties internal properties of the main frame
    private void mainDisplay(Container pane) {
        JPanel panel = new JPanel();
        // Setting the layout to x-axis so that all of the buttons are in a straight
        // line
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        // Making the border
        panel.setBorder(BorderFactory.createTitledBorder("Action Buttons:"));
        JButton addButton = new JButton("Add to Database");
        JButton recallButton = new JButton("Recall from the Database");
        JButton deleteButton = new JButton("Delete from the Database");
        JButton editRowButton = new JButton("Edit Row");
        panel.add(addButton);
        panel.add(recallButton);
        panel.add(deleteButton);
        panel.add(editRowButton);
        pane.add(panel, BorderLayout.NORTH);
        // Adding actionlistener for each of the buttons
        recallButton.addActionListener(new recallButtonClass());
        addButton.addActionListener(new addButtonClass());
        deleteButton.addActionListener(new deleteButtonClass());
        editRowButton.addActionListener(new editbuttonclass());
    }

    // Function for creating the small panel for name and age
    private JPanel smallPanel(String Name, JTextField textfield, boolean editable) {
        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.X_AXIS));
        smallPanel.add(new JLabel(Name));
        textfield.setEditable(editable);
        smallPanel.add(textfield);
        return smallPanel;
    }

    // Function for creating 2 textboxes infron of each other with 1 JLabel each
    private JPanel periodSmallPanel(String Period, JTextField textFieldPeriod, String Teacher,
            JTextField textFieldTeacher, boolean editable) {

        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.X_AXIS));
        smallPanel.add(new JLabel(Period));
        textFieldPeriod.setEditable(editable);
        smallPanel.add(textFieldPeriod);
        smallPanel.add(new JLabel(Teacher));
        textFieldTeacher.setEditable(editable);
        smallPanel.add(textFieldTeacher);
        return smallPanel;

    }

    // Function for making the other frames, when the button is pressed
    private JFrame MakeSecondaryFrames(String displayText, JPanel panel) {
        JFrame secondaryFrame = new JFrame(displayText);
        secondaryFrame.setVisible(true);
        secondaryFrame.setSize(800, 250);
        secondaryFrame.setLocationRelativeTo(null);
        secondaryFrame.setResizable(false);
        secondaryFrame.setDefaultCloseOperation(2);
        Container secondaryPane = (JPanel) secondaryFrame.getContentPane();
        secondaryPane.setLayout(new BorderLayout(6, 6));
        secondaryPane.add(panel, BorderLayout.NORTH);
        return secondaryFrame;
    }

    // The recall button text frame
    private void recallTextFrame(Boolean editable) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Populate:"));

        // Making a textbox and setting each textox to a specified index of the
        // textField array
        panel.add(smallPanel("  Name      ", textFieldArrayOutput[0], editable));
        panel.add(smallPanel("  Age          ", textFieldArrayOutput[1], editable));
        panel.add(periodSmallPanel("  Period 1  ", textFieldArrayOutput[2], "  Period 1 Teacher  ",
                textFieldArrayOutput[3], editable));
        panel.add(periodSmallPanel("  Period 2  ", textFieldArrayOutput[4], "  Period 2 Teacher  ",
                textFieldArrayOutput[5], editable));
        panel.add(periodSmallPanel("  Period 3  ", textFieldArrayOutput[6], "  Period 3 Teacher  ",
                textFieldArrayOutput[7], editable));
        panel.add(periodSmallPanel("  Period 4  ", textFieldArrayOutput[8], "  Period 4 Teacher  ",
                textFieldArrayOutput[9], editable));
        panel.add(periodSmallPanel("  Period 5  ", textFieldArrayOutput[10], "  Period 5 Teacher  ",
                textFieldArrayOutput[11], editable));
        panel.add(smallPanel(" Id ", textFieldArrayOutput[12], !editable));
        JPanel smallPanel2 = new JPanel();
        JButton populateButton = new JButton("populate button");
        smallPanel2.add(populateButton);
        JButton editButton = new JButton("Edit Button");
        if (editable) {
            smallPanel2.add(editButton);
        }
        panel.add(smallPanel2);
        populateButton.addActionListener(new getFromFile());
        editButton.addActionListener(new updateFile());
        MakeSecondaryFrames("Recall text", panel);

    }

    // Same as above, making the frame with textFields as editable
    private void writeTextFrame() {
        // not working for period 5 teacher or period
        JPanel panelsouth = new JPanel();
        panelsouth.setLayout(new BoxLayout(panelsouth, BoxLayout.Y_AXIS));
        panelsouth.setBorder(BorderFactory.createTitledBorder("Display:"));

        panelsouth.add(smallPanel("  Name      ", textFieldArrayInput[0], true));
        panelsouth.add(smallPanel("  Age          ", textFieldArrayInput[1], true));
        panelsouth.add(periodSmallPanel("  Period 1  ", textFieldArrayInput[2], "  Period 1 Teacher  ",
                textFieldArrayInput[3], true));
        panelsouth.add(periodSmallPanel("  Period 2  ", textFieldArrayInput[4], "  Period 2 Teacher  ",
                textFieldArrayInput[5], true));
        panelsouth.add(periodSmallPanel("  Period 3  ", textFieldArrayInput[6], "  Period 3 Teacher  ",
                textFieldArrayInput[7], true));
        panelsouth.add(periodSmallPanel("  Period 4  ", textFieldArrayInput[8], "  Period 4 Teacher  ",
                textFieldArrayInput[9], true));
        panelsouth.add(periodSmallPanel("  Period 5  ", textFieldArrayInput[10], "  Period 5 Teacher  ",
                textFieldArrayInput[11], true));

        JPanel smallPanel2 = new JPanel();
        JButton submitButton = new JButton("submit button");
        smallPanel2.add(submitButton);
        panelsouth.add(smallPanel2);
        submitButton.addActionListener(new WriteToFile());

        MakeSecondaryFrames("write text", panelsouth);
    }

    // prompting the user for the ID they want to delete
    private void deleteTextFrame() {

        getDialogboxId = JOptionPane.showInputDialog(frame, "Enter Id you want to delete:");
        try {
            Integer.parseInt(getDialogboxId);
        } catch (NumberFormatException isId) {
            JOptionPane.showMessageDialog(frame, "The entered input isn't a number.", "Input error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        FileInputOutput fio = new FileInputOutput("test.txt");
        fio.deleteRow(getDialogboxId);
    }

    private class recallButtonClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            recallTextFrame(false);
        }

    }

    private class editbuttonclass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getDialogboxId = JOptionPane.showInputDialog(frame, "Enter Id you want to edit:");
            // Converting it to an int and then checking that if there is an error then tell
            // the user that the entered input isn't a number
            try {
                Integer.parseInt(getDialogboxId);
                // Integer.parseInt(textFieldArrayOutput[12].getText());
            } catch (NumberFormatException isId) {
                JOptionPane.showMessageDialog(frame, "The entered input isn't a number.", "Input error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            textFieldArrayOutput[12].setText(getDialogboxId);
            recallTextFrame(true);
        }

    }

    private class addButtonClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            writeTextFrame();
        }

    }

    private class deleteButtonClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            deleteTextFrame();
        }

    }

    private class updateFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Adding bar and then returning the textfield text
            for (int i = 0; i < textFieldArrayOutput.length; i++) {
                addBar = textFieldArrayOutput[i].getText() + "|";
                textFieldArrayOutput[i].setText(addBar);
            }
            FileInputOutput fio = new FileInputOutput("test.txt");
            fio.getRowEdit(textFieldArrayOutput);

        }

    }

    // For the button in the second frame
    private class WriteToFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            FileInputOutput fioId = new FileInputOutput("test.txt");
            try {
                textArray[0] = fioId.getId();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            int j = 0;
            for (int i = 1; i < (textFieldArrayInput.length + 1); i++) {
                textArray[i] = textFieldArrayInput[j].getText() + "|";
                j++;
            }

            for (int k = 0; k < textArray.length; k++) {
                FileInputOutput fio = new FileInputOutput("test.txt", textArray[k]);
                fio.WriteFile();
                if (k == textArray.length - 1)
                    fio.NewLine();
            }

        }
    }

    private class getFromFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            FileInputOutput fio = new FileInputOutput("test.txt");
            String row;
            try {
                Integer.parseInt(textFieldArrayOutput[12].getText());
                row = fio.getRow(textFieldArrayOutput);
                if (row.equals("null")) {
                    JOptionPane.showMessageDialog(frame, "The entered input doesn't exist.", "Id too large",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // the rowval = "" messes this up
                for (int i = 0; i < textFieldArrayOutput.length - 1; i++) {
                    int num = row.indexOf("|");
                    String text = row.substring(0, num);
                    String removetext = row.substring(0, (num + 1));

                    row = row.replace(removetext, "");

                    textFieldArrayOutput[i].setText(text);
                }
            } catch (IOException | NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "The entered input isn't a number.", "Input error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}