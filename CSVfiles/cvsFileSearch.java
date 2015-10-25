
/**
 * Write a description of cvsFileSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class cvsFileSearch {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String s =countryinfo(parser, "Nauru");
        System.out.println(s);
        parser = fr.getCSVParser();
        System.out.println("These contries exports fish and nuts ");
        listExportTwoProduct(parser,"gold","diamonds");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"sugar"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999", 1);
    }
    
    public void bigExporters ( CSVParser parser, String amount,int value){
    
        for(CSVRecord record : parser ) {
         String val =  record.get("Value (dollars)");
         if(val.length() > amount.length()) {
          System.out.print(record.get("Country")+ ":  ");
          System.out.println(val);
         }
        }
    } 
    
    
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for(CSVRecord record : parser) {
            String expItem = record.get("Exports");
            if(expItem.contains(exportItem)) count++;
        }
        return count;
    }
    
    
    public void listExportTwoProduct(CSVParser ps, String exportItem1, String exportItem2){
      
        for(CSVRecord rec : ps){
            String expItem = rec.get("Exports");
            if(expItem.contains(exportItem1) && expItem.contains(exportItem2) ){
            System.out.print(rec.get("Country")+ ":  ");
            System.out.println(rec.get("Exports"));
          }
        }
        
    }
    
    
    public String countryinfo( CSVParser parser, String country){
        String result = "NOT FOUND";
        //parser = fr.getCSVParser();
        for( CSVRecord record : parser){

            String temp = record.get( "Country");
           // if(temp.contains(country)){
               if (record.get("Country").equals(country)) {
                result = temp +": "+ record.get("Exports") +": "+ record.get("Value (dollars)");

            }
        }

        return result;
    }

    
    
    
}
