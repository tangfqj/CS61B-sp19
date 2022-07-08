/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int i = 0;
        int pathLengthSum = 0;
        while(N >= Math.pow(2, i)){
            pathLengthSum += i * Math.pow(2, i);
            N -= Math.pow(2, i);
            i++;
        }
        pathLengthSum += N * i;
        return pathLengthSum;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        double OAD = (double) optimalIPL(N) / N;
        return OAD;
    }

//    public static void main(String[] args){
//        int op1 = optimalIPL(1);
//        int op2 = optimalIPL(3);
//        int op3 = optimalIPL(8);
//        System.out.println(op1);
//        System.out.println(op2);
//        System.out.println(op3);
//        double oad1 = optimalAverageDepth(3);
//        double oad2 = optimalAverageDepth(8);
//        System.out.println(oad1);
//        System.out.println(oad2);
//    }
}
