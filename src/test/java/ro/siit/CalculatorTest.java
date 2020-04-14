package ro.siit;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;


public class CalculatorTest {
    @Test
    public void computationTestMm() {
        String output = "10 cm + 1 m - 10 mm + 2 km - 55 dm";
        String resultUnit = "Mm";
        Assert.assertEquals(new Calculator().computation(output, resultUnit), 1995590, 0);
    }

    @Test
    public void computationTestCm() {
        String output = "10 cm + 1 m - 10 mm + 2 km - 55 dm";
        String resultUnit = "CM";
        Assert.assertEquals(new Calculator().computation(output, resultUnit), 199559.0, 0);
    }

    @Test
    public void computationTestDm() {
        String output = "10 cm + 1 m - 10 mm + 2 km - 55 dm";
        String resultUnit = "dM";
        Assert.assertEquals(new Calculator().computation(output, resultUnit), 19955.9, 0);
    }

    @Test
    public void computationTestM() {
        String output = "10 cm + 1 m - 10 mm + 2 km - 55 dm";
        String resultUnit = "M";
        Assert.assertEquals(new Calculator().computation(output, resultUnit), 1995.59, 0);
    }

    @Test
    public void computationTestKm() {
        String output = "10 cm + 1 m - 10 mm + 2 km - 55 dm";
        String resultUnit = "Km";
        Assert.assertEquals(new Calculator().computation(output, resultUnit), 1.99559, 0);
    }
    @Test
    public void milliTime(){

        String output = "10 cm + 1 m - 10 mm + 2 km - 55 dm";
        String resultUnit = "dM";
        StatisticsRepository statisticsRepository = new StatisticsRepository(new Calculator());
        long result = statisticsRepository.nanoTime(output, resultUnit);
        Assert.assertEquals(result, 6,0);
    }
    @Test
    public void fastestTime(){
        //test always fails because result is in nanoseconds and will always be different.
        // if we use milliseconds, test is inconclusive, since multiple results are 0 milliseconds
        String output = "10 cm + 1 m - 10 mm + 2 km - 55 dm";
        StatisticsRepository statisticsRepository = new StatisticsRepository(new Calculator());
        Map<String,Long> fastestMap = new HashMap<>();

        fastestMap.put("mm", statisticsRepository.nanoTime(output,"mm"));
        fastestMap.put("cm", statisticsRepository.nanoTime(output,"cm"));
        fastestMap.put("dm", statisticsRepository.nanoTime(output,"dm"));
        fastestMap.put("m",  statisticsRepository.nanoTime(output,"m"));
        fastestMap.put("km", statisticsRepository.nanoTime(output,"km"));
        long fastestTime = Collections.min(fastestMap.values());
        Assert.assertEquals(fastestTime, 318100,0);
    }
}


