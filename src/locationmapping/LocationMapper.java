package locationmapping;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;

public class LocationMapper {
    /**
     * Einige Locations
     */
    public final Location BERLIN = new Location(52.5f, 13.4f);


    /**
     * Das Processing Applet in dem der Mapper läuft
     */
    PApplet app;
    /**
     * Die Unfolding Karte des Mappers
     */
    UnfoldingMap map;
    /**
     * Beschreibt ob Zeichnen pausiert ist oder nicht
     */
    public boolean paused = true;
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
     * Der Start/Pause-Knopf
     */
    PlayButton play;
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
     * Konstruktor für LocationMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper läuft
     */
    public LocationMapper(PApplet app){
        this.app = app;
    }

    /**
     * Initialisiert Fenster, Karte und Buttons, sollte als erstes in setup
     * Methode des Processing Sketches aufgerufen werden
     */
    public void init(){
        // Fenstergröße Setzen und Anpassbar machen
        app.size(this.width, this.height);
        //app.frame.setResizable(true); //funktioniert unter eclipse nicht

        // Karte erstellen
        this.map = new UnfoldingMap(app);

        // Ermoeglicht Zoom und Pan auf Karte
        MapUtils.createDefaultEventDispatcher(this.app, this.map);

        // Smoothes Scrollen und Zoomen auf Karte
        app.smooth();
        map.setTweening(true);

        // Setze Startort uns Zoomlevel der Karte
        map.zoomAndPanTo(this.startLocation, this.startZoomLevel);

        // Zoom Buttons und Slider erstellen
        this.slider = new SliderButton(this, 150, 3, this.width, this.height);
        this.zoomIn = new ZoomButton(this, 175, 14, 15, 15, true);
        this.zoomOut = new ZoomButton(this, 0, 14, 15, 15, false);

        // Play Button erstellen
        this.play = new PlayButton(this, width, height);
    }

    /**
     *
     */
    public void update(){
        // Zeichne Karte
        this.map.draw();

        // Zeichne Zoom Slider, ZoomIn-Knopf, ZoomOut-Knopf und Pause=Knopf
        this.slider.draw();
        this.zoomIn.draw();
        this.zoomOut.draw();
        this.play.draw();
    }
}
