package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Objects.Item;
import Objects.OpenTimes;

public class ReadCSV {
    

    private String currentLine;
    BufferedReader br; 
    //read in the times CSV file to print
    public String readTimesFile(String fileName) throws IOException { 
        br = new BufferedReader(new FileReader(fileName));
        StringBuilder lines = new StringBuilder();

        //read through and split into items to print
        while ((currentLine = br.readLine()) != null) { 
            String[] str = currentLine.split(";");
            //add each line together to return
            if (str[1].equals("Day")) {
                lines.append(str[1] + "   \t\t" + str[2] + " - " + str[3] +"\n");;
            } else {
                lines.append(str[1] + "   \t" + str[2] + " - " + str[3] +"\n");
            }
        }

        return lines.toString();
        
    }

    //read in the item list
    public List<Item> readItemsFile(String fileName) throws IOException { 
        br = new BufferedReader(new FileReader(fileName));

        List<Item> items = new ArrayList<>();

        //read through and split into items, add to item class
        while ((currentLine = br.readLine()) != null) {
            String[] str = currentLine.split(";");

            if (str[0].equals("Item_ID")) {
                continue;
            } else {
                Item item = new Item(Integer.parseInt(str[0]), str[1], new BigDecimal(str[2]), Integer.parseInt(str[3].substring(0,2)));
                items.add(item);
            }
             
        }

        return items;
        
    }

    //read in opening times from CSV
    public static List<OpenTimes> getOpenTimes() { 
        List<OpenTimes> times = new ArrayList<OpenTimes>();
        String currentLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\CSV\\PhotoShop_OpeningHours.csv"));
            while ((currentLine = br.readLine()) != null) { 
                String[] str = currentLine.split(";");
                //add each line together to return
                if (str[1].equals("Day")) {
                    // Skip Header line;
                } else {
                    times.add(new OpenTimes(str[1], str[2], str[3]));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return times;
    }
}