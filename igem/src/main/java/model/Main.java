package model;

// import javax.swing.SwingUtilities;

public class Main {
    //目前只考虑两个菌，之后试图拓展到三个菌，光诱导启动子，之后也可以添加。
    // private String[] bacteria_name = new String[]{"A", "B", "C"};
    private Bacteria A;
    private Bacteria B;
    private Bacteria C;
    // private Promoter p;

    public Main(){

        abcd();

    }

    private void abcd() {
        //系统的初始化
        //点击双菌株按钮，点击三菌株按钮，两个事件
        A = new Bacteria("A", 2, 3.5, 0.80, 2.0, 10, 0.4, 0, 2, 4);
        B = new Bacteria("B", 3, 3.0, 0.85, 1.8, 20, 0.3, 0, 2, 5);
        C = new Bacteria();

        A.bacterias.add(B);
        A.bacterias.add(C);

        B.bacterias.add(A);
        B.bacterias.add(C);

        C.bacterias.add(A);
        C.bacterias.add(B);

        // Object_behind_promoter sm1 = new Object_behind_promoter("sm_tra", 0.5);
        Object_behind_promoter sm2 = new Object_behind_promoter("sm_lux", 0.5);
        // Object_behind_promoter sm3 = new Object_behind_promoter("sm_sal", 0.8);
        // Object_behind_promoter CCDB1 = new Object_behind_promoter("CCDB", 0.5);
        // Object_behind_promoter CCDA1 = new Object_behind_promoter("CCDA", 0.5);

        Object_behind_promoter CCDB2 = new Object_behind_promoter("CCDB", 0.5);
        Object_behind_promoter CCDA2 = new Object_behind_promoter("CCDA", 0.5);


        // p = new Promoter(0.9, "sm_tra");
        // A.P.add(p);
        // A.sm.add(sm1);
        // Promoter p1 = new Promoter("tra", 0.01, 4, "CCDB", 2, 3);
        // A.P.add(p1);
        // A.sm.add(CCDB1);
        // p1 = new Promoter("lux", 0.01, 4.2, "CCDA", 2, 3);
        // A.P.add(p1);
        // A.sm.add(CCDA1);

        Promoter p2 = new Promoter(0.9, "sm_lux");
        B.P.add(p2);
        B.sm.add(sm2);
        // Promoter p32 = new Promoter(0.45, "sm_lux");
        // B.P.add(p32);
        // B.sm.add(sm2);
        Promoter p3 = new Promoter("lux", 0.01, 4.2, "CCDB", 2, 3);
        B.P.add(p3);
        B.sm.add(CCDB2);
        Promoter p4 = new Promoter("tra", 0.01, 4, "CCDA", 2, 3);
        B.P.add(p4);
        B.sm.add(CCDA2);

        for (int i = 0; i < 100000; i++) {
            A.A();
            B.A();
        }

        new FunctionPlotter("Bacteria number", "A", "B", A.getXYPlot(), B.getXYPlot());
        

    }

    public static void main(String[] args) {
        new Main();

    }

}