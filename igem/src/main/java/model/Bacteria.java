package model;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.xy.XYSeries;

public class Bacteria {

    public String name;     //存储细菌的名称，之后绘图使用
    private double y;       //毒蛋白引起的最大死亡率
    private double k;       //有效毒蛋白的计算比例
    private double r;       //增长率
    private double k_k;     //环境容纳量
    private double a;       //影响系数
    private double b;
    private Hill hill;      //希尔方程
    private double result;
    private double n;
    public List<Double> Fx = new ArrayList<Double>();       //存储细菌数量
    public List<Bacteria> bacterias = new ArrayList<>();
    public List<Promoter> P = new ArrayList<Promoter>();       //存储细菌所含有的启动子
    public List<Object_behind_promoter> sm = new ArrayList<>();

    /* 
     * 最初的模型
     * 构造方法，输入参数
     * 毒蛋白引起的最大死亡率
     * 有效毒蛋白的计算比例
     * 希尔方程相关系数（希尔系数，产生半数占用时的配体浓度）
     */

    public Bacteria() {
        this.Fx.add(0.0);
    }
    /* 
     * 构造方法什么的之后再说
     */
    public Bacteria(String name, double n0, double y, double k, double r, double k_k, double a, double b, double m, double Xc) {
        this.Fx.add(n0);
        this.name = name;
        this.y = y;
        this.k = k;
        this.r = r;
        this.k_k = k_k;
        this.a = a;     //列表中第二个菌，对
        this.b = b;
        hill = new Hill(m, Xc);
    }

    //计算细菌的数量变化
    public void calculate_bacteria(double CCDB_number, double CCDA_number) {
        n = Fx.get(Fx.size() - 1);
        if (CCDB_number - CCDA_number * k > 0) {
            /* 
             * dn1 / dt = r * n1 * (1 - (n1 + a * n2) / K) - F(ccdb - ccda * k) * n1 * y
             */
            result = n + (n * r - n * hill.calculateFx(CCDB_number - CCDA_number * k) * y - n * r * (n + a * bacterias.get(0).Fx.get(bacterias.get(0).Fx.size() - 1) + b * bacterias.get(1).Fx.get(bacterias.get(1).Fx.size()-1)) / (k_k)) * 0.001;
        }
        else {
            result = n + n * r  * (1 - (n + a * bacterias.get(0).Fx.get(bacterias.get(0).Fx.size() - 1) + b * bacterias.get(1).Fx.get(bacterias.get(1).Fx.size()-1)) / (k_k)) * 0.001;
        }
        Fx.add(result);
    }

    
    public void A(){
        n = Fx.get(Fx.size() - 1);
        for (int i = 0; i < P.size(); i++) {
            calculate(n, i, bacterias.get(0), bacterias.get(1));
        }
        
        this.calculate_bacteria(getprotein("CCDB"), getprotein("CCDA"));
        
    }

    //这个里面还是存在误差，但是应该可以忽略吧，之后再修改吧
    private void calculate(double n, int i, Bacteria another_bacteria_1, Bacteria another_bacteria_2) {
        // System.out.println(i);
        // System.out.println(P.get(i).type);
        //如果是组成型启动子,无论表达什么，都是这个规律的，但是在最开始构建的时候，就应该将那个，CCDB给排除在外。
        if (P.get(i).type.equals("constitutive promoter")) {
            // System.out.println(1);
            // sm.get(i).result = P.get(i).Strength * n * 0.001 + sm.get(i).Fx.get(sm.get(i).Fx.size()-1);
            sm.get(i).result = P.get(i).Strength * n * 0.001 - sm.get(i).Fx.get(sm.get(i).Fx.size()-1) * sm.get(i).c * 0.001 + sm.get(i).Fx.get(sm.get(i).Fx.size()-1);
            sm.get(i).Fx.add(sm.get(i).result);
        }
        else {
            //先获取影响该启动子的信号分子的量
            // System.out.println(this.name + "----" +P.get(i).name);
            double Signal_Molecule = getsm(P.get(i).name);
            // System.out.println("----------------------------");
            // System.out.println(P.get(i).name + Signal_Molecule);
            if(Signal_Molecule > 3) {
                sm.get(i).result = sm.get(i).result + (P.get(i).Basic_Strength + P.get(i).Activated_Strength * P.get(i).hill.calculateFx(Signal_Molecule)) * n * 0.001 - sm.get(i).c * sm.get(i).result * 0.001;
                sm.get(i).Fx.add(sm.get(i).result);
            }
            else {
                sm.get(i).result = sm.get(i).result + (P.get(i).Basic_Strength + P.get(i).Activated_Strength * P.get(i).hill.calculateFx(Signal_Molecule)) * n * 0.5 * 0.001 - sm.get(i).c * sm.get(i).result * 0.001;
                sm.get(i).Fx.add(sm.get(i).result);
            }
        }
    }


    private double getprotein(String name){
        double result = 0;
        for(int i = 0; i < P.size(); i ++){
            if(sm.get(i).name.contains(name)){
                result = sm.get(i).Fx.get(Fx.size() - 1) + result;
            }
        }
        return result;
    }

    private double getsm(String name){

        double result = 0;
        for (int i = 0; i < bacterias.size(); i++) {
            for (int j = 0; j < bacterias.get(i).sm.size(); j++) {
                // if(name.equals("lux")){System.out.println(1);}
                // if(this.name.equals("B")){
                    // System.out.println(true);
                    // System.out.println(bacterias.get(i).sm.get(j).name);
                // }
                // if(bacterias.get(i).sm.get(j).name.contains(name)){System.out.println("----" + bacterias.get(i).sm.get(j).Fx.get(Fx.size() - 1));}
                if(bacterias.get(i).sm.get(j).name.contains(name)){
                    result = bacterias.get(i).sm.get(j).Fx.get(Fx.size() - 1) + result;
                }
            }
        }
        for(int i = 0; i < P.size(); i ++){
            if(sm.get(i).name.contains(name)){
                result = sm.get(i).Fx.get(Fx.size() - 1) + result;
            }
        }
        // System.out.println(result);
        return result;
    }

    /* 
     * 如果只有种间竞争的关系所引起的细菌数量变化
     */
    public void calculate_just_compete(double another_bacteria_number) {
        n = Fx.get(Fx.size() - 1);
        result = n + n * r  * (1 - (n + a * another_bacteria_number) / (k_k)) * 0.001;
        Fx.add(result);
    }

    /* 
     * 如果只有毒蛋白的作用（这个实际意义未知）（之前我在写的时候没考虑到竞争关系的加入）
     */
    public void calculate_just_CCDB(double CCDB_number) {
        n = Fx.get(Fx.size() - 1);
        result = n + n * (r - hill.calculateFx(CCDB_number) * y) * 0.001;
        Fx.add(result);
    }

    /* 
     * 如果同时有毒蛋白和解毒蛋白作用（这里只是为了复原出之前做的一些内容）
     */
    public void calculate_with_CCDB_and_CCDA(double CCDB_number, double CCDA_number) {
        n = Fx.get(Fx.size() - 1);
        if (CCDB_number - CCDA_number * k > 0) {
            result = n + n * (r - hill.calculateFx(CCDB_number - CCDA_number * k) * y) * 0.001;
            }
            else {
                result = n + n * r * 0.001;
            }
        Fx.add(result);
    }

    /* 
     * 如果有毒蛋白作用和竞争的作用
     */
    public void calculate_with_CCDB_and_compete(double another_bacteria_number, double CCDB_number) {
        n = Fx.get(Fx.size() - 1);
        result = n + n * (r - hill.calculateFx(CCDB_number) * y - r * (n + a * another_bacteria_number) / (k_k)) * 0.001;
        Fx.add(result);
    }

    /* 
     * 如果有毒蛋白，解毒蛋白，竞争三种关系
     */
    public void calculate_with_CCDB_and_CCDA_and_compete(double another_bacteria_number, double CCDB_number, double CCDA_number) {
        n = Fx.get(Fx.size() - 1);
        if (CCDB_number - CCDA_number * k > 0) {
            result = n + (n * r - n * hill.calculateFx(CCDB_number - CCDA_number * k) * y - n * r * (n + a * another_bacteria_number) / (k_k)) * 0.001;
            }
            else {
                result = n + n * r  * (1 - (n + a * another_bacteria_number) / (k_k)) * 0.001;
            }
        Fx.add(result);
    }
    
    public XYSeries getXYPlot() {
        XYSeries xySeries = new XYSeries(this.name + "Bacteria");
        for (int i = 0; i < Fx.size(); i++) {
            xySeries.add(i * 0.001, Fx.get(i));
        }
        return xySeries;
    }
}
