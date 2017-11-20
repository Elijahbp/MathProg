import Modules.Lab_6;
import Modules.Lab_7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Modules.Lab_7.MethodOfCyclicCordinateDescent;
import static Modules.Lab_7.MethodOfSteepestDescent;

public class Main {
    public static void main(String[] args) {
        //6-я лабораторная работа
        System.out.println("6-я лабораторная работа:Решение задачи безусловной оптимизации функций одной переменной ");
        double a= 4;
        double b= 6;
        double h= 1;
        double k= 5;
        Map.Entry<Double,Double> evenSearch = Lab_6.EvenSearch(4,6,1,5);
        System.out.println("Метод равномерного поиска");
        System.out.println("F ("+evenSearch.getKey()+")= "+evenSearch.getValue()+" Минимум функции при a="+a+"; b="+b+"; h="+h+"; k="+k);
        System.out.println("Метод золотого сечения");
        Map<String,Double>  goldenSection = Lab_6.GoldenSection(4,6,4.5,5.5);
        System.out.println("Промежуток значений оптимальных точек функции: "+goldenSection.get("Ak")+" - "+ goldenSection.get("Bk"));

        //7-я лабораторная работа
        System.out.println("7-я лабораторная работа:Решение задачи безусловной оптимизации функций одной переменной ");
        double x1 = 1.1;
        double x2 = 1.2;

        ArrayList<Double> xKplus = new ArrayList<>();
        xKplus.add(x1);
        xKplus.add(x2);
        List<Double> lambdaList = new ArrayList<>();
        k = 0;
        while (k <= 1) {
            lambdaList.add(k);
            k += 0.005;
        }
        List<Double> bufX = MethodOfCyclicCordinateDescent(xKplus,lambdaList);
        System.out.println("Метод циклического покоординатного спуска");
        System.out.println("Значение точки x("+bufX.get(0)+","+bufX.get(1)+")");;

        System.out.println("Метод наискорейшего спуска");
        bufX = MethodOfSteepestDescent(x1,x2,lambdaList);
        System.out.println("Значение точки x("+bufX.get(0)+","+bufX.get(1)+")");;

    }
}
