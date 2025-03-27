public class Hovedprogram {
    public static void main(String[] args) {
        Kontroll kontroll = new Kontroll(); //lager kontroll objekt
        kontroll.faaGameOfLifeView().setVisible(true); //gjør gui synlig
    }
}

