package model;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.xy.XYSeries;

public class Object_behind_promoter {

    public String name;
    public double a = 0.5;
    public double b = 0.7;
    public double c;
    public double result;
    public List<Double> Fx = new ArrayList<>();

    public Object_behind_promoter(String name, double c) {
        Fx.add(0.0);
        this.name = name;
        this.c = c;
    }

    public XYSeries getXYPlot() {
        XYSeries xySeries = new XYSeries("Signal_Molecule");
        for (int i = 0; i < Fx.size(); i++) {
            xySeries.add(i * 0.001, Fx.get(i));
        }
        return xySeries;
    }
}
