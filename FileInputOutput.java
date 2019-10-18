import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
    private String line;

    public FileInputOutput(String FileName, String UserInput) {
        this.FileName = FileName;
        this.UserInput = UserInput;
    }

    public FileInputOutput(String FileName) {
        this.FileName = FileName;
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

    // reader = new BufferedReader(new FileReader(this.FileName));
    // while ((line = reader.readLine()) != null) {
    // return Character.toString(line.charAt(0));
    // }
    // if ((line = reader.readLine()) == null)
    // return "0";
    // reader.close();
    // return null;

    public String getId() throws IOException {

        reader = new BufferedReader(new FileReader(this.FileName));
        while ((line = reader.readLine()) != null) {
            return Character.toString(line.charAt(0));
        }
        if ((line = reader.readLine()) == null)
            return "0";
        reader.close();
        return null;

        // File file = new File(this.FileName);
        // Scanner readerScanner = new Scanner(new File(this.FileName));
        // if ((readerScanner.nextLine() == null))
        // return "0";
        // if ((readerScanner.nextLine()) != null)
        // while ((readerScanner.hasNextLine())) {
        // line = Character.toString(readerScanner.nextLine().charAt(0));
        // return line;
        // }

        // readerScanner.close();
        // return null;
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