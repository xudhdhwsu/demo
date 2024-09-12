package model;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Java_plot extends JFrame{
    public Java_plot(String title, XYSeries series) {
        super(title);
        
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // 创建图表
        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Line",      // 图表标题
                "X",            // X 轴标签
                "Y",            // Y 轴标签
                dataset         // 数据集
        );

        // 自定义图表样式（可选）
        chart.getXYPlot().setForegroundAlpha(1f);

        // 创建图表面板并将其添加到 JFrame 中
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
