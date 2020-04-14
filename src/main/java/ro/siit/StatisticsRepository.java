package ro.siit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class StatisticsRepository {

    private Calculator calculator;
    static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);


    public StatisticsRepository(Calculator calculator) {

        this.calculator = calculator;
    }

    public long nanoTime(String output, String resultUnit){
        long startTime = System.nanoTime();
        calculator.computation(output,resultUnit);
        long endTime = System.nanoTime() - startTime;
        logger.log(Level.DEBUG,"Execution time is " + endTime + " nanoseconds");
        return endTime;
    }
    public long fastestTime(String output){
        Map<String,Long> fastestMap = new HashMap<>();

        fastestMap.put("mm", nanoTime(output,"mm"));
        fastestMap.put("cm", nanoTime(output,"cm"));
        fastestMap.put("dm", nanoTime(output,"dm"));
        fastestMap.put("m", nanoTime(output,"m"));
        fastestMap.put("km", nanoTime(output,"km"));
      /*  for (Map.Entry<String, Long> mapLine: fastestMap.entrySet()){
            System.out.println(mapLine.getKey() + " " + mapLine.getValue());
        }*/
        long fastestTime = Collections.min(fastestMap.values());
        for (Map.Entry<String, Long> mapLine: fastestMap.entrySet()){
            if (mapLine.getValue() == fastestTime) {
                System.out.println("Fastest execution time is made in " + mapLine.getKey().toUpperCase() + " and is done in " + fastestTime + " nanoseconds");
                logger.log(Level.DEBUG,"Fastest execution time is made in " + mapLine.getKey().toUpperCase() + " and is done in " + fastestTime + " nanoseconds");
            }
        }
        return fastestTime;
    }

}
