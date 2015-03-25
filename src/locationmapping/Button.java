package locationmapping;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.*;

class Button {
    PApplet app;
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
     * Die Höhe des Buttons
     */
    float h;


    Button(LocationMapper mapper, float x, float y, float w, float h) {
        this.map = mapper.map;
        this.app = mapper.app;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * mouseOver ist true, wenn die Maus darüber gehalten wird.
     * Benötigt zum Erkennen von Klicks auf den Button
     *
     * @param xM x Position der Maus
     * @param yM y Position der Maus
     * @return Wahrheitswert ob Maus über Button ist
     */
    boolean mouseOver(int xM, int yM) {
        return (xM > x && xM < x + w && yM > y && yM < y + h);
    }

    void draw() {
        app.strokeWeight(1);
        app.stroke(80);
        app.fill(mouseOver(app.mouseX, app.mouseY) ? 255 : 220);
        app.rect(x, y, w, h);
    }

}