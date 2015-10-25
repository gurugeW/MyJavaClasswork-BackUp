
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
        String s =countryinfo(parser, "Germany");
        System.out.println(s);
        parser = fr.getCSVParser();
        listExportTwoProduct(parser,"gold","diamonds");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"gold"));

    }
    
    public void bigExporter ( CSVParser parser, String amount,int value){
    
        for(CSVRecord record : parser ) {
            
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
            System.out.println(rec.get("Country"));
          }
        }
        
    }
    
    
    public String countryinfo( CSVParser parser, String country){
        String result = "NOT FOUND";
        //parser = fr.getCSVParser();
        for( CSVRecord record : parser){

            String temp = record.get( "Country");
            if(temp.contains(country)){
                result = temp +": "+ record.get("Exports") +": "+ record.get("Value (dollars)");

            }
        }

        return result;
    }

    
    
    
}
