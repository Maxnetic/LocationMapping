package locationmapping;

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
     * @param plusSymbol 
     * @return neues Objekt vom Typ ZoomButton
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
        app.stroke(0);
        app.line(x+3,y+h/2,x+w-3,y+h/2);
        if (plusSymbol) {
            app.line(x+w/2,y+3,x+w/2,y+h-3);
        }
    }

}
