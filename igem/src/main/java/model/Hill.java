package model;

import java.util.ArrayList;
import java.util.List;

public class Hill {

    private double m;       //希尔系数
    private double Xc;      //产生半数占用时的配体浓度
    public List<Double> Fx = new ArrayList<Double>();
    private double result;
    /* 
     * 构造方法，输入参数
     * 希尔系数
     * 产生半数占用时的配体浓度
     */
    public Hill(double m, double Xc) {
        this.m = m;
        this.Xc = Xc;
        double t = 0;
        for (int i = 0; i < 1000; i++) {
            result = Math.pow(t, m) / (Math.pow(Xc, m) + Math.pow(t, m));
            t += 0.001;
            // 将result添加至列表中
            Fx.add(result);
        }
    }

    public double calculateFx(double x) {
        return Math.pow(x, m) / (Math.pow(Xc, m) + Math.pow(x, m));
    }


}
