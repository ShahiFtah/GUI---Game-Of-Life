public class Modell {
    private int rad;
    private int kol;
    private boolean[][] celler;

    public Modell(int rad, int kol) {
        this.rad = rad;
        this.kol = kol;
        celler = new boolean[rad][kol];
    }

    public int hentRad() {
        return rad;
    }

    public int hentKol() {
        return kol;
    }

    public boolean celleTilstand(int rad, int kol) { //returnerer cellen om den er levende eller død
        return celler[rad][kol];
    }

    public void settCelleLevende(int rad, int kol, boolean alive) {
        celler[rad][kol] = alive;
    }

    public void fyllMedTilfeldigeCeller() {
        for (int row = 0; row < rad; row++) {
            for (int col = 0; col < kol; col++) {
                boolean lever = Math.random() <= 0.3333;
                celler[row][col] = lever;
            
            }
        }
    }

    public void nullstill() {
        for (int row = 0; row < rad; row++) {
            for (int col = 0; col < kol; col++) {
                celler[row][col] = false;
            }
        }
    }

    public void oppdater() {
        boolean[][] celleListen = new boolean[rad][kol];

        for (int row = 0; row < rad; row++) {
            for (int col = 0; col < kol; col++) {
                int antallLevendeNaboer = tellLevendeNaboer(row, col);
                celleListen[row][col] = celler[row][col];
                
                if (celler[row][col]) {
                    if (antallLevendeNaboer == 2 || antallLevendeNaboer == 3 ) {
                        // Cellen har 2 eller 3 levende naboer
                        // Sett cellen til levende.
                        celleListen[row][col] = true;
                    }
                    else if (antallLevendeNaboer < 2 || antallLevendeNaboer > 3){
                        celleListen[row][col] = false;
                    }
                } 
                else {
                    if (antallLevendeNaboer == 3) {
                        celleListen[row][col] = true;
                    }
                }

            } 
        }celler = celleListen;
                
    };

    
    private int tellLevendeNaboer(int row, int col) {
        int count = 0;

        // Iterer gjennom alle nabo-celler
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Sjekk at cellen ikke er utenfor rutenettet, og ikke er cellen selv
                if (i >= 0 && i < rad && j >= 0 && j < kol && !(i == row && j == col)) {
                    if (celler[i][j]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}

