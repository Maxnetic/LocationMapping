package locationmapping;


public class SliderButton extends Button {
	/**
	 * Zoomlevel
	 */
    float zoomLevel;
    /**
     * Startwert fuer Zoomlevel
     */
    int startZoomLevel;

    /**
     * Konstruktor fuer SliderButton Objekte
     * 
     * @param mapper Mapperobjekt
     * @param x X-Koordinate des Buttons
     * @param y Y-Koordiante des Buttons
     * @param w Breite des Buttons
     * @param h Hoehe des Buttons
     * @param zoomlevel Zoomlevel
     * @param startZoomlevel Startwert fuer Zoomlevel
     * @return neues Objekt vom Typ Button
     */
    public SliderButton(Mapper mapper, float x, float y, float w, float h, float zoomLevel, int startZoomLevel){
        super(mapper, x, y, w, h);
        this.zoomLevel = zoomLevel;
        this.startZoomLevel = startZoomLevel;
    }

    /**
     * Zeichnet den Schieberegler des Zooms
     */
    void draw(){
         super.draw();
         app.rect(x + ((w-4) * ((map.getZoomLevel()-this.startZoomLevel) / this.zoomLevel) ), 16, 4, 16);
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
        return (xM > x && xM < x + w && yM > y-6 && yM < y+h+6);
    }

    /**
     * Zoomt in Mausbereich
     * 
     * @param xM X-Koordinate von der Maus
     */
    void zoomHandler(int xM) {
        int clickedZoom = (int) ( (xM-x)/(w-4f) * this.zoomLevel ) + this.startZoomLevel;
        map.zoomToLevel( clickedZoom );
    }
}

