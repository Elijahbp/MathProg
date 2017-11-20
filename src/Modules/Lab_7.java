package Modules;

import java.util.*;

public class Lab_7{

    private static final double EPS =0.001;
    //Метод циклического поординатного спуска
     public static List<Double> MethodOfCyclicCordinateDescent(List<Double> bXk,List<Double> lambdaList) {

         Map<Double, Double> listFunction = new HashMap<>();

         List<Double> xKplus = new ArrayList<>();
         xKplus.addAll(bXk);
         List<Double> xK = new ArrayList<>();
         List<Double> Y = new ArrayList<>();
         Y.addAll(xKplus);
         List<Double> bufY = new ArrayList<>();
         bufY.addAll(Y);
         double bufLambda = 0;
         double Yi=0;
         do {
             xK.clear();
             xK.addAll(xKplus);
             for (int i =0;i<xKplus.size();i++) {
                 Yi = Y.get(i);
                 for (double lambda: lambdaList){
                     bufY.set(i,Yi + lambda);
                     listFunction.put(lambda,Function_7lb(bufY.get(0),bufY.get(1)));
                     bufY.set(i,Yi);
                 }
                 bufLambda = Lab_6.getMin(lambdaList.get(0),listFunction).getKey();
                 listFunction.clear();
                 Y.set(i,Yi+bufLambda);
             }
             xKplus.clear();
             xKplus.addAll(Y);
         }while (!proofDif(xKplus,xK));
         return xKplus;
     }

     private static boolean proofDif(List<Double> xKplus1,List<Double> xK){
         double Sum=0;
         for (int i = 0; i < xKplus1.size(); i++) {
             Sum+=Math.pow((xKplus1.get(i)-xK.get(i)),2);
         }
         if (Math.sqrt(Sum) < EPS){
             return true;
         }
         else return false;
     }



    //Метод наискорейшего спуска
    public static List<Double> MethodOfSteepestDescent(double x1,double x2,List<Double> lambdaList){
         double X1i = x1;
         double X2i = x2;
         double di=0;
         Map<Double, Double> listFunction = new HashMap<>();
         List<Double>listXi  = new ArrayList<>();
         listXi.add(X1i);listXi.add(X2i);
         double Yi=0; double bufLambda=0;
         do {
             di = -1* grad_Function(listXi.get(0),listXi.get(0));
             for (int i = 0; i < listXi.size(); i++) {
                 Yi = listXi.get(i);
                 for (Double lambda:lambdaList) {
                     listXi.set(i,Yi + lambda);
                     listFunction.put(lambda,Function_7lb(listXi.get(0),listXi.get(1)));
                     listXi.set(i,Yi);
                 }
                 bufLambda = Lab_6.getMin(lambdaList.get(0),listFunction).getKey();
                 listFunction.clear();
             }
             listXi.set(0,listXi.get(0)+bufLambda*di);
             listXi.set(1,listXi.get(1)+bufLambda*di);
         }while (grad_Function(listXi.get(0),listXi.get(1)) > EPS);
         return listXi;
    }

    private static double grad_Function(double x1, double x2 ) {
         return 6 * x1 + 30 * x2 +5;
    }

    private static double Function_7lb(double x1, double x2){
        return Math.pow(x1,2) + 4*x1*x2 + 13*Math.pow(x2,2) + 5* x2;
    }
}
