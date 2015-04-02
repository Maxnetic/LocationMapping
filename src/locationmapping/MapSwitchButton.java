package locationmapping;

/**
 * Die Klasse MapSwitchButton erstellt einen Button zum Wechseln zwischen verschiedenen Kartenansichten.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */
public class MapSwitchButton extends Button {
    /**
     * Information ob angezeigtes Symbol plus ist (default = false)
     */
    String label;
    /**
     * Wahrheitswert ob alternative Karte aktiviert ist
     */
    boolean[] mapSwitchedState = {false, false};

    /**
     * Konstruktor fuer MapSwitchButton Objekte
     *
     * @param mapper Mapperobjekt
     * @param x X-Koordinate des Buttons
     * @param y Y-Koordinate des Buttons
     * @param w Breite des Buttons
     * @param h Hoehe des Buttons
     * @param label Text fuer Button
     */
    public MapSwitchButton(Mapper mapper, float x, float y, float w, float h, String label) {
        super(mapper, x, y, w, h);
        this.label = label;
    }

    /**
     * Zeichnet MapSwitchButton
     */
    void draw() {
        super.draw();
        this.mapper.app.fill(this.mapper.textColor);
        this.mapper.app.textFont(this.mapper.font, 12);
        this.mapper.app.text(this.label , x+5, y+12);
    }

    /**
     * Switched zwischen beiden Karten Provider Varianten
     */
    void mapSwitchHandler(){
        if ( !mapSwitchedState[0] ){
            if ( !mapSwitchedState[1] ){
                this.mapper.setStyle("light");
            } else {
                this.mapper.setStyle("dark");
                mapSwitchedState[0] = !mapSwitchedState[0];
            }
            mapSwitchedState[1] = !mapSwitchedState[1];
        } else {
            if ( !mapSwitchedState[1] ){
                this.mapper.setStyle("terrain");
            } else {
                this.mapper.setStyle("hybrid");
                mapSwitchedState[0] = !mapSwitchedState[0];
            }
            mapSwitchedState[1] = !mapSwitchedState[1];
        }
    }
}
