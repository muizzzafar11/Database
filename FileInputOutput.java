import java.io.BufferedReader;
import java.io.BufferedWriter;
// import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    }

    public FileInputOutput(String FileName) {
        this.FileName = FileName;
        try {
            reader = new BufferedReader(new FileReader(this.FileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void WriteFile() {
        try {
            // True tells that the fiel is already present so don't create it
            writer = new BufferedWriter(new FileWriter(this.FileName, true));
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
            line = Integer.toString(Integer.parseInt(rowVal.substring(0, number))+1);
        }
        if (line == "")
            return "0|";
        return line + "|";
    }

    public void getFile(JTextField[] textFields) {
        try {
            reader = new BufferedReader(new FileReader(this.FileName));
            // for (int i = 0; i < textFields.length; i++) {
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                // }
            }
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}