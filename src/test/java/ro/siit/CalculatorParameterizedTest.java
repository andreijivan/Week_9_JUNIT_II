package ro.siit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
    public  class CalculatorParameterizedTest{
        private String output;
        private String resultUnit;
        private double finalResult;

    public CalculatorParameterizedTest(String output, String resultUnit, double finalResult) {
        this.output = output;
        this.resultUnit = resultUnit;
        this.finalResult = finalResult;
    }

    @Parameterized.Parameters
    public static Collection inputValues(){
        return Arrays.asList(new Object [][] {
                { "10 cm + 1 m - 10 mm + 2 km - 55 dm", "Km", 1.99559 },
                { "10 cm + 1 m - 10 mm + 2 km - 55 dm", "m", 1995.59 },
                { "10 cm + 1 m - 10 mm + 2 km - 55 dm", "dm", 19955.9 },
                { "10 cm + 1 m - 10 mm + 2 km - 55 dm", "cm", 199559.0 },
                { "10 cm + 1 m - 10 mm + 2 km - 55 dm", "MM", 1995590 }
        });
    }

    @Test
    public void calculatorComputation(){

        Assert.assertEquals(finalResult,new Calculator().computation(output,resultUnit),0);

    }

}
