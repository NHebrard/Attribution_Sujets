package views.result;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import models.bean.Model;
import models.stats.Statistic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * Classe permettant la cr�ation d'un PieChart � partir du model 
 * 
 * Le piechart cr�e repr�sentera la proportion des �l�ves ayant eu leur ni�me choix
 *
 */
public class ResultStatsPieChart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static PiePlot pieplot;

	private static PieDataset createDataset(Model model)
    {
		Statistic stats = new Statistic(model);
            DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
           
            for (int i = 0 ; i < model.getConstraint().getNbMaxChoice() ; i++) {
            	defaultpiedataset.setValue((i+1) + "� choix", Math.round(stats.PortionWhichGetChoiceN(i+1)*100)/100);
            }
            
            if (stats.PortionWhichGetChoiceN(-2) != 0) {
            	Double value = new Double(stats.PortionWhichGetChoiceN(-2) * 100);
            	defaultpiedataset.setValue("Par d�faut avec choix", Math.round(value) / 100);
            }
            if (stats.PortionWhichGetChoiceN(-1) != 0)
            	defaultpiedataset.setValue("Par d�faut sans choix", Math.round(stats.PortionWhichGetChoiceN(-1)*100)/100);
            return defaultpiedataset;
    }

    private static JFreeChart createChart(PieDataset piedataset)
    {
            JFreeChart jfreechart = ChartFactory.createPieChart("Statistiques de la r�partition", piedataset, true, true, false);
            pieplot = (PiePlot)jfreechart.getPlot();
            pieplot.setNoDataMessage("Aucune donn�e disponible");
            pieplot.setExplodePercent("Two", 0.20000000000000001D);
            pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
            pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));
            pieplot.setLabelGenerator(null);
            pieplot.setInteriorGap(0.00D);
            return jfreechart;
    }

    /**
     * @param model le model de l'application ayant �t� r�solu
     * @return le JPanel contenant le piechart g�n�r�
     */
    public static JPanel createDemoPanel(Model model)
    {   	
            JFreeChart jfreechart = createChart(createDataset(model));	
            JPanel panel = new ChartPanel(jfreechart);
            panel.addMouseWheelListener(new MouseWheelListener() {
				
				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					pieplot.handleMouseWheelRotation(e.getWheelRotation());
				}
			});
            
            JPanel myPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
    		gbc.gridx = 0;
    		gbc.gridy = 0;
    		gbc.weightx = 1;
    		gbc.weighty = 1;
    		gbc.fill = GridBagConstraints.BOTH;
            myPanel.add(panel, gbc);
            return myPanel;
    }
}
