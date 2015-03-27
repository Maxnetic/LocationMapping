package locationmapping;

public class PlayButton extends Button {
	/**
	 * Mapperobjekt zum Verwalten des Buttons
	 */
    Mapper mapper;
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
        this.mapper = mapper;
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
        this.x = app.width/2f;
        this.y = app.height - this.d/2f - 32;

        app.fill( mouseOver(app.mouseX, app.mouseY) ? 255 : 220);
        app.stroke(150);
        app.ellipse(x, y, d, d);

        // Je nach Pausezustand wird Play- oder Stop-Symbol gezeichnet
        app.noStroke();
        app.fill(120);
        if(mapper.paused == false) {
            app.rect(x-d/7-d/14, y-d/4, d/7, d/2);
            app.rect(x+d/7-d/14, y-d/4, d/7, d/2);
        } else {
            app.triangle(x-d/7, y-d/4, x-d/7, y+d/4, x+d/4, y);
        }
    }


}

