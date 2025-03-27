public class Kontroll {
    private Modell modell;
    private GameOfLifeGUI view;

    public Kontroll() {
        modell = new Modell(8, 12);
        view = new GameOfLifeGUI(modell, this); //når programmet kjører vil denne kalle gui klassen og programmet begynner
    }

    public GameOfLifeGUI faaGameOfLifeView() {
        return view;
    }

    public void veksleCelle(int row, int col) {
        modell.settCelleLevende(row, col, !modell.celleTilstand(row, col));
    }

    public void faaRandomCells() {
        modell.fyllMedTilfeldigeCeller();
        view.HentBrett().oppdaterCeller();
    }

    public void nullstill() {
        modell.nullstill();
        view.HentBrett().oppdaterCeller();
    }

    public void nesteGenerasjon() {
        modell.oppdater();
        view.HentBrett().oppdaterCeller();
    }
}


