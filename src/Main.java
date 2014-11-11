import java.io.*;
import java.util.*;

public class Main {

    public static void readUselessLine(BufferedReader br, int n) throws IOException {
        String line;
        for (int i = 0; i < n; i++) {
            line = br.readLine();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileReader in = new FileReader("source.txt");
        BufferedReader br = new BufferedReader(in);
        String line = new String();
        readUselessLine(br, 3);

        // lire le nombre de ville et créer les deux tableau
        line = br.readLine();
        String[] str = line.split(" : ");
        int nbrVille = Integer.valueOf(str[1]);
        double[] X = new double[nbrVille];
        double[] Y = new double[nbrVille];

        readUselessLine(br, 2);

        // lire les coordonées
        for (int i = 0; i < nbrVille; i++) {
            line = br.readLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\t");
            String uselessId = tokenizer.nextToken();
            X[i] = Double.valueOf(tokenizer.nextToken());
            Y[i] = Double.valueOf(tokenizer.nextToken());
        }
        double[][] distances = new double[nbrVille][nbrVille];
        for (int i = 0; i < nbrVille; i++) {
            for (int j = 0; j < nbrVille; j++) {
                double d = Math.sqrt(Math.pow((X[i] - X[j]), 2) + Math.pow((Y[i] - Y[j]), 2));
                distances[i][j] = d;
            }
        }

        PrintWriter writer = new PrintWriter("distances.txt", "UTF-8");
        line = "";
        for (int i = 0; i < nbrVille; i++)
            line += "\t" + (i+1);
        writer.println(line + "\ty\n");
        for (int i = 0; i < nbrVille; i++){
            line = "" + (i+1);
            for (int j = 0; j < nbrVille; j++) 
                line += "\t" + String.format("%.2f", distances[i][j]);
            writer.println(line + "\n");
        }
        writer.close();
        
    }
}
