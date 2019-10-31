import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextField;

/*
 * FileInputOutput
 * A swing thing for inputting data from the user
 * A make a file and write to it. 
 * 
 */
public class FileInputOutput {

    private String FileName;
    private String UserInput;
    private BufferedWriter writer;
    private BufferedReader reader;

    // For reading line from the text file
    private String line = "";
    // For storing the line and changing the id of the line, getId()
    private String rowVal = "";
    // For stroing the string from the jTextField, getRowEdit()
    private String textString = "";
    // For storing the reduced value of the id, deleteRow()
    private String reduceIdLine;
    // For storing the lines of the text file, editRow and deleteRow
    private ArrayList<String> data = new ArrayList<String>();
    // For the data arrayList
    private int i = 0;
    // For storing the location of the bar seperating the data in the Db
    private int endIndex;
    // For deleting the
    private StringBuilder stringBuilder;
    // For stroing the id of the line form the text field
    private String textId;

    private String removeId;
    // For storing text form textField, getRow()
    private String textFieldText;

    public FileInputOutput(String FileName, String UserInput) {
        this.FileName = FileName;
        this.UserInput = UserInput;
        try {
            reader = new BufferedReader(new FileReader(this.FileName));
            writer = new BufferedWriter(new FileWriter(this.FileName, true));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public FileInputOutput(String FileName) {
        this.FileName = FileName;
        try {
            writer = new BufferedWriter(new FileWriter(this.FileName, true));
            reader = new BufferedReader(new FileReader(this.FileName));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Writing to file, if the constructor with userInput is used
    public void WriteFile() {
        try {
            writer.append(this.UserInput);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // For moving onto the next line in the text file
    public void NewLine() {
        try {
            writer = new BufferedWriter(new FileWriter(this.FileName, true));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gettign the id of the row, the first element of the row
    public String getId() throws IOException {

        while ((line = reader.readLine()) != null) {
            // finding the first occurrence of | in the string.
            int number = line.indexOf("|");
            // increase the row val and return it, new id of the row
            rowVal = Integer.toString(Integer.parseInt(line.substring(0, number)) + 1);
        }
        // If nothing is present then return zero.
        if (rowVal == "")
            return "0|";
        return rowVal + "|";
    }

    /**
     * The algorithm for the deleteRow is that it is writing the rows to the
     * arraylist and if the id of the row matches the given id then it moves onto
     * the next line using readLine() and it adds the next line to the arraylist
     * while decreasing the id of the line. Afterwards it makes a new file and
     * writes the arraylist elements to it.
     */
    public void deleteRow(String Id) {

        // Changed the idData with textID
        // replace line with readline to make it work
        try {
            while ((line = reader.readLine()) != null) {
                endIndex = line.indexOf("|");
                textId = line.substring(0, endIndex);
                if ((textId.equals(Id)) == false) {
                    data.add(i, line);
                    i++;
                } else if (textId.equals(Id)) {
                    // when it goes back, it is set to null, change that as well
                    while ((line = reader.readLine()) != null) {
                        endIndex = line.indexOf("|");
                        int reduceVal = Integer.parseInt(line.substring(0, endIndex)) - 1;
                        reduceIdLine = Integer.toString(reduceVal);
                        stringBuilder = new StringBuilder(line);
                        stringBuilder.delete(0, endIndex);
                        reduceIdLine += stringBuilder;
                        data.add(i, reduceIdLine);
                        i++;
                    }
                }
            }
            writer = new BufferedWriter(new FileWriter(this.FileName));
            for (int j = 0; j < data.size(); j++) {
                String stringData = data.get(j);
                writer.append(stringData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    // If the ID matches the id of the line then it returns that line
    public String getRow(JTextField[] textFields) throws IOException {

        while ((line = reader.readLine()) != null) {
            endIndex = line.indexOf("|");
            textId = line.substring(0, endIndex);
            removeId = line.substring(0, (endIndex + 1));
            textFieldText = textFields[textFields.length - 1].getText();
            if (textId.equals(textFieldText)) {
                // return a string without any id and its seperator
                line = line.replace(removeId, "");
                return line;
            }
        }
        reader.close();
        // If there is no such line then return null string
        return "null";
    }

    /**
     * The JtextField index 12 contains the Id, inoput from the user. Adding the
     * rows from the database to the data arraylist, if the id matches then start
     * replacing that string with the text of all of the jtextfield boxes. And then
     * it goes on and fills the arraylist with the other rows from the database.
     * This arraylist is then written to a new file with the same name.
     */
    public void getRowEdit(JTextField[] textFields) {
        try {
            while ((line = reader.readLine()) != null) {
                endIndex = line.indexOf("|") + 1;
                textId = line.substring(0, endIndex);
                if (textId.equals(textFields[12].getText())) {
                    textString = textFields[12].getText();
                    for (int j = 0; j < textFields.length - 1; j++) {
                        textString += textFields[j].getText();
                    }
                    line = textString;

                }
                data.add(i, line);
                i++;
            }

            writer = new BufferedWriter(new FileWriter(this.FileName));
            for (int j = 0; j < data.size(); j++) {
                String stringData = data.get(j);
                writer.append(stringData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}