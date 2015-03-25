package locationmapping;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

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
     * der Provider für die Karte
     */
    AbstractMapProvider mapProvider = new Google.GoogleTerrainProvider();
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
    @SuppressWarnings("deprecation")
    public void init(){
        // Fenstergröße Setzen und Anpassbar machen
        app.size(this.width, this.height);
        app.frame.setResizable(true); //funktioniert unter eclipse nicht

        // Karte erstellen
        this.map = new UnfoldingMap(this.app, this.mapProvider);

        // Ermoeglicht Zoom und Pan auf Karte
        MapUtils.createDefaultEventDispatcher(this.app, this.map);

        // Smoothes Scrollen und Zoomen auf Karte
        app.smooth();
        map.setTweening(true);

        // Setze Startort uns Zoomlevel der Karte
        map.zoomAndPanTo(this.startLocation, this.startZoomLevel);
        map.setZoomRange(4, 16);

        // Zoom Buttons und Slider erstellen
        this.slider = new SliderButton(this, 32, 22, 184, 4, 13, 4);
        this.zoomIn = new ZoomButton(this, 202, 16, 16, 16, true);
        this.zoomOut = new ZoomButton(this, 16, 16, 16, 16, false);

        // Play Button erstellen
        this.play = new PlayButton(this, 41);

        // Listener Einsetzen
        this.app.registerMethod("mouseEvent", this);
        this.app.registerMethod("keyEvent", this);
        this.app.registerMethod("draw", this);

    }

    /**
     *
     */
    public void draw(){
        // Zeichne Karte
        this.map.draw();

        // Zeichne Zoom Slider, ZoomIn-Knopf, ZoomOut-Knopf und Pause=Knopf
        this.slider.draw();
        this.zoomIn.draw();
        this.zoomOut.draw();
        this.play.draw();

        // if(iter.hasNext()){
        //     if(frameCount % speed == 0 && pause == false){
        //         Trackpoint curr = (Trackpoint) iter.next();
        //         MyMarker tmp = new MyMarker(curr);
        //         System.out.println(curr.getDateTime());
        //         tmp.setStyle(curr.getService());
        //         map.addMarker(tmp);
        //         map.panTo(curr.getLocation());
        //         System.out.println(curr.getDateTime());
        //     }
        // }
    }

    public void mouseEvent(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        switch ( e.getAction() ){
            case MouseEvent.CLICK:
                if ( play.mouseOver(x, y) )
                    paused = !paused;
                if ( zoomIn.mouseOver(x, y) )
                    map.zoomLevelIn();
                if ( zoomOut.mouseOver(x, y) )
                    map.zoomLevelOut();
                // wenn auf den Slider gedrückt, zoome hinein oder hinaus
                if ( slider.mouseOver(x, y) ) {
                    slider.zoomHandler(x);
                }
                break;
        }
    }

    public void keyEvent(KeyEvent e){
        if ( e.getKey() == ' ' ) {
            paused = !paused;
        }
    }


}
