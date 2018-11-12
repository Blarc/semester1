import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class naloga2 {

    public static class Malloc {
        int vseh = 0;
        int[][] tab;
        int[] idTab;
        int length;
        int atm = 0;

        public void init(int size) {
            this.length = size;
            this.tab = new int[size][2];
            this.idTab = new int[size+1];
            this.tab[0][1] = size;
        }


        //v zadnjo se zapise oboje
        public boolean alloc(int size, int id) {
            if (idTab[id-1] == 0) {
                for (int i = atm; i < length; i += tab[i][1]) {
                    if (tab[i][1] >= size && tab[i][0] == 0) {
                        this.vseh++;
                        idTab[id - 1] = i + 1;
                        if (i + size < length && tab[i + size][0] == 0) {
                            tab[i + size][1] = tab[i][1] - size;
                            tab[i + tab[i][1] - 1][1] = tab[i][1] - size;
                        }
                        tab[i][0] = id;
                        tab[i][1] = size;
                        tab[i + size - 1][0] = id;
                        if (atm + size < length && atm == i) {
                            atm += size;
                        }
                        return true;
                    }
                }
            }

            return false;
        }

        // THIS WORKS !!!
        public int free(int id) {
            if (idTab[id-1] > 0) {
                if (idTab[id - 1] - 1 < atm) {
                    atm = idTab[id - 1] - 1;
                }

                int mta = idTab[id - 1] - 1;
                int res = tab[mta][1];
                int why = tab[mta][1];
                int next = tab[mta][1];

                if (mta + next < length && tab[mta + next][0] == 0) {
                    tab[mta + next - 1][0] = 0;
                    int temp = next;

                    res += tab[mta + next][1];
                    next += tab[mta + next][1];

                    tab[mta + temp][1] = 0;
                }

                if (mta - 1 >= 0 && tab[mta - 1][0] == 0) {
                    next += tab[mta - 1][1];
                    res += tab[mta - 1][1];

                    tab[mta][0] = 0;

                    tab[mta][1] = 0;
                    int temp = tab[mta - 1][1];
                    tab[mta - 1][1] = 0;

                    mta -= temp;
                }

                tab[mta][0] = 0;
                tab[mta][1] = res;
                tab[mta + next - 1][0] = 0;
                tab[mta + next - 1][1] = res;
                idTab[id - 1] = 0;

                return why;
            }
            return 0;
        }

        //CLEAN UP AWAITS
        public void defrag(int a) {
            for (int j = 0; j < a; j++) {
                while(tab[atm][0] != 0 && atm < length) {
                    atm += tab[atm][1];
                }
                
                if (tab[atm][0] != 0) {
                	return;
                }
                
                int next = tab[atm][1];
                int temp = next;

                tab[atm][0] = tab[atm + next][0];
                tab[atm][1] = tab[atm + next][1];
                tab[atm + next][0] = 0;
                tab[atm + next][1] = 0;
                int g = tab[atm][1];
                tab[atm + g - 1][0] = tab[atm][0];

                tab[atm + next - 1][1] = 0;
                tab[atm + g + next - 1][0] = 0;

                if (atm + g + next < length && tab[atm + g + next][0] == 0) {
                    next += tab[atm + g + next][1];
                    tab[atm + g + temp][1] = 0;
                }

                tab[atm][1] = g;
                tab[atm + g][1] = next;
                tab[atm + g + next - 1][1] = next;
                idTab[tab[atm][0] - 1] -= temp;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        if(args.length < 1) {
            System.out.println("Uporaba: java naloga2 <vhodna datoteka> <izhodna datoteka>");
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String[] line;
        line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        System.out.println("n: " + n);


        Malloc plswork = new Malloc();
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(",");
            String ukaz = line[0];
            int bard = Integer.parseInt(line[1]);
            switch(ukaz) {
                case "i":
                    //System.out.println(bard);
                    plswork.init(bard);
                    break;
                case "a":
                    int sion = Integer.parseInt(line[2]);
                    //System.out.println(bard + " " + sion);
                    plswork.alloc(bard, sion);
                    break;
                case "f":
                    //System.out.println(bard);
                    plswork.free(bard);
                    break;
                case "d":
                    //System.out.println(bard);
                    plswork.defrag(bard);
                    break;
                    default: System.out.println("invalid");
            }
           /* if (i == n-1) {
                System.out.println(Arrays.toString(line));
            } */
        }
        br.close();


        int[][] tab = plswork.tab;
        for (int i = 0; i < plswork.length; i += tab[i][1]) {
            int id = tab[i][0];
            int start = i;
            int end = start + tab[i][1] - 1;
            if (tab[i][0] != 0) {
                System.out.println(id + "," + start + "," + end);
            }

        }

        //System.out.println("ATM: " + plswork.atm);
        //System.out.println(Arrays.toString(plswork.idTab));
        //System.out.println(Arrays.deepToString(plswork.tab));
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed Time: " + elapsedTime + " ms");
    }
}
