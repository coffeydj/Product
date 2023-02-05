import javax.swing.JFileChooser;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProductReader {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Product> people = new ArrayList<>();

        JFileChooser chooser = new JFileChooser();
        Scanner in = null;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            in = new Scanner(selectedFile);
        }

        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "ID", "First Name", "Last Name", "Title", "YOB");
        System.out.println("======================================================================");

        for (Product person : people) {
            System.out.printf("%-15s", person.getID());
            System.out.printf("%-15s", person.getName());
            System.out.printf("%-15s", person.getDescription());
            System.out.printf("%-15d\n", person.getCost());
        }
    }

    private static String removeComma(String inString) {
        return inString.substring(0, inString.length() - 1);
    }
}
