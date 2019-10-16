package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    public double[] temperatureSeries;

    public void setTemperatureSeries(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }

    public void checkEmptyAndNormal(){
        if (temperatureSeries.length < 1){
            throw new IllegalArgumentException("Array must not be empty");
        }
        for(int i = 0; i < temperatureSeries.length; i++){
            if (temperatureSeries[i] < -273){
                throw new InputMismatchException();
            }
        }
    }

    public TemperatureSeriesAnalysis() {
        setTemperatureSeries(new double[]{0});
    }


    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        setTemperatureSeries(temperatureSeries);
    }

    public double average() {
        checkEmptyAndNormal();
        double summ = 0;
        int n = temperatureSeries.length;
        for (int i = 0; i < n; i++){
            summ += temperatureSeries[i];
        }
        return summ/n;

    }

    public double deviation() {
        checkEmptyAndNormal();
        double av = average();
        double summ = 0;
        for (int i = 0; i < temperatureSeries.length; i++){
            summ += Math.pow(temperatureSeries[i] - av, 2);
        }
        return Math.sqrt(summ/temperatureSeries.length);
    }

    public double min() {
        checkEmptyAndNormal();
        double mini = Double.MAX_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++){
            if (temperatureSeries[i] < mini){
                mini = temperatureSeries[i];
            }
        }
        return mini;
    }

    public double max() {
        checkEmptyAndNormal();
        double maxi = Double.MIN_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++){
            if (temperatureSeries[i] > maxi){
                maxi = temperatureSeries[i];
            }
        }
        return maxi;
    }

    public double findTempClosestToZero() {
        checkEmptyAndNormal();
        double closest = Double.MAX_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++){
            if (Math.abs(temperatureSeries[i]) < Math.abs(closest)){
                closest = temperatureSeries[i];
            }
            else if (Math.abs(temperatureSeries[i]) == Math.abs(closest)){
                if (temperatureSeries[i] > closest){
                    closest = temperatureSeries[i];
                }
            }
        }

        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        checkEmptyAndNormal();
        double diff = Double.MAX_VALUE;
        double closest = 0;
        double new_diff;
        for (int i = 0; i < temperatureSeries.length; i++){
            new_diff = Math.abs(temperatureSeries[i] - tempValue);
            if (new_diff < diff){
                diff = new_diff;
                closest = temperatureSeries[i];
            }
            else if (new_diff == diff){
                if (temperatureSeries[i] > closest){
                    closest = temperatureSeries[i];
                }
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkEmptyAndNormal();
        double[] less_lst = new double[temperatureSeries.length];
        int j = 0;

        for (int i = 0; i < temperatureSeries.length; i++){
            if (temperatureSeries[i] < tempValue){
                less_lst[j] = temperatureSeries[i];
                j++;

            }
        }
        less_lst = Arrays.copyOfRange(less_lst, 0, j);
        return less_lst;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkEmptyAndNormal();
        double[] greater_lst = new double[temperatureSeries.length];
        int j = 0;
        for (int i = 0; i < temperatureSeries.length; i++){
            if (temperatureSeries[i] > tempValue){
                greater_lst[j] = temperatureSeries[i];
                j++;
            }
        }
        greater_lst = Arrays.copyOfRange(greater_lst, 0, j);
        return greater_lst;
    }

    public TempSummaryStatistics summaryStatistics() {
        TempSummaryStatistics analysis = new TempSummaryStatistics(average(), deviation(),min(),max());
        return analysis;
    }

    public int addTemps(double... temps) {
        checkEmptyAndNormal();
        int count = 0;
        double[] new_lst = new double[temperatureSeries.length * 2];
        for (int i = 0; i < temperatureSeries.length; i++){
            new_lst[i] = temperatureSeries[i];
            count += 1;
        }
        for (int j = 0; j < temps.length; j++){
            new_lst[temperatureSeries.length + j + 1] = temps[j];
            count += 1;
        }
        return count;
    }

}
