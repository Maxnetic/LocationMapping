package locationmapping;

import java.lang.ClassLoader;
import java.io.InputStream;
import java.io.IOException;

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
    * Farben fuer Buttons, Text und Hintergrund
    */
    public int textColor;
    public int buttonColor1;
    public int buttonColor2;
    public int highlightColor;
    public int red;
    public int yellow;
    public int green;
    public int blue;

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
    AbstractMapProvider[] mapProviders = {new MapProvider.Light(), new MapProvider.Light()};
    /**
    * Alternativer Karten Provider
    */
    AbstractMapProvider[] altMapProviders = {new MapProvider.Hybrid(), new MapProvider.Dark()};
    /**
    * Die Schriftart fuer Texte auf der Karte
    */
    PFont font;
    /**
    * Die Schriftart fuer Icons auf der Karte
    */
    PFont iconFont;
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
    int startZoomLevel = 12;
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
    MapSwitchButton mapSwitchButton;
    /**
    * Fenster groessenanpassbar
    */
    boolean resizable = false;
    /**
    * in die Infobox zu schreibender String
    */
    String infoString = "";
    /**
    * Wahrheitswert, ob Kartenstyle geswitched werden muss
    */
    boolean styleSwitched = false;

    /**
    * Konstruktor für LocationMapper Objekte
    *
    * @param app Processing Applet, in dem Mapper laeuft
    */
    public Mapper(PApplet app){
        this.app = app;

        // Lade Schriftarten aus JAR
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            this.font = new PFont(classLoader.getResourceAsStream("Courier.vlw"));
            this.iconFont = new PFont(classLoader.getResourceAsStream("FontAwesome.vlw"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static PFont getFont(){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            return new PFont(classLoader.getResourceAsStream("Courier.vlw"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public static PFont getIconFont(){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            return new PFont(classLoader.getResourceAsStream("FontAwesome.vlw"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

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
    * legt den beim Start gezeigten Ort fest
    *
    * @param location Ortsobjekt, welches als Startort der Karte gesetzt wird
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setStartLocation(Location location){
        this.startLocation = location;
        return this;
    }
    /**
    * legt den beim Start gezeigten Ort fest
    *
    * @param latitude Breitengrad des Startorts
    * @param longitude Längengrad des Startorts
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setStartLocation(double latitude, double longitude){
        return this.setStartLocation(new Location(latitude, longitude));
    }
    /**
    * Setzt Startzoomstufe
    *
    * @param zoomLevel Startzoomstufe
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setStartZoomLevel(int zoomLevel){
        this.startZoomLevel = zoomLevel;
        return this;
    }


    /**
    * Setzt MapProvider vor Initmethode
    *
    * @param provider MapProvider für Hauptkarte
    * @param overviewProvider MapProvider für Übersichtskarte
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setMapProvider(AbstractMapProvider provider, AbstractMapProvider overviewProvider){
        this.mapProviders[0] = provider;
        this.mapProviders[1] = overviewProvider;
        return this;
    }
    /**
    * Setzt MapProvider vor Initmethode
    *
    * @param provider MapProvider für Karten
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setMapProvider(AbstractMapProvider provider){
        return this.setMapProvider(provider, provider);
    }
    /**
    * Setzt MapProvider vor Initmethode
    *
    * @param provider MapProvider für Hauptkarte als String
    * @param providerOverview MapProvider für Übersichtskarte als String
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setMapProvider(String provider, String providerOverview){
        return this.setMapProvider(getMapProvider(provider), getMapProvider(providerOverview));
    }
    /**
    * Setzt MapProvider vor Initmethode
    *
    * @param provider MapProvider als String
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setMapProvider(String provider){
        return this.setMapProvider(provider, provider);
    }


    /**
    * Setzt Alternative Karten Provider
    *
    * @param altProvider alternativer Provider für Karte
    * @param altOverviewProvider alternativer Provider für Übersichtskarte
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setAltMapProvider(AbstractMapProvider altProvider, AbstractMapProvider altOverviewProvider){
        this.altMapProviders[0] = altProvider;
        this.altMapProviders[1] = altOverviewProvider;
        return this;
    }
    /**
    * Setzt Alternative Karten Provider
    *
    * @param altProvider alternativer Provider für Karte
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setAltMapProvider(AbstractMapProvider altProvider){
        return this.setAltMapProvider(altProvider, altProvider);
    }
    /**
    * Setzt Alternative Karten Provider
    *
    * @param altProvider alternativer Provider für Karte als String
    * @param altOverviewProvider alternativer Provider für Übersichtskarte als String
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setAltMapProvider(String altProvider, String altOverviewProvider){
        return this.setAltMapProvider(this.getMapProvider(altProvider), this.getMapProvider(altOverviewProvider));
    }
    /**
    * Setzt Alternative Karten Provider
    *
    * @param altProvider alternativer Provider für Karte als Stringals String
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setAltMapProvider(String altProvider){
        return this.setAltMapProvider(this.getMapProvider(altProvider));
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
    * Setzt Map und UI Style
    *
    * @param style Map Style als String
    * @return Mapper Objekt für Method-Chaning
    */
    public Mapper setStyle(String style){
        this.styleSwitched = true;
        switch(style){
            case "light":
                this.setMapProvider(new MapProvider.Light());
                this.textColor = Const.LIGHT_TEXT_COLOR;
                this.buttonColor1 = Const.LIGHT_BUTTON_COLOR1;
                this.buttonColor2 = Const.LIGHT_BUTTON_COLOR2;
                this.highlightColor = Const.LIGHT_HIGHLIGHT_COLOR;
                break;
            case "dark":
                this.setMapProvider(new MapProvider.Dark());
                this.textColor = Const.DARK_TEXT_COLOR;
                this.buttonColor1 = Const.DARK_BUTTON_COLOR1;
                this.buttonColor2 = Const.DARK_BUTTON_COLOR2;
                this.highlightColor = Const.DARK_HIGHLIGHT_COLOR;
                this.red = Const.DARK_RED;
                this.yellow = Const.DARK_YELLOW;
                this.green = Const.DARK_GREEN;
                this.blue = Const.DARK_BLUE;
                break;
            case "hybrid":
            case "satelite":
                this.setMapProvider(new MapProvider.Hybrid(), new MapProvider.Dark());
                this.textColor = Const.DARK_TEXT_COLOR;
                this.buttonColor1 = Const.DARK_BUTTON_COLOR1;
                this.buttonColor2 = Const.DARK_BUTTON_COLOR2;
                this.highlightColor = Const.DARK_HIGHLIGHT_COLOR;
                this.red = Const.DARK_RED;
                break;
            case "terrain":
            default:
                this.setMapProvider(new MapProvider.GoogleTerrain());
                this.textColor = Const.DEFAULT_TEXT_COLOR;
                this.buttonColor1 = Const.DEFAULT_BUTTON_COLOR1;
                this.buttonColor2 = Const.DEFAULT_BUTTON_COLOR2;
                this.highlightColor = Const.DEFAULT_HIGHLIGHT_COLOR;
                this.red = Const.DEFAULT_RED;
                this.yellow = Const.DEFAULT_YELLOW;
                this.green = Const.DEFAULT_GREEN;
                this.blue = Const.DEFAULT_BLUE;
                break;
        }
        return this;
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

        // Karte erstellen
        this.map = new UnfoldingMap(this.app, this.mapProviders[0]);
        this.overviewMap = new OverviewMap(this, 270, 180, this.mapProviders[1]);

        // Setze Farben fuer Interface
        this.setStyle("light");

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
        this.mapSwitchButton = new MapSwitchButton(this, 251, 16, 84, 16, "SWITCH MAP");

        // Listener Einsetzen
        this.app.registerMethod("mouseEvent", this);
        this.app.registerMethod("keyEvent", this);
        this.app.registerMethod("draw", this);
        this.app.registerMethod("pre", this);
    }

    /**
    * Methode, die automatisch vor Zeichenmethode aufgerufen wird
    */
    public void pre(){
        if ( this.styleSwitched ){
            this.map.mapDisplay.setProvider(this.mapProviders[0]);
            this.overviewMap.mapDisplay.setProvider(this.mapProviders[1]);
            this.styleSwitched = false;
        }
        if ( resizable ){
            this.map.mapDisplay.resize(this.app.width, this.app.height);
        }
    }
    /**
    * Zeichenmethode
    */
    public void draw(){
        // Zeichne Karte
        this.map.draw();
        if ( !this.resizable )
            this.overviewMap.draw();


        // Zeichne Zoom Slider, ZoomIn-Knopf und ZoomOut-Knopf
        this.slider.draw();
        this.zoomIn.draw();
        this.zoomOut.draw();
        this.mapSwitchButton.draw();
    }

    /**
    * Zeichnet ein Feld mit Informationen ueber Ort und Zeit
    *
    * @param text zu setzender Informationstext
    */
    void drawInfoBox(String text){
        // Zeichne weisses Rechteck
        this.app.fill(this.buttonColor1);
        this.app.noStroke();
        this.app.rect(0, this.app.height-54, this.app.width, 54);
        // Zeichne Linie ueber Rechteck
        this.app.stroke(this.textColor);
        this.app.strokeWeight(1.5f);
        this.app.line(0, this.app.height-54, this.app.width, this.app.height-54);
        // Schreibe Urzeit in Rechteck
        this.app.fill(this.textColor);
        this.app.textFont(this.font, 16);
        this.app.text(text , 32, this.app.height-20);
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
    * @param timeFormat spezifizierung des Datumsformats, wähle eine Constante: UNIX, ISO8601, EXPONENT_APPLE oder MDY_DATETIME
    * @return TrackpointList mit Datenpunkten aus Datei
    */
    public TrackpointList importData(String filename, int timeFormat) {
        DataImporter importer = new DataImporter(this.app);
        return importer.load(filename, 0, timeFormat);
    }

    /**
    * Exportiert Daten aus angegbener Trackpointliste in eine CSV Datei
    *
    * @param trackpointList Liste mit zu exportierenden Trackpoint
    * @param filename Name der zu exportierenden Datei, .csv wird automatisch hinzugefügt
    * @param maxExportSize maximale Anzahl von Zeilen der Export Datei
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
    * Exportiert Daten aus angegbener Trackpointliste in eine CSV Datei
    *
    * @param trackpointList Liste mit zu exportierenden Trackpoint
    * @param filename Name der zu exportierenden Datei, .csv wird automatisch hinzugefügt
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
            case MouseEvent.MOVE:
                this.moveEventHandler(x, y);
                break;
        }
    }
    /**
    * Verwaltet Mausklickinteractionen
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
    * Verwaltet Mausbewegungsinteraktionen
    *
    * @param x X-Koordinate der Maus
    * @param y Y-Koordinate der Maus
    */
    void moveEventHandler(int x, int y) {
        // Marker hitMarker = this.map.getFirstHitMarker(x,y);
        // if (hitMarker != null) {
            // this.infoString = hitMarker.getId();
        // }
    }
    /**
    * Verwaltet Tastenaktionen
    *
    * @param e Tastenevent
    */
    public void keyEvent(KeyEvent e){
        switch( e.getKey() ){
            case '1':
                this.setStyle("light");
                break;
            case '2':
                this.setStyle("dark");
                break;
            case '3':
                this.setStyle("terrain");
                break;
            case '4':
                this.setStyle("satelite");
                break;
        }
    }
}