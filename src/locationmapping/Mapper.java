package locationmapping;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

public abstract class Mapper {
    /**
     * Einige Locations
     */
    public final Location BERLIN = new Location(52.5f, 13.4f);
    public final Location DEUTSCHLAND = new Location(51.16f, 10.45f);
    public final Location HAMBURG = new Location(53.55f, 9.99f);
    public final Location MUENCHEN = new Location(48.14f, 11.57f);
    public final Location KOELN = new Location(50.94f, 6.95f);
    public final Location FRANKFURT = new Location(50.12f, 8.68f);
    public final Location STUTTGART = new Location(48.78f, 9.19f);


    /**
     * Das Processing Applet in dem der Mapper läuft
     */
    public PApplet app;
    /**
     * Die Unfolding Karte des Mappers
     */
    public UnfoldingMap map;
    /**
     * Beschreibt ob Zeichnen pausiert ist oder nicht
     */
    public boolean paused = false;
    /**
     * der Provider für die Karte
     */
    private AbstractMapProvider mapProvider = new Google.GoogleTerrainProvider();
    /**
     * die Start Breite des Fensters
     */
    private int width = 800;
    /**
     * Die Start Höhe des Fensters
     */
    private int height = 600;
    /**
     * Mittelpunkt für Initialisierung der Karte
     */
    private Location startLocation = BERLIN;
    /**
     * Zoomlevel, mit der Karte initalisiert wird
     */
    private int startZoomLevel = 10;
    /**
     * Der ZoomIn-Knopf
     */
    private ZoomButton zoomIn;
    /**
     * Der ZomOut-Knopf
     */
    private ZoomButton zoomOut;
    /**
     * Der Zoomslider
     */
    private SliderButton slider;
    /**
    * Fenster groessenanpassbar
    */
    boolean resizable = true;

    /**
     * Setzt Groessenanpassbarkeit des Fensters
     *
     * @param resizable Gibt an ob Fenster groessenanpassbar sein soll oder nicht
     */
    public void setResizable(boolean resizable){
        this.resizable = resizable;
    }

    /**
     * gibt Startzoomstufe zurück
     */
    public int getZoomLevel(){
        return startZoomLevel;
    }

     /**
     * legt die Zoomstufe zum Programmstart fest
     */
    public void setZoomLevel(int newZoom){
        startZoomLevel = newZoom;
    }

     /**
     * gibt den beim Start gezeigten Ort an
     */
    public Location getStartLocation(){
        return startLocation;
    }

    /**
     * legt den beim Start gezeigten Ort fest
     */
    public Mapper setStartLocation(Location location){
        this.startLocation = location;
        return this;
    }
    /**
     * legt den beim Start gezeigten Ort fest
     */
    public Mapper setStartLocation(double latitude, double longitude){
        return this.setStartLocation(new Location(latitude, longitude));
    }
	/**
	* Setzt Startzoomstufe 
	*
	* @param zoomLevel Startzoomstufe
	*/
    public Mapper setStartZoomLevel(int zoomLevel){
        this.startZoomLevel = zoomLevel;
        return this;
    }
	/**
	* Setzt MapProvider
	*
	* @param provider MapProvider als String
	*/
    public void setMapProvider(String provider){
        if (provider == "Microsoft"){
            mapProvider = new Microsoft.RoadProvider();
        }
        if (provider == "Yahoo"){
            mapProvider = new Yahoo.RoadProvider();
        }
        if (provider == "Open Street Map"){
            mapProvider = new OpenStreetMap.OpenStreetMapProvider();
        }
        if (provider == "Google"){
            mapProvider = new Google.GoogleMapProvider();
        }
    }
	/**
	* Setzt MapProvider
	*
	* @param provider MapProvider 
	*/
    public void setMapProvider(AbstractMapProvider provider){
        mapProvider = provider;
    }

    /**
     * Konstruktor für LocationMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper laeuft
     */
    public Mapper(PApplet app){
        this.app = app;
    }

    /**
     * Initialisiert Fenster, Karte und Buttons, sollte als erstes in setup
     * Methode des Processing Sketches aufgerufen werden
     */
    @SuppressWarnings("deprecation")
    public void init(){
        // Fenstergröße Setzen und Anpassbar machen
        this.app.size(this.width, this.height);
        if ( resizable )
            this.app.frame.setResizable(true);

        // Karte erstellen
        this.map = new UnfoldingMap(this.app, this.mapProvider);

        // Ermoeglicht Zoom und Pan auf Karte
        MapUtils.createDefaultEventDispatcher(this.app, this.map);

        // Setze Startort uns Zoomlevel der Karte
        this.map.setZoomRange(4, 16);
        this.map.zoomAndPanTo(this.startLocation, this.startZoomLevel);

        // Smoothes Scrollen und Zoomen auf Karte
        this.app.smooth();
        this.map.setTweening(true);

        // Zoom Buttons und Slider erstellen
        this.slider = new SliderButton(this, 32, 22, 184, 4, 13, 4);
        this.zoomIn = new ZoomButton(this, 202, 16, 16, 16, true);
        this.zoomOut = new ZoomButton(this, 16, 16, 16, 16, false);

        // Listener Einsetzen
        this.app.registerMethod("mouseEvent", this);
        this.app.registerMethod("keyEvent", this);
        this.app.registerMethod("draw", this);
    }

    /**
     * Importiert Daten aus angegbener Datei in eine Trackpointliste
     *
     * @param filename Name der zu importierenden Datei aus dem data Ordner
     * @return TrackpointList mit Datenpunkten aus Datei
     */
    public TrackpointList importData(String filename) {
        DataImporter importer = new DataImporter(this.app);
        return importer.load(filename);
    }
	
    public abstract void addMarker(Marker marker);

    /**
     * Zeichenmethode 
     */
    public void draw(){
        // Zeichne Karte
        this.map.draw();

        // Zeichne Zoom Slider, ZoomIn-Knopf, ZoomOut-Knopf und Pause=Knopf
        this.slider.draw();
        this.zoomIn.draw();
        this.zoomOut.draw();
    }
	/**
	* Verwaltet Mausaktionen
	*
	* @param e Mausaktion
	*/
    public void mouseEvent(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        switch ( e.getAction() ){
            case MouseEvent.CLICK:
                this.clickEventHandler(x, y);
                break;
        }
    }
	/**
	* Verwaltet Mausklicks
	*
	* @param x X-Koordinate der Maus
	* @param y Y-Koordinate der Maus
	*/
    void clickEventHandler(int x, int y) {
        if ( zoomIn.mouseOver(x, y) )
            map.zoomLevelIn();
        if ( zoomOut.mouseOver(x, y) )
            map.zoomLevelOut();
        if ( slider.mouseOver(x, y) ) {
            slider.zoomHandler(x);
        }
    }
	/**
	* Verwaltet Tastenaktionen
	*
	* @param e Tastenevent
	*/
    public void keyEvent(KeyEvent e){

    }


}
