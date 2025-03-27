import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLifeGUI extends JFrame {
    private Board brett;
    private ControlPanel controlPanel;

    public GameOfLifeGUI(Modell modell, Kontroll kontroll) { // i npr hovedprogrammet kalles blir denne kalt ved hjelp av objektet til kontroll
        setTitle("Game of Life (shahiaf)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 500); //setter størrelse
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        Komponenter(modell, kontroll); //kaller på metoden som skal hente knappene start, nullstill og avslutt og spillbrettet 
        }

    private void Komponenter(Modell modell, Kontroll kontroll) {//når denne metoden blir kalt, lager vi controlpanel og brett objekt som vi kaller på og legger tilsynlighet
        //her kunne vi likså godt ikke hatt med metoden og bare kjørt det direkte til klassene
        try { 
            UIManager.setLookAndFeel(
             UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { 
            System.exit(1); 
        }

        controlPanel = new ControlPanel(kontroll); //kaller på klassen kontrollpanel med objektet for å starte 
        add(controlPanel, BorderLayout.NORTH);
        
        brett = new Board(modell, kontroll);//kaller på klassen med objektet
        add(brett, BorderLayout.CENTER);

        
    }

    public Board HentBrett() {
        return brett;
    }

    public ControlPanel HentControlPanel() {
        return controlPanel;
    }
}
//Klassen under skriver ut spillbrettet: 
class Board extends JPanel {
    private Modell modell;
    private Kontroll kontroll;
    private Celle[][] celler;

    public Board(Modell modell, Kontroll kontroll) {
        this.modell = modell;
        this.kontroll = kontroll;
        celler = new Celle[modell.hentRad()][modell.hentKol()];
        setLayout(new GridLayout(modell.hentRad(), modell.hentKol()));
        lagCeller();
//todimensjonal array blir opprettet, layouten til brettet blir satt til en gridlayout med ant rad og kol som tilsvarer verdier hentet fra modell, deretter lager vi cellene
    }

    private void lagCeller() {
        for (int row = 0; row < modell.hentRad(); row++) {
            for (int col = 0; col < modell.hentKol(); col++) {
                celler[row][col] = new Celle(row, col, modell.celleTilstand(row, col), kontroll);
                add(celler[row][col]); 
            }
        }
    }

    public void oppdaterCeller() {
        for (int row = 0; row < modell.hentRad(); row++) {
            for (int col = 0; col < modell.hentKol(); col++) {
                celler[row][col].settLevendeEllerDoed(modell.celleTilstand(row, col));
                
            }//går gjennom hver celle og oppdaterer dem
        }
    }
}

//klassen under skriver knappene/button 
class ControlPanel extends JPanel {
    private JButton startButton;
    private JButton clearButton;
    private Timer timer;
    private Kontroll  kontroll;
    private JButton exitButton;

    public ControlPanel(Kontroll kontroll) {
        this.kontroll= kontroll;
        KjorKnappOgTekst(); //starter metoden under
    }

    private void KjorKnappOgTekst() {
        startButton = new JButton("Start");
        clearButton = new JButton("Nullstill");
        exitButton = new JButton("Avslutt");
       
        timer = new Timer(2000, e -> kontroll.nesteGenerasjon());
    
        startButton.addActionListener(e -> { //istedet for å lage en ny klasse for knappene som implementerer ActionListener, skriver jeg det rett under, hva knappene skal gjøre
            kontroll.faaRandomCells();
            timer.start();
        });
        add(startButton);//knapp for start

        //startButton.addActionListener(new Startbehandler());;

        clearButton.addActionListener(e -> {
            timer.stop();
            kontroll.nullstill();
        });
        add(clearButton);//knapp for nullstill

        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);//knapp for å avslutte 
    }
}
