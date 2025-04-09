/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @author Alejandro Olea
 * @version    2025.04.08
 */

/**
 * A class to analyze hourly web access data from a log file.
 * It counts the number of accesses per hour and provides methods to print and analyze the data.
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Creates a LogAnalyzer object with a default log file name "demo.log".
     * Initializes an array to store hourly access counts and sets up the LogfileReader.
     */
    public LogAnalyzer()
    { 
        this("demo.log"); // Call the parameterized constructor with default file
    }

    /**
     * Create a LogAnalyzer object with a specified log file name.
     * Initialize an array to store hourly access counts and sets up the LogfileReader with the given file.
     * 
     * @param filename The name of the log file to analyze (e.g., "mylog.txt").
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data from the specified file.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     * Read each log entry using the LogfileReader and increment the count for the corresponding hour.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * Display each hour (0-23) and the number of accesses recorded.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    /**
     * Return total number of accesses recorded in the log file.
     * Sum the access counts for all hours.
     * 
     * @return The total number of accesses across all hours.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) {
            total += hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Return the hour with the highest number of accesses.
     * Analyze the hourly counts to determine which hour (0-23) had the most activity.
     * If multiple hours tie, return the earliest.
     * 
     * @return The hour (0-23) with the most accesses.
     */
    public int busiestHour()
    {
        int busiest = 0;
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > hourCounts[busiest]) {
                busiest = hour;
            }
        }
        return busiest;
    }
    
    /**
     * Return the hour with the lowest number of accesses.
     * Analyze the hourly counts to determine which hour (0-23) had the least activity.
     * If multiple hours tie, return the earliest.
     * 
     * @return The hour (0-23) with the fewest accesses.
     */
    public int quietestHour()
    {
        int quietest = 0;
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] < hourCounts[quietest]) {
                quietest = hour;
            }
        }
        return quietest;
    }
}
