import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Celle extends JPanel {
    protected int rad;
    protected int kol;
    private boolean ilivet;
    private Kontroll kontroll;

    public Celle(int rad, int kol, boolean ilivet, Kontroll kontroll) {
        this.rad = kol;
        this.rad = kol;
        this.ilivet = ilivet;
        this.kontroll = kontroll;
//tar inn parameterne

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(ilivet ? Color.BLUE : Color.WHITE);// if setning om hvis den er ilivet = blå, hvis den ikke er levende = hvit
        
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                kontroll.veksleCelle(rad, kol);
                switchLevendeDoed();


            }
        });
    };
    

    public boolean isAlive() {
        return ilivet;
    }

    public void settLevendeEllerDoed(boolean ilivet) {
        this.ilivet = ilivet;
        if (ilivet) {
            setBackground(Color.BLUE);
        } else {
            setBackground(Color.WHITE);
        }
    }

    public void switchLevendeDoed() {
        settLevendeEllerDoed(!ilivet);
    }


}