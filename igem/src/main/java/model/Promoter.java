package model;

public class Promoter {

    // private String[] Quorum_sensing_Promoter_name = new String[]{"tra", "lux", "abc"};

    
    // private String[] Signal_Molecule_name = new String[]{"sm_tra", "sm_lux", "sm_abc"};
    // private String[] Quorum_sensing_Protein_name = new String[]{"CCDB", "CCDA"};
    public String type;
    public String name;
    public double Strength;
    public double Basic_Strength;
    public double Activated_Strength;
    public double m;
    public double Xc;
    public Hill hill;

    
    public Promoter(double Strength, String express) {
        this.type = "constitutive promoter";
        this.Strength = Strength;
    }

    public Promoter(String name, double Basic_Strength, double Activated_Strength, String express, double m, double Xc) {
        this.type = "quorum sensing promoters";
        this.name = name;
        this.Basic_Strength = Basic_Strength;
        this.Activated_Strength = Activated_Strength;
        this.m = m;
        this.Xc = Xc;
        this.hill = new Hill(m, Xc);
    }

}
