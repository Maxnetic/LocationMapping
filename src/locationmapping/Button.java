package locationmapping;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.*;

class Button {
	/**
	 * laufende Processing Applet Instanz
	 */
    PApplet app;
    /**
     * Karte, auf der der Button gezeichnet werden soll
     */
    UnfoldingMap map;
    /**
     * Die X-Koordinate des Buttons
     */
    float x;
    /**
     * Die Y-Koordinate des Buttons
     */
    float y;
    /**
     * Die Breite des Buttons
     */
    float w;
    /**
     * Die Hoehe des Buttons
     */
    float h;

    /**
     * Konstruktor fuer Button Objekte
     * 
     * @param mapper Mapperobjekt
     * @param x X-Koordinate des Buttons
     * @param y Y-Koordiante des Buttons
     * @param w Breite des Buttons
     * @param h Hoehe des Buttons
     * @return neues Objekt vom Typ Button
     */
    Button(Mapper mapper, float x, float y, float w, float h) {
        this.map = mapper.map;
        this.app = mapper.app;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * mouseOver ist true, wenn die Maus darueber gehalten wird.
     * Benoetigt zum Erkennen von Klicks auf den Button
     *
     * @param xM x Position der Maus
     * @param yM y Position der Maus
     * @return Wahrheitswert ob Maus ueber Button ist
     */
    boolean mouseOver(int xM, int yM) {
        return (xM > x && xM < x + w && yM > y && yM < y + h);
    }
    /**
     * Zeichnet Button
     */
    void draw() {
        app.strokeWeight(1);
        app.stroke(80);
        app.fill(mouseOver(app.mouseX, app.mouseY) ? 255 : 220);
        app.rect(x, y, w, h);
    }

}