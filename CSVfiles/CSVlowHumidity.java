
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVlowHumidity {
   
public CSVRecord lowestHumidityInManyFiles() {
    CSVRecord lowestSoFar = null;
    DirectoryResource dr = new DirectoryResource();
    for( File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      
      CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser()); 
      lowestSoFar = lowestOfTheTwo(lowestSoFar, currentRow);  
    }
   return lowestSoFar;
   }
    
public void testLowestHumidityInManyFiles(){
 
  CSVRecord  csvr =  lowestHumidityInManyFiles();
  
  System.out.println("Lowest Humidity was " + csvr.get("Humidity") + " at " + csvr.get("DateUTC"));
 

}
    


  
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
     CSVRecord lowestSoFar = null;
      for(CSVRecord currentRow : parser) {
      if( !currentRow.get("Humidity").equals("N/A")) {
       lowestSoFar = lowestOfTheTwo(lowestSoFar, currentRow);  
    }
       
       
    }
   return lowestSoFar;
} 
public CSVRecord lowestOfTheTwo( CSVRecord lowestSoFar, CSVRecord currentRow){
  if(lowestSoFar == null) {
            lowestSoFar = currentRow;
            
        }
        else  {
         double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));   
          double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));  
          
          if(currentHumidity < lowestHumidity) {
              lowestSoFar = currentRow;
            }
        }
  return lowestSoFar;
}
  
 public void testLowestHumidityInFile() {
   FileResource fr = new FileResource();
   CSVParser parser = fr.getCSVParser();
   CSVRecord csv = lowestHumidityInFile(parser);   
   System.out.println("Lowest Humidity was "+ csv.get("Humidity") +" at " + csv.get("DateUTC") );
     
     
    }
    
    
    
    
}
