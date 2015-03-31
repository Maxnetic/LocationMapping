package locationmapping;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.*;

/**
 * Die Klasse Button ist dafür zustaendig alle Schaltflaechen die
 * interaktiv ueber die Maus genutzt werden wollen auf die genutzte Karte zu zeichnen.
 * Dabei handelt es sich beispielweise um Start- und Stopbuttons oder Schaltflaechen zum
 * herein- und herauszoomen aus der Karte.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

class Button {
    /**
     * Mapperobjekt zum Verwalten des Buttons
     */
    Mapper mapper;
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
     */
    Button(Mapper mapper, float x, float y, float w, float h) {
        this.mapper = mapper;
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
        mapper.app.strokeWeight(1.5f);
        mapper.app.stroke(mapper.textColor);
        mapper.app.fill(mouseOver(mapper.app.mouseX, mapper.app.mouseY) ? mapper.buttonColor2 : mapper.buttonColor1);
        mapper.app.rect(x, y, w, h);
    }

}