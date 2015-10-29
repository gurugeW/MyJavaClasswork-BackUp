
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;


public class CSVColdest {
 
  

    
    
    
    
    
    
public void testFileWithColdestTemperature() {
  String fileName = fileWithColdestTemperature();
   FileResource fr = new FileResource( fileName);
    CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
  
  System.out.println("Coldest day was in file " + fileName.substring(fileName.lastIndexOf("\\" ) + 1));
  System.out.println("Coldest temperatur on that day was " + smallest.get("TemperatureF"));
  printAll(fr.getCSVParser());

}
    
    
public String fileWithColdestTemperature() {
    CSVRecord coldestSoFar = null;
    String fileName = "";
    DirectoryResource dr = new DirectoryResource();
   
    for( File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      
      CSVRecord currentRow = coldestHourInFile(fr.getCSVParser()); 
      if( Double.parseDouble(currentRow.get("TemperatureF")) > -500){
      if(coldestSoFar == null) {
            coldestSoFar = currentRow;
            fileName = f.toString();
        }
        else {
         double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));   
          double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));  
          
          if(currentTemp < coldestTemp) {
              coldestSoFar = currentRow;
              fileName = f.toString();
            }
        }
    } 
 }
    return fileName;
   }
    
    
    
    
   public CSVRecord coldestHourInFile(CSVParser parser){
       CSVRecord coldestSoFar = null;
      for(CSVRecord currentRow : parser) {
       coldestSoFar = getSmallestOfTwo( currentRow, coldestSoFar );
       
    }
   return coldestSoFar;
}


public void testColdestHourInFile()  {
 FileResource fr = new FileResource( "nc_weather/2014/weather-2014-05-01.csv");
  CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
  System.out.println("Coldest temperature was " + smallest.get("TemperatureF")
  + " at " + smallest.get("TimeEDT"));
    
}


public CSVRecord getSmallestOfTwo( CSVRecord currentRow, CSVRecord coldestSoFar ) {
     if(coldestSoFar == null) {
            coldestSoFar = currentRow;
            
        }
        else {
         double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));   
          double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));  
          
          if(currentTemp < coldestTemp) {
              coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
}
 public void printAll(CSVParser parser){
     System.out.println( "All the Temperatures on the coldest day were:");
     for(CSVRecord record : parser) { 
          System.out.print(record.get("DateUTC")+" ");   
                  System.out.println(record.get("TemperatureF") );
           
        }
     
    }

  public double averageTemperatureInFile(CSVParser parser) {
   double average = 0,  total = 0;
   int count = 0 ;
    for( CSVRecord csvr : parser){
     total = total + Double.parseDouble(csvr.get("TemperatureF")); 
     count++;
     
     
    }
    return ((double)total/count);  
      
    }
    
 
  public void  testAverageTemperatureInFile(){
   
   FileResource fr = new FileResource();
   CSVParser parser = fr.getCSVParser();
   double average = averageTemperatureInFile(parser);   
   System.out.println("Average temperature in file is "+ average);
     
     
    }  

 public double  averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
     double averageTemp = 0 , total = 0;
     int count = 0;
     for(CSVRecord csvr : parser ) {
      double currentHumidity = Double.parseDouble(csvr.get("Humidity")); 
      if( currentHumidity >= value) { 
         total += Double.parseDouble(csvr.get("TemperatureF")); 
         count++;
        }
        }
        averageTemp = total/count;
     return averageTemp;
    }
    
 public void testAverageTemperatureWithHighHumidityInFile(){ 
   FileResource fr = new FileResource();
   CSVParser parser = fr.getCSVParser();
   double average = averageTemperatureWithHighHumidityInFile(parser, 80);
   System.out.println("Average Temp when high Humidity is " + average);
     
    }
    
}
