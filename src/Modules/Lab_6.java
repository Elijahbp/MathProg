package Modules;

import java.util.*;

public class Lab_6 {
    private static final double EPS =0.001;
    //6-я лабораторная: Решение задачи безусловной оптимизации функций одной переменной

    //Метод равномерного поиска
    //а - левая граница, b - правая граница, h - шаг, k - кратность шага (h2=h1/k)
    public static Map.Entry<Double,Double> EvenSearch(double a, double b, double h, double k){
        Map<Double, Double> listF = getListFunction(a, b, h);
        Map.Entry<Double,Double> minF = getMin(a,listF);
        double bufBackMinF = 0;
        do {
            bufBackMinF = minF.getValue();
            a = minF.getKey()-h; b = minF.getKey()+h;
            h = h/k;
            listF = getListFunction(a, b, h);
            minF = getMin(a,listF);
        }while ((bufBackMinF - minF.getValue()) >= EPS);
        return minF;
    }

    //Метод золотого сечения
    //а1 - левая граница, b1 - правая граница, c1 и d1 - случайные числа в промежутке [a1,b1], при этом c<d
    public static Map<String,Double> GoldenSection(double a1, double b1, double c1, double d1){
        double FunctionC = Function_6_Lab(c1);
        double FunctionD = Function_6_Lab(d1);
        double ak,bk,ck,dk;
        double b_ak=a1,b_bk=b1,b_ck=c1,b_dk=d1;
        do {
            if (FunctionC > FunctionD){
                //интервал cb
                ak = b_ck;
                bk = b_bk;

                ck = b_dk;
                dk = ck + 0.382*(bk-ck);
                b_ak = ak; b_bk = bk; b_ck = ck; b_dk = dk;
            }else {
                // интервал ad
                ak = b_ak;
                bk = b_dk;

                dk = b_ck;
                ck = ak + 0.618*(dk - ak);
                b_ak = ak; b_bk = bk; b_ck = ck; b_dk = dk;
            }
            FunctionC = Function_6_Lab(ck);
            FunctionD = Function_6_Lab(dk);
        }while ((bk - ak)>=EPS);
        Map<String,Double> RangeOfValues = new HashMap<>();
        RangeOfValues.put("Ak",ak);
        RangeOfValues.put("Bk",bk);
        return RangeOfValues;
    }

    //получение списка функций в заданном диапазоне чисел
    private static Map<Double,Double> getListFunction(double a,double b, double h){
        Map<Double,Double> listF= new HashMap<Double, Double>();
        double bufValue=a;
        double bufF=0;
        while (bufValue<=b){
            bufF = Function_6_Lab(bufF);
            listF.put(bufValue,bufF);
            bufValue+=h;
        }
        return listF;
    }

    //Полуение минимального значения из заданного списка функций
    static Map.Entry<Double,Double> getMin(double a,Map<Double,Double> listFunction){
        double minValueF = listFunction.get(a);
        Map.Entry<Double,Double> minF = new AbstractMap.SimpleEntry<Double, Double>(a,listFunction.get(a));
        for (Map.Entry<Double,Double> Fi: listFunction.entrySet()){
            if (minValueF > Fi.getValue()) {
                minValueF = Fi.getValue();
                minF = Fi;
            }
        }
        return  minF;
    }



    private static double Function_6_Lab(double xi){
       return  -4 * xi + Math.exp(xi - 0.2);
    }

}

