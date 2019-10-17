package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {


    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        setTemperatureSeries(new double[]{0});
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        setTemperatureSeries(temperatureSeries);
    }

    public double[] getTemperatureSeries() {
        return Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }


    public void setTemperatureSeries(double[] temperatureSeries) {
        this.temperatureSeries =
                Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public void checkEmptyAndNormal() {
        final int LOWEST = -273;
        if (temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < LOWEST) {
                throw new InputMismatchException();
            }
        }
    }


    public double average() {
        checkEmptyAndNormal();
        double summ = 0;
        int n = temperatureSeries.length;
        for (int i = 0; i < n; i++) {
            summ += temperatureSeries[i];
        }
        return summ / n;

    }

    public double deviation() {
        checkEmptyAndNormal();
        double av = average();
        double summ = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            summ += (temperatureSeries[i] - av) * (temperatureSeries[i] - av);
        }
        return Math.sqrt(summ / temperatureSeries.length);
    }

    public double min() {
        checkEmptyAndNormal();
        double mini = Double.MAX_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < mini) {
                mini = temperatureSeries[i];
            }
        }
        return mini;
    }

    public double max() {
        checkEmptyAndNormal();
        double maxi = Double.MIN_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > maxi) {
                maxi = temperatureSeries[i];
            }
        }
        return maxi;
    }

    public double findTempClosestToZero() {
        checkEmptyAndNormal();
        double closest = Double.MAX_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (Math.abs(temperatureSeries[i]) < Math.abs(closest)) {
                closest = temperatureSeries[i];
            } else if (Math.abs(temperatureSeries[i]) - Math.abs(closest)
                    < Double.MIN_VALUE && temperatureSeries[i] > closest) {

                closest = temperatureSeries[i];

            }
        }

        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        checkEmptyAndNormal();
        double diff = Double.MAX_VALUE;
        double closest = 0;
        double newDiff;
        for (int i = 0; i < temperatureSeries.length; i++) {
            newDiff = Math.abs(temperatureSeries[i] - tempValue);
            if (newDiff < diff) {
                diff = newDiff;
                closest = temperatureSeries[i];
            } else if (Math.abs(newDiff - diff) < Double.MIN_VALUE
                    && temperatureSeries[i] > closest) {

                closest = temperatureSeries[i];

            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkEmptyAndNormal();
        double[] lessLst = new double[temperatureSeries.length];
        int j = 0;

        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                lessLst[j] = temperatureSeries[i];
                j++;

            }
        }
        lessLst = Arrays.copyOfRange(lessLst, 0, j);
        return lessLst;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkEmptyAndNormal();
        double[] greaterLst = new double[temperatureSeries.length];
        int j = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > tempValue) {
                greaterLst[j] = temperatureSeries[i];
                j++;
            }
        }
        greaterLst = Arrays.copyOfRange(greaterLst, 0, j);
        return greaterLst;
    }

    public TempSummaryStatistics summaryStatistics() {
        TempSummaryStatistics analysis =
                new TempSummaryStatistics(average(),
                        deviation(), min(), max());
        return analysis;
    }

    public int addTemps(double... temps) {
        checkEmptyAndNormal();
        int count = 0;
        int old = temperatureSeries.length;
        double[] oldLst = Arrays.copyOf(temperatureSeries, old);
        temperatureSeries = new double[temperatureSeries.length * 2];
        for (int i = 0; i < old; i++) {
            temperatureSeries[i] = oldLst[i];
            count += 1;
        }
        for (int j = 0; j < temps.length; j++) {
            temperatureSeries[old + j + 1] = temps[j];
            count += 1;
        }
        return count;
    }

}
