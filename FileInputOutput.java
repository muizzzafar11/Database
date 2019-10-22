import java.io.BufferedReader;
import java.io.BufferedWriter;
// import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// import java.util.Scanner;
import javax.swing.JTextField;

// import jdk.nashorn.internal.parser.Scanner;

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

    public FileInputOutput(String FileName, String UserInput) {
        this.FileName = FileName;
        this.UserInput = UserInput;
        try {
            reader = new BufferedReader(new FileReader(this.FileName));
            writer = new BufferedWriter(new FileWriter(this.FileName, true));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FileInputOutput(String FileName) {
        this.FileName = FileName;
        try {
            writer = new BufferedWriter(new FileWriter(this.FileName, true));
            reader = new BufferedReader(new FileReader(this.FileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void WriteFile() {
        try {
            // True tells that the fiel is already present so don't create it
            // writer = new BufferedWriter(new FileWriter(this.FileName, true));
            // Writing the input to the user
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String rowVal = "";

    public String getId() throws IOException {

        while ((rowVal = reader.readLine()) != null) {
            // finding the first occurrence of | in the string.
            int number = rowVal.indexOf("|");
            line = Integer.toString(Integer.parseInt(rowVal.substring(0, number)) + 1);
        }
        if (line == "")
            return "0|";
        return line + "|";
    }

    public void deleteRow(String Id) {
        String IdData;
        String readLine, line3;
        ArrayList<String> data = new ArrayList<String>();
        int i = 0;
        int endIndex;
        StringBuilder stringBuilder;
        try {
            while ((readLine = reader.readLine()) != null) {
                endIndex = readLine.indexOf("|");
                IdData = readLine.substring(0, endIndex);
                if ((IdData.equals(Id)) == false) {
                    data.add(i, readLine);
                    i++;
                } else if (IdData.equals(Id)) {
                    // when it goes back, it is set to null, change that as well
                    while ((readLine = reader.readLine()) != null) {
                        endIndex = readLine.indexOf("|");
                        int reduceVal = Integer.parseInt(readLine.substring(0, endIndex)) - 1;
                        line3 = Integer.toString(reduceVal);
                        stringBuilder = new StringBuilder(readLine);
                        stringBuilder.delete(0, endIndex);
                        line3 += stringBuilder;
                        data.add(i, line3);
                        i++;
                    }
                }
            }
        } catch (NumberFormatException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            writer = new BufferedWriter(new FileWriter(this.FileName));
            for (int j = 0; j < data.size(); j++) {
                String stringData = data.get(j);
                writer.write(stringData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getRow(JTextField[] textFields) throws IOException {
        // reader = new BufferedReader(new FileReader(this.FileName));

        while ((line = reader.readLine()) != null) {
            int indexBar = line.indexOf("|");
            String textId = line.substring(0, indexBar);
            String removeId = line.substring(0, (indexBar + 1));
            String textFieldText = textFields[textFields.length - 1].getText();
            if (textId.equals(textFieldText)) {
                // return a string without any id and its seperator
                line = line.replace(removeId, "");
                return line;
            }
        }
        reader.close();
        return null;
    }
}