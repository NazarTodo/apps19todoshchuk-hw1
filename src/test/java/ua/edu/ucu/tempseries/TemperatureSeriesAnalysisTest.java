package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis arr1;

    @Before
    public void setUp() throws Exception {
        double[] temperatureSeries = {10.0, 14.7, -3.1, 2.0};
        arr1 = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }


    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testSetTemperatureSeries() {
    }

    @Test
    public void testCheckEmpty() {
    }

    @Test(expected = InputMismatchException.class)
    public void testTooLowTemperatureAverage() {
        double[] temperatureSeries = {10.0, 14.7, -300.1, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = seriesAnalysis.average();
    }

    @Test
    public void testAverage1() {
    }

    @Test
    public void testDeviation() {
        double exp = 6.9003623093284;
        double real = arr1.deviation();
        assertEquals(exp, real, 0.000001);
    }

    @Test
    public void testMin() {

        double exp = -3.1;
        double real = arr1.min();

        assertEquals(exp, real, 0.00001);
    }

    @Test
    public void testMax() {
        double exp = 14.7;
        double real = arr1.max();
        assertEquals(exp, real, 0.0001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double exp = 2;
        double real = arr1.findTempClosestToZero();
        assertEquals(exp, real, 0.0001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double exp = 10;
        double real = arr1.findTempClosestToValue(12.0);
        assertEquals(exp, real, 0.0001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] exp = {10.0, -3.1, 2.0};
        double[] real = arr1.findTempsLessThen(12.2);

        assertArrayEquals(exp, real, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] exp = {10.0, 14.7, 2.0};
        double[] real = arr1.findTempsGreaterThen(1.2);

        assertArrayEquals(exp, real, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        TempSummaryStatistics real = arr1.summaryStatistics();
        double av = real.getAvgTemp();
        double dev = real.getDevTemp();
        double mini = real.getMinTemp();
        double maxi = real.getMaxTemp();
        assertEquals(arr1.average(), av, 0.0001);
        assertEquals(arr1.max(), maxi, 0.00001);
        assertEquals(arr1.min(), mini, 0.001);
        assertEquals(arr1.deviation(), dev, 0.00001);
    }

    @Test
    public void testAddTemps() {
        int exp = 7;
        int count = arr1.addTemps(1.0, 38.1, -3);
        assertEquals(exp, count);
    }
    @Test
    public void testTheSameClosestNumbers(){
        double[] temperatureSeries = {-2.0, 3.0, 132.3, 21.5, 22.0, 2.0, -65.0, 8.2, -21.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double closest_to_zero_exp = 2.0;
        double real_closest2zero = seriesAnalysis.findTempClosestToZero();
        assertEquals(closest_to_zero_exp, real_closest2zero, 0.0001);
        double closest_to_value_exp = 2.0;
        double real_closest2value = seriesAnalysis.findTempClosestToValue(0);
        assertEquals(closest_to_value_exp, real_closest2value, 0.0001);
    }
    @Test
    public void testEmptySetter(){
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
    }
}
