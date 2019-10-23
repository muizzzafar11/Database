import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.IOException;
import java.io.IOException;

/**
 * Gui
 */
public class Gui {

    /**
     *
     */
    // private static final long serialVersionUID = 1L;

    private int x, y;
    private JFrame frame;
    private Container contentPane;
    private String DeleteRowIdString;

    private JTextField[] textFieldArrayInput = new JTextField[] { new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField() };

    private JTextField[] textFieldArrayOutput = new JTextField[] { new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField(), new JTextField() };

    private String[] textArray = new String[textFieldArrayInput.length + 1];

    public Gui(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void makeFrame() {
        frame = new JFrame("Database App");
        frame.setVisible(true);
        frame.setSize(this.x, this.y);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        Specifications();

    }

    private void Specifications() {
        contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new BorderLayout(6, 6));
        mainDisplay(contentPane);

    }

    private void mainDisplay(Container pane) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Action Buttons:"));
        JButton addButton = new JButton("Add to Database");
        JButton recallButton = new JButton("Recall from the Database");
        JButton deleteButton = new JButton("Delete from the Database");
        panel.add(addButton);
        panel.add(recallButton);
        panel.add(deleteButton);
        pane.add(panel, BorderLayout.NORTH);
        recallButton.addActionListener(new recallButtonClass());
        addButton.addActionListener(new addButtonClass());
        deleteButton.addActionListener(new deleteButtonClass());

    }

    private JPanel smallPanel(String Name, JTextField textfield, boolean editable) {
        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.X_AXIS));
        smallPanel.add(new JLabel(Name));
        textfield.setEditable(editable);
        smallPanel.add(textfield);
        return smallPanel;
    }

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

    private void recallTextFrame() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Populate:"));

        // panel.add(smallPanel(" Id ", textFieldArrayOutput[12]));
        panel.add(smallPanel("  Name      ", textFieldArrayOutput[0], false));
        panel.add(smallPanel("  Age          ", textFieldArrayOutput[1], false));
        panel.add(periodSmallPanel("  Period 1  ", textFieldArrayOutput[2], "  Period 1 Teacher  ",
                textFieldArrayOutput[3], false));
        panel.add(periodSmallPanel("  Period 2  ", textFieldArrayOutput[4], "  Period 2 Teacher  ",
                textFieldArrayOutput[5], false));
        panel.add(periodSmallPanel("  Period 3  ", textFieldArrayOutput[6], "  Period 3 Teacher  ",
                textFieldArrayOutput[7], false));
        panel.add(periodSmallPanel("  Period 4  ", textFieldArrayOutput[8], "  Period 4 Teacher  ",
                textFieldArrayOutput[9], false));
        panel.add(periodSmallPanel("  Period 5  ", textFieldArrayOutput[10], "  Period 5 Teacher  ",
                textFieldArrayOutput[11], false));
        panel.add(smallPanel(" Id ", textFieldArrayOutput[12], true));
        JPanel smallPanel2 = new JPanel();
        JButton populateButton = new JButton("populate button");
        smallPanel2.add(populateButton);
        panel.add(smallPanel2);
        populateButton.addActionListener(new getFromFile());
        MakeSecondaryFrames("Recall text", panel);

    }

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

    // Change the names of the array, make a new array so that we don't have any
    // errors
    private void deleteTextFrame() {

        DeleteRowIdString = JOptionPane.showInputDialog(frame, "Enter Id you want to delete:");

        FileInputOutput fio = new FileInputOutput("test.txt");
        try {
            Integer.parseInt(DeleteRowIdString);
            // Integer.parseInt(textFieldArrayOutput[12].getText());
        } catch (NumberFormatException isId) {
            JOptionPane.showMessageDialog(frame, "The entered input isn't a number.", "Input error",
                    JOptionPane.ERROR_MESSAGE);
        }
        fio.deleteRow(DeleteRowIdString);
    }

    private class recallButtonClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            recallTextFrame();
        }

    }

    private class addButtonClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            writeTextFrame();
        }

    }

    private class deleteButtonClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            deleteTextFrame();
        }

    }

    private class WriteToFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            FileInputOutput fioId = new FileInputOutput("test.txt");
            try {
                textArray[0] = fioId.getId();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println(textArray.length);
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
            // TODO Auto-generated method stub
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
                for (int i = 0; i < textFieldArrayOutput.length - 1; i++) {
                    int num = row.indexOf("|");
                    String text = row.substring(0, num);
                    String removetext = row.substring(0, (num + 1));
                    row = row.replace(removetext, "");
                    textFieldArrayOutput[i].setText(text);
                    // textFieldArrayOutput[i].setText();
                }
            } catch (IOException | NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "The entered input isn't a number.", "Input error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}