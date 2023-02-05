import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {
        ArrayList<Product> productOData = new ArrayList<>();
        ArrayList<String> productCSVData = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductData.txt");

        boolean done = false;
        String ID = "";
        String Name = "";
        String Description = "";
        String CSVProductRecord = "";
        double Cost = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]:");
            Name = SafeInput.getNonZeroLenString(in, "Enter the name of the product:");
            Description = SafeInput.getNonZeroLenString(in, "Enter the Description: ");
            Cost = SafeInput.getRangedDouble(in, "Enter the Cost of the product: ", 0, 9999);

            CSVProductRecord = ID + ", " + Name + ", " + Description +  ", " + Cost;
            productCSVData.add(CSVProductRecord);

            productOData.add(new Product(ID, Name, Description, Cost));


            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while (!done);

        for (String p : productCSVData)
            System.out.print(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : productCSVData)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
