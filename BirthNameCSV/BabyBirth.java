
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

// field 0 = name,  1 = gender, 2 = NumOfBirth

public class BabyBirth {

    public int getTotalBirthsRankedHigher(int year, String name, String gender ) {
        int rank = 0, currentRowRank = 0, totalBirths = 0; 

        FileResource fr = new FileResource();
        rank = getRank(year, name, gender);
        System.out.println("Rank is " + rank);
        for (CSVRecord row : fr.getCSVParser(false)) {
            if( gender.equals(row.get(1) )) { // check for same gender
                currentRowRank = getRank(year, row.get(0), gender);
                if( currentRowRank < rank ){ // if the current row is ranked higher
                    //System.out.println( "name " + row.get(0) + "  " + "gender  " + row.get(1) );
                    totalBirths += Integer.parseInt( row.get(2)); // get and add num of births 

                }
                else { break; }
            }
        }

        return totalBirths;
    }

    public void  testGetTotalBirthsRankedHigher() {
        System.out.println( getTotalBirthsRankedHigher(1990 , "Emily", "F")); 

    }

    
    public double getAverageRank(String name, String gender ){
        double average = -1; int cnt = 0, total = 0;
        DirectoryResource dr = new DirectoryResource(); // open directory  
        for (File f: dr.selectedFiles()){  // go through all selected files 
            int rank =( getRank( f, name, gender));
            cnt++; 
            if (rank > 0 ) {
                total += rank;  

            }
        }

        return ((double) total)/ cnt ;  
    }

    public void testGetAverageRank() {
        double average = getAverageRank("Robert", "M");
        System.out.println(average);

    } 

    public int getRank(File f, String name, String gender) {
        int rank = -1 , temp = 0;

        FileResource fr = new FileResource(f);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            //System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1)  ); 
            if (rec.get(1).equals(gender)) {
                temp++;    
            }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)) { rank = temp; 
                // System.out.println(rec.get(2)); break; 
            }

        }
        return rank;    
    }

    public int yearOfHighestRank( String name, String gender ) { 
        int year = -1 ,  tempTotalBirth = 0, totalBirths = 0;
        String tempYear = " ";
        DirectoryResource dr = new DirectoryResource(); // open directory
        for (File f: dr.selectedFiles()){   // go through all selected files
            FileResource file = new FileResource(f);   

            for (CSVRecord rec : file.getCSVParser(false)) {  // go through each row in file
                // check for name and gender matche 
                if ( (rec.get(0).equals(name)) && (rec.get(1).equals(gender)) ) { 
                    int currentBirth = Integer.parseInt( rec.get(2)); 
                    if(currentBirth > totalBirths) { // check for highest births
                        totalBirths = currentBirth;
                        year  = getIntegerYear( f.getName() ) ; // get the file name
                    }
                }

            }
        }

        return year;
    }

    public void testYearOfHighestRank( ) {
        int  year = yearOfHighestRank("Mich", "M");
        System.out.println(year);

    }

    public int getIntegerYear(String fname){
        int year = ( Integer.parseInt(  fname.substring(3,7) ) );
        return year;   
    }

    public String whatIsNameInYear( String name, int year, int newYear, String gender   ) {
        int yearRank = getRank(year, name, gender);  
        System.out.println(yearRank);  

        String newYearName = getName(newYear,yearRank, gender);
        String result = name + " born in " + year + " would be " + newYearName + 
            " if he/she was born in " + newYear + ".";
        return result;
    } 

    public void testWhatIsNameInYear() {
        String newName = whatIsNameInYear( "Owen", 1974, 2014, "M" );   
        System.out.println(newName);

    }

    public String getName(int year, int rank, String gender ){
        String name = "NO NAME";
        int tempRank = 0;
        String fname= "us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fname); 

        for (CSVRecord rec : fr.getCSVParser(false)) {
            // check for gender match
            if (rec.get(1).equals(gender)) { 
                tempRank++;    
            } 
            if(tempRank == rank && rec.get(1).equals(gender)) { name = rec.get(0);  break; }    
        }
        return name;   
    }

    public void testGetName() {
        String name = getName(  1982 , 450, "M");
        System.out.println(name);  
    }

    public int getRank(int year, String name, String gender) {
        int rank = -1 , temp = 0;
        String fname= "us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            //System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1)  ); 
            if (rec.get(1).equals(gender)) {
                temp++;    
            }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)) { rank = temp; 
                // System.out.println(rec.get(2)); break; 
            }

        }
        return rank;    
    }

    public void testGetRank() {
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);  
    }

    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100 ) {
                System.out.println("Name " + rec.get(0) + 
                    " Gender " + rec.get(1) + 
                    " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys  = 0, boyNames  = 0; 
        int totalGirls = 0, girlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) { 
            int numBorn =  Integer.parseInt(rec.get(2));
            totalBirths += numBorn;

            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boyNames++;
            }
            else {
                totalGirls += numBorn;
                girlNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girl names = " + girlNames);
        System.out.println("total boy names = " + boyNames);
        System.out.println("total names = " + (boyNames +girlNames));
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource ("us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }

}