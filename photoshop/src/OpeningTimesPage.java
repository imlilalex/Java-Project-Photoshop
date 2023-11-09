import java.util.Scanner;

import CSV.ReadCSV;

public class OpeningTimesPage {
    public void showOpeningTimes(){
        ReadCSV readCSV = new ReadCSV();
        
        //print opening times from CSV
        System.out.println("Here are the Opening Times:");
        try {
            System.out.print(readCSV.readTimesFile("src\\CSV\\PhotoShop_OpeningHours.csv")); 
        } catch (Exception e) {
            System.out.println("Opening Times not found.");
        }

        System.out.println("Enter any character to return to the homepage");
        Scanner scan = new Scanner(System.in);
        scan.next();
    }
    
}
