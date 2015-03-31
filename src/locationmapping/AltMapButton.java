package locationmapping;

/**
 * Die Klasse AltMapButton erstellt einen Button zum Wechseln zwischen verschiedenen Kartenansichten.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */
public class AltMapButton extends Button {
    /**
     * Information ob angezeigtes Symbol plus ist (default = false)
     */
    String label;
    /**
     * Wahrheitswert ob alternative Karte aktiviert ist
     */
    boolean mapSwitchedState = false;

    /**
     * Konstruktor fuer AltMapButton Objekte
     *
     * @param mapper Mapperobjekt
     * @param x X-Koordinate des Buttons
     * @param y Y-Koordinate des Buttons
     * @param w Breite des Buttons
     * @param h Hoehe des Buttons
     * @param label Text fuer Button
     */
    public AltMapButton(Mapper mapper, float x, float y, float w, float h, String label) {
        super(mapper, x, y, w, h);
        this.label = label;
    }

    /**
     * Zeichnet AltMapButton
     */
    void draw() {
        super.draw();
        this.mapper.app.fill(this.mapper.textColor);
        this.mapper.app.textFont(this.mapper.font, 12);
        this.mapper.app.text(this.label , x+8, y+14);
    }

    /**
     * Switched zwischen beiden Karten Provider Varianten
     */
    void mapSwitchHandler(){
        if ( !mapSwitchedState ){
            this.mapper.map.mapDisplay.setProvider(this.mapper.altMapProviders[0]);
            this.mapper.overviewMap.mapDisplay.setProvider(this.mapper.altMapProviders[1]);
        } else {
            this.mapper.map.mapDisplay.setProvider(this.mapper.mapProvider);
            this.mapper.overviewMap.mapDisplay.setProvider(this.mapper.mapProvider);
        }
        mapSwitchedState = !mapSwitchedState;
    }

}
