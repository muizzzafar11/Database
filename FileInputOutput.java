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

    private String line = "";
    private String IdData;
    private String reduceIdLine;
    private ArrayList<String> data = new ArrayList<String>();
    private int i = 0;
    private int endIndex;
    private StringBuilder stringBuilder;
    private String textId;
    private String removeId;
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

    public void WriteFile() {
        try {
            writer.write(this.UserInput);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void NewLine() {
        try {
            writer = new BufferedWriter(new FileWriter(this.FileName, true));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String rowVal = "";

    public String getId() throws IOException {

        while ((line = reader.readLine()) != null) {
            // finding the first occurrence of | in the string.
            int number = line.indexOf("|");
            rowVal = Integer.toString(Integer.parseInt(line.substring(0, number)) + 1);
        }
        if (rowVal == "")
            return "0|";
        return rowVal + "|";
    }

    // private String checkLargerId;

    public void deleteRow(String Id) {

        // replace line with readline to make it work
        try {
            while ((line = reader.readLine()) != null) {
                endIndex = line.indexOf("|");
                IdData = line.substring(0, endIndex);
                if ((IdData.equals(Id)) == false) {
                    data.add(i, line);
                    i++;
                } else if (IdData.equals(Id)) {
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
                writer.write(stringData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    public String getRow(JTextField[] textFields) throws IOException {
        // reader = new BufferedReader(new FileReader(this.FileName));

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
        // put that line here, this is the error
        return "null";
    }

    // check the number for this string I think this is wrong.
    private String textString = "";

    // creating an empty file without the data in it.
    // Probably the fault of the arraylist
    public void getRowEdit(JTextField[] textFields) {
        try {
            while ((line = reader.readLine()) != null) {
                endIndex = line.indexOf("|") + 1;
                textId = line.substring(0, endIndex);
                // if ((textId.equals(textFields[12].getText())) == false) {
                //     data.add(i, line);
                //     i++;
                // }
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
                writer.write(stringData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Have jradioboxes, make an array for each jradiobox. each index corresponds
// with the one of the string array contining the string seperated by |.
// The string will replace,
// give each radiobox a number and it will move onto that number of the gettext
// string array, ask for the index first,