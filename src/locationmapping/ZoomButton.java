package locationmapping;

/**
 * Die Klasse ZoomButton erstellt zwei Buttons zum Herein- bzw. Herauszoomen aus der Karte.
 * ZoomButton erweitert Button.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class ZoomButton extends Button {
    /**
     * Information ob angezeigtes Symbol plus ist (default = false)
     */
    boolean plusSymbol = false;

    /**
     * Konstruktor fuer ZoomButton Objekte
     *
     * @param mapper Mapperobjekt
     * @param x X-Koordinate des Buttons
     * @param y Y-Koordinate des Buttons
     * @param w Breite des Buttons
     * @param h Hoehe des Buttons
     * @param plusSymbol Wahrheitswert ob Symbol auf dem Button ein Plus ist
     */
    public ZoomButton(Mapper mapper, float x, float y, float w, float h, boolean plusSymbol) {
        super(mapper, x, y, w, h);
        this.plusSymbol = plusSymbol;
    }

    /**
     * Zeichnet ZoomButton
     */
    void draw() {
        super.draw();
        mapper.app.strokeWeight(3);
        mapper.app.line(x+4,y+h/2,x+w-4,y+h/2);
        if (plusSymbol) {
            mapper.app.line(x+w/2,y+4,x+w/2,y+h-4);
        }
    }
}