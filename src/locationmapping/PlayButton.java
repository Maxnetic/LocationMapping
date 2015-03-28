package locationmapping;

public class PlayButton extends Button {
    /**
     * Durchmesser des Buttons
     */
    float d;

    /**
     * Konstruktor fuer PlayButton Objekte
     *
     * @param mapper Mapperobjekt
     * @param d Durchmesser des Buttons
     * @return neues Objekt vom Typ PlayButton
     */
    public PlayButton(Mapper mapper, float d) {
        super(mapper, d, d, d, d);
        this.d = d;
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
        return (xM > x-d/2 && xM < x+d/2 && yM > y-d/2 && yM < y+d/2 );
    }

    /**
     * Zeichnet Button, Mapper verwaltet Pausetaste
     */
    void draw() {
        mapper.app.strokeWeight(1.5f);
        this.x = mapper.app.width/2f;
        this.y = mapper.app.height - this.d/2f - 64;

        mapper.app.fill( mouseOver(mapper.app.mouseX, mapper.app.mouseY) ? mapper.highlightColor : mapper.backgroundColor);
        mapper.app.stroke(mapper.textColor);
        mapper.app.ellipse(x, y, d, d);

        // Je nach Pausezustand wird Play- oder Stop-Symbol gezeichnet
        mapper.app.noStroke();
        mapper.app.fill(mapper.textColor);
        if(mapper.paused == false) {
            mapper.app.rect(x-d/7-d/14, y-d/4, d/7, d/2);
            mapper.app.rect(x+d/7-d/14, y-d/4, d/7, d/2);
        } else {
            mapper.app.triangle(x-d/7, y-d/4, x-d/7, y+d/4, x+d/4, y);
        }
    }


}

