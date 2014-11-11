import java.io.*;

/**
 *
 * @author Oussama ZAKI
 */
public class TSP {
    
    private double[][] cost;

    public static void main(String[] args) throws IOException {
        TSP tsp = new TSP();
        tsp.readInput2("distances.txt");
        tsp.solve();
    }

    public void readInput2(String str) throws IOException {
        FileReader in = new FileReader(str);
        BufferedReader br = new BufferedReader(in);
        String line = new String();
        line = br.readLine();
        int n = 10, i = 0;
        cost = new double[n][n];
        while (i < 10) {
            line = br.readLine();
            if (!line.isEmpty()) {
                String[] costStr = line.split("\t");
                for (int j = 0; j < 10; j++) {
                    cost[i][j] = Double.parseDouble(costStr[j + 1].replace(',', '.'));
                }
                i++;
            }
        }
    }

    public void solve() {
        int route[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // resultat
        int[] done = new int[10];   //villes deja visitées

        /* calculer la route minimale */
        int k = 1, i = 0;
        // k pour remplir le resultat, i pour itérer entre les ville
        // k = 1, on skip la premiere ville c'est la ville du depart
        while (k < 10) {
            done[k - 1] = i; // chaque ville visité on l'ajoute a ce tableau
            route[k] = min(cost[i], done); // à la i ém ville on cherche la ville la plus proche
            // tout en ignorant les ville deja visité
            i = route[k]; // on commence à partir de cette ville trouvé
            k++; // on incrémente le nombre de ville visité
        }

        /* affichage */
        double totalCost = 0;
        for (int j = 0; j < 10; j++) {
            System.out.print((route[j] + 1) + " -> ");
            totalCost += cost[route[j]][route[j + 1]];
        }
        System.out.println("1");
        System.out.println("Total Cost : " + totalCost);
    }

    int min(double[] a, int... reject) {
        int resIndex = 0;
        double res = Double.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            boolean skip = false;
            for (int r : reject) {
                if (i == r) {
                    skip = true;
                    break;
                }
            }
            if (!skip) {
                if (a[i] < res) {
                    res = a[i];
                    resIndex = i;
                }
            }
        }
        return resIndex;
    }
}
