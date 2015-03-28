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
     * @param zoomlevel Anzahl der Zoomlevel der Karte
     * @param startZoomlevel kleinstes Zoomlevel der Karte
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
        // Line
        mapper.app.noStroke();
        mapper.app.fill(mapper.textColor);
        mapper.app.rect(x, y, w, h);

        // Button
        mapper.app.strokeWeight(1.5f);
        mapper.app.stroke(mapper.textColor);
        mapper.app.fill(mouseOver(mapper.app.mouseX, mapper.app.mouseY) ? mapper.highlightColor : mapper.backgroundColor);
        mapper.app.rect(x + ((w+6f) * ((mapper.map.getZoomLevel()-this.startZoomLevel) / this.zoomLevel) ), 16, 8, 16);
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
     * @param xM X-Koordinate der Maus
     */
    void zoomHandler(int xM) {
        int clickedZoom = (int) ( (xM-x)/(w-8f) * this.zoomLevel ) + this.startZoomLevel;
        mapper.map.zoomToLevel( clickedZoom );
    }
}

