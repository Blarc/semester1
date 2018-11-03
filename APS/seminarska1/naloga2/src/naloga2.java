import java.util.Arrays;

public class naloga2 {

    public static class Malloc {
        int[][] tab;
        int[] idTab;
        int length;
        int atm = 0;
        int n = 0;

        public void init(int size) {
            this.length = size;
            this.tab = new int[size][2];
            this.idTab = new int[size+1];
            this.tab[0][1] = size;
        }

        public boolean alloc(int size, int id) {
            for (int i = atm; i <= n; i++) {
                if (tab[i][1] >= size && tab[i][0] == 0) {
                    idTab[id-1] = i;
                    tab[i][0] = id;
                    tab[i][1] = size;
                    tab[i+1][1] = length - tab[i][1];
                    length -= tab[i][1];
                    this.atm++;
                    this.n++;
                    return true;
                }
            }

            return false;
        }

        public int free(int id) {
            this.atm = idTab[id-1];
            int temp = tab[idTab[id-1]][1];
            tab[idTab[id-1]][0] = 0;
            idTab[id-1] = 0;
            return temp;
        }

        public void defrag(int a) {
            for (int i = 0; i < this.n; i++) {
                if (tab[i][0] == 0) {
                    int[] temp = tab[i];
                    tab[i] = tab[i+1];
                    tab[i+1] = temp;
                    idTab[tab[i][0]-1] = i;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Malloc nov = new Malloc();
        nov.init(15);
        nov.alloc(5,1);
        nov.alloc(3,2);
        nov.alloc(2,3);
        nov.free(2);
        nov.alloc(4, 4);
        nov.defrag(1);
        nov.alloc(2, 5);

        System.out.println(Arrays.toString(nov.idTab));
        System.out.println(Arrays.deepToString(nov.tab));
    }
}
