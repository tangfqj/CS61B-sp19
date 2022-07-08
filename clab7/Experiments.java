import edu.princeton.cs.algs4.StdRandom;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        int n = 5000;      // number of items

        BST<Integer> bst = new BST<>();
        int[] items = StdRandom.permutation(100000, n);
        double[] numOfItems = new double[n];
        double[] averageDepths = new double[n];
        double[] optimalAverageDepths = new double[n];

        for(int i = 0; i < n; i++){
            bst.add(items[i]);
            numOfItems[i] = i + 1;
            averageDepths[i] = bst.averageDepth();
            optimalAverageDepths[i] = ExperimentHelper.optimalAverageDepth(i + 1);
        }

        XYChart cht = new XYChartBuilder().width(800).height(600).xAxisTitle("Number of Items")
                .yAxisTitle("Average Depth").build();
        cht.addSeries("Random Average Depth", numOfItems, averageDepths);
        cht.addSeries("Optimal Average Depth", numOfItems, optimalAverageDepths);
        new SwingWrapper(cht).displayChart();
    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
