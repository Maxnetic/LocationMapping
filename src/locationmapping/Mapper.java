package locationmapping;

import processing.core.PApplet;
import processing.core.PFont;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


/**
 * Mapper-Oberklasse
 * Beinhaltet verschiedene Methoden um die Funktionsfaehigkeit
 * von unfolding maps zu erweitern.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public abstract class Mapper implements Const {
    /**
     * Zeitformate fuer Importer
     */
    public static final int UNIX = 0;
    public static final int ISO8601 = 1;
    public static final int EXPONENT_APPLE = 2;
    public static final int MDY_DATETIME = 3;

    /**
     * Einige Locations
     */
    public final Location BERLIN = new Location(52.5f, 13.4f);
    public final Location GERMANY = new Location(51.16f, 10.45f);
    public final Location HAMBURG = new Location(53.55f, 9.99f);
    public final Location MUENCHEN = new Location(48.14f, 11.57f);
    public final Location KOELN = new Location(50.94f, 6.95f);
    public final Location FRANKFURT = new Location(50.12f, 8.68f);
    public final Location STUTTGART = new Location(48.78f, 9.19f);


    /**
    * Spezielle Kartenfarbe
    */
    public final int POSITRON_RED = -2714732;


    /**
    * Farben fuer Buttons, Text und Hintergrund
    */
    public int textColor;
    public int highlightColor;
    public int backgroundColor;

    /**
     * Das Processing Applet in dem der Mapper laeuft
     */
    public PApplet app;
    /**
     * Die Unfolding Karte des Mappers
     */
    public UnfoldingMap map;
    /**
     * Die Uebersichtskarte des Mappers
     */
    public OverviewMap overviewMap;
    /**
     * Beschreibt ob Zeichnen pausiert ist oder nicht
     */
    public boolean paused = false;
    /**
     * der Provider für die Karte
     */
    AbstractMapProvider mapProvider = new MapProvider.Light();
    /**
     * Alternativer Karten Provider
     */
    AbstractMapProvider[] altMapProviders = {new MapProvider.Hybrid(), new MapProvider.Hybrid()};
    /**
     * Die Farbe fuer Markierungen auf der Karte
     */
    int mapColor = POSITRON_RED;
    /**
     * Die Schriftart fuer Texte auf der Karte
     */
    PFont font;
    /**
     * die Start Breite des Fensters
     */
    int width = 800;
    /**
     * Die Start Hoehe des Fensters
     */
    int height = 600;
    /**
     * Mittelpunkt für Initialisierung der Karte
     */
    Location startLocation = BERLIN;
    /**
     * Zoomlevel, mit der Karte initalisiert wird
     */
    int startZoomLevel = 10;
    /**
     * Der ZoomIn-Knopf
     */
    ZoomButton zoomIn;
    /**
     * Der ZomOut-Knopf
     */
    ZoomButton zoomOut;
    /**
     * Der Zoomslider
     */
    SliderButton slider;
    /**
     * Map Switch Button
     */
    AltMapButton mapSwitchButton;
    /**
    * Fenster groessenanpassbar
    */
    boolean resizable = false;

    /**
     * Setzt Fensterbreite
     *
     * @param width Fensterbreite
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * Setzt Fensterbreite
     *
     * @param height Fensterbreite
     */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * Setzt Groessenanpassbarkeit des Fensters
     *
     * @param resizable Gibt an ob Fenster groessenanpassbar sein soll oder nicht
     */
    public void setResizableAndDisableOverview(boolean resizable){
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
    * @param provider MapProvider
    */
    public void setMapProvider(AbstractMapProvider provider){
        mapProvider = provider;
    }
    /**
    * Setzt MapProvider
    *
    * @param provider MapProvider als String
    */
    public void setMapProvider(String provider){
        mapProvider = getMapProvider(provider);
    }

    /**
     * Setzt Alternative Karten Provider
     *
     * @param altProvider alternativer Provider für Karte
     * @param altOverviewProvider alternativer Provider für Übersichtskarte
     */
    public void setAltMapProvider(AbstractMapProvider altProvider, AbstractMapProvider altOverviewProvider){
        this.altMapProviders[0] = altProvider;
        this.altMapProviders[1] = altOverviewProvider;
    }
    /**
     * Setzt Alternative Karten Provider
     *
     * @param altProvider alternativer Provider für Karte als String
     * @param altOverviewProvider alternativer Provider für Übersichtskarte als String
     */
    public void setAltMapProvider(String altProvider, String altOverviewProvider){
        this.altMapProviders[0] = getMapProvider(altProvider);
        this.altMapProviders[1] = getMapProvider(altOverviewProvider);
    }
    /**
     * Setzt Alternative Karten Provider
     *
     * @param altProvider alternativer Provider für Karte als Stringals String
     */
    public void setAltMapProvider(String altProvider){
        this.altMapProviders[0] = getMapProvider(altProvider);
        this.altMapProviders[1] = getMapProvider(altProvider);
    }

    /**
    * Findet MapProvider Objekt für Eingabestring
    *
    * @param provider MapProvider als String
    * @return Map Provider Objekt
    * @throws RuntimeException falls provider String nicht geparsed werden kann
    */
    public AbstractMapProvider getMapProvider(String provider){
        AbstractMapProvider out = null;
        provider = provider.toLowerCase().trim();
        switch(provider){
            case "satelite":
                out = new MapProvider.Satelite();
                break;
            case "hybrid":
                out = new MapProvider.Hybrid();
                break;
            case "google":
                out = new MapProvider.GoogleMap();
                break;
            case "terrain":
                out = new MapProvider.GoogleTerrain();
                break;
            case "light":
            case "white":
            case "day":
                out = new MapProvider.Light();
                break;
            case "dark":
            case "black":
            case "night":
                out = new MapProvider.Dark();
                break;
            case "gray":
                out = new MapProvider.OSMGray();
                break;
            case "osm":
            case "open street map":
                out = new MapProvider.OSM();
                break;
        }
        if ( out != null )
            return out;
        else
            throw new RuntimeException("Map Provider not found, allowed values: 'light', 'dark', 'satelite', 'hybrid', 'google', 'osm', 'open street map' ");
    }
    /**
    * Setzt MapProvider
    *
    * @param provider Map Style als String
    * @throws RuntimeException falls provider String nicht geparsed werden kann
    */
    public void setStyle(String provider){
        this.setMapProvider(provider);
    }

    /**
     * Konstruktor für LocationMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper laeuft
     */
    public Mapper(PApplet app){
        this.app = app;
        this.font = this.app.loadFont("../../data/Courier.vlw");
    }

    /**
     * Initialisiert Fenster, Karte und Buttons, sollte als erstes in setup Methode des Processing Sketches aufgerufen werden
     *
     * @param w Fensterbreite
     * @param h Fensterhoehe
     */
    public void init(int w, int h){
        this.width = w;
        this.height = h;
        this.init();
    }
    /**
     * Initialisiert Fenster, Karte und Buttons, sollte als erstes in setup
     * Methode des Processing Sketches aufgerufen werden
     */
    public void init(){
        // Fenstergröße Setzen und Anpassbar machen
        if ( resizable ){
            this.app.size(this.width, this.height);
            this.app.frame.setResizable(true);
        } else {
            this.app.size(this.width, this.height, this.app.OPENGL);
        }

        // Setze Farbmodus auf HSB
        this.app.colorMode(app.HSB, 360, 100, 100, 100);

        // Setze Farben fuer Interface
        this.textColor       = this.app.color(0, 0, 50, 100);
        this.highlightColor  = this.app.color(0, 0, 90, 100);
        this.backgroundColor = this.app.color(0, 0, 95, 100);

        // Karte erstellen
        this.map = new UnfoldingMap(this.app, this.mapProvider);
        this.overviewMap = new OverviewMap(this, 270, 180, this.mapProvider, this.mapColor);

        // Ermoeglicht Zoom und Pan auf Karte
        MapUtils.createDefaultEventDispatcher(this.app, this.map);

        // Setze Startort uns Zoomlevel der Karte
        this.map.setZoomRange(5, 17);
        this.map.zoomAndPanTo(this.startZoomLevel, this.startLocation);
        this.overviewMap.zoomAndPanTo(this.startZoomLevel-5, this.startLocation);

        // Smoothes Scrollen und Zoomen auf Karte
        this.app.smooth();
        this.map.setTweening(true);

        // Zoom Buttons und Slider erstellen
        this.slider = new SliderButton(this, 32, 23, 188, 3, 13, 5);
        this.zoomIn = new ZoomButton(this, 219, 16, 16, 16, true);
        this.zoomOut = new ZoomButton(this, 16, 16, 16, 16, false);

        //
        this.mapSwitchButton = new AltMapButton(this, 16, 64, 92, 20, "SWITCH MAP");

        // Listener Einsetzen
        this.app.registerMethod("mouseEvent", this);
        this.app.registerMethod("keyEvent", this);
        this.app.registerMethod("draw", this);
    }

    /**
     * Zeichenmethode
     */
    public void draw(){
        // Zeichne Karte
        if ( resizable ){
            this.map.mapDisplay.resize(this.app.width, this.app.height);
            this.map.draw();
        } else {
            this.map.draw();
            this.overviewMap.draw();
        }


        // Zeichne Zoom Slider, ZoomIn-Knopf und ZoomOut-Knopf
        this.slider.draw();
        this.zoomIn.draw();
        this.zoomOut.draw();
        this.mapSwitchButton.draw();
    }

    /**
     * Importiert Daten aus angegbener Datei in eine Trackpointliste
     *
     * @param filename Name der zu importierenden Datei aus dem data Ordner
     * @return TrackpointList mit Datenpunkten aus Datei
     */
    public TrackpointList importData(String filename) {
        DataImporter importer = new DataImporter(this.app);
        return importer.load(filename, 0, UNIX);
    }
    /**
     * Importiert Daten aus angegbener Datei in eine Trackpointliste
     *
     * @param filename Name der zu importierenden Datei aus dem data Ordner
     * @return TrackpointList mit Datenpunkten aus Datei
     */
    public TrackpointList importData(String filename, int timeFormat) {
        DataImporter importer = new DataImporter(this.app);
        return importer.load(filename, 0, timeFormat);
    }

    /**
     * Importiert Daten aus angegbener Datei in eine Trackpointliste
     *
     * @param filename Name der zu importierenden Datei aus dem data Ordner
	 * @throws RuntimeException falls der Export fehlgeschlagen ist
     */
    public void exportData(TrackpointList trackpointList, String filename, int maxExportSize) {
        DataExporter exporter = new DataExporter(this.app);
        exporter.setMaxExportSize(maxExportSize);
        if ( exporter.write(trackpointList, filename) )
            System.out.println("Written CSV to " + filename);
        else
            throw new RuntimeException("export failed");
    }
    /**
     * Importiert Daten aus angegbener Datei in eine Trackpointliste
     *
     * @param filename Name der zu importierenden Datei aus dem data Ordner
     * @throws RuntimeException falls der Export fehlgeschlagen ist
	 */
    public void exportData(TrackpointList trackpointList, String filename) {
        DataExporter exporter = new DataExporter(this.app);
        if ( exporter.write(trackpointList, filename) )
            System.out.println("Written CSV to " + filename);
        else
            throw new RuntimeException("export failed");
    }

    public abstract void addMarker(Marker marker);

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
        if ( this.zoomIn.mouseOver(x, y) )
            this.map.zoomLevelIn();
        else if ( this.zoomOut.mouseOver(x, y) )
            this.map.zoomLevelOut();
        else if ( this.slider.mouseOver(x, y) ) {
            this.slider.zoomHandler(x);
        }
        else if ( this.overviewMap.mouseOver(x, y) ) {
            this.overviewMap.panToHandler(x, y);
        }
        else if ( this.mapSwitchButton.mouseOver(x, y) ) {
            this.mapSwitchButton.mapSwitchHandler();
        }

    }
    /**
    * Verwaltet Tastenaktionen
    *
    * @param e Tastenevent
    */
    public void keyEvent(KeyEvent e){
        if ( e.getKey() == 's' ) {
            this.mapSwitchButton.mapSwitchHandler();
        }
    }
}