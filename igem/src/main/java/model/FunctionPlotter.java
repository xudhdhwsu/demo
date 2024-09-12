package model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class FunctionPlotter extends ApplicationFrame {

    public FunctionPlotter(String title, String name_A, String name_B, XYSeries series1, XYSeries series2) {
        super(title);
        JFreeChart chart = createChart(createDataset(series1, series2), name_A, name_B);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    private XYSeriesCollection createDataset(XYSeries series1, XYSeries series2) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series1);

        dataset.addSeries(series2);

        return dataset;
    }

    private JFreeChart createChart(XYSeriesCollection dataset, String name_A, String name_B) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Function Plotter",
                name_A,
                name_B,
                dataset
        );
        return chart;
    }

    // public static void main(String[] args) {
        // FunctionPlotter plotter = new FunctionPlotter("Function Plotter");
        // plotter.pack();
        // RefineryUtilities.centerFrameOnScreen(plotter);
        // plotter.setVisible(true);
    // }
}