
/**
 * Write a description of CVSRowSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVRowSearch {
    
    public void listExporters( CSVParser parser, String exportOfinterest) {
     for ( CSVRecord record : parser ) {   
      String export = record.get( "Exports" );  
       if(export.contains(exportOfinterest)) {
        String country = record.get("Country");
        System.out.println(country);
        
        }
        }    
    }
    
    public void whoExportCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
        
    }
    
}
