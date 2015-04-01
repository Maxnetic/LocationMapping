package locationmapping;

import java.util.*;

import org.joda.time.DateTime;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.Location;

/**
 * FireflyMapper erweitert Mapper.
 * Erzeugt ein Mapper-Objekt das die Visualisierung aehnlich der
 * Firefly-Animation ermoeglicht.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class FireflyMapper extends Mapper{
    /**
    * Geschwindigkeit mit der gezeichnet wird
    */
    int secondsPerFrame = 10;
    /**
    * Zeit, für die Marker auf Karten bleiben
    */
    int fadeOutTime;
    /**
     * Der Start/Pause-Knopf
     */
    PlayButton play;
    /**
     * Der aktuelle Zeitpunkt, der gezeichnet wird
     */
    DateTime time;

    Trackpoint nextTrackpoint;
    /**
     * TrackpointList fuer die Marker gezeichnet wird
     */
    TrackpointList trackpointList;
    /**
     * Iterator ueber die Liste
     */
    Iterator<Trackpoint> iter;

    /**
     * Konstruktor für FireflyMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper läuft
     */
    public FireflyMapper(PApplet app){
        super(app);
    }

    /**
    * Fügt Marker hinzu
    *
    * @param marker Marker der hinzugefuegt werden soll
    */
    public void addMarker(Marker marker) {
        map.addMarker(marker);
    }

    /**
     * Initialisiert Fenster, Karte und Buttons, sollte als erstes in setup
     * Methode des Processing Sketches aufgerufen werden
     */
    public void init(){
        // Setze Startkarte auf Deutschland
        this.setStartLocation(Const.GERMANY);
        this.setStartZoomLevel(6);

        super.init();
        this.setStyle("dark");
        this.fadeOutTime = 600/this.secondsPerFrame;

        // Play Button erstellen
        this.play = new PlayButton(this, 41);
    }
    /**
    * Initmethode
    * @param trackpointList TrackpointList die initialisiert wird
    */
    public void load(TrackpointList trackpointList){
        // Setze Trackpointliste und initialisiere Iterator
        this.trackpointList = trackpointList;
        this.iter = this.trackpointList.iterator();

        // Setze Zeit und Ort auf Werte des ersten Trackpoint, berechne Geschwindigkeiten
        this.nextTrackpoint = trackpointList.getFirst();
        this.time = this.nextTrackpoint.getTime();
    }

    /**
    * Zeichenmethode
    */
    public void draw(){
        super.draw();

        // Zeichne Playbutton und Zeitinfo Fußzeile
        this.play.draw();
        this.drawInfoBox(this.time.toString("EE, HH:mm:ss, MMM d, YYYY"));

        // Timetolive aller Trackpoints auf der Karte updaten
        for ( Iterator<Marker> iter = this.map.mapDisplay.getDefaultMarkerManager().getMarkers().iterator(); iter.hasNext(); ){
            FireflyMarker marker = (FireflyMarker) iter.next();
            marker.setColor(this.app.color(this.app.hue(Const.DARK_YELLOW), this.app.saturation(Const.DARK_YELLOW), this.app.brightness(Const.DARK_YELLOW), 100f/this.fadeOutTime * marker.timeToLive));
            marker.timeToLive--;
            if ( marker.timeToLive <= 0 )
                iter.remove();
        }

        // Zeichne alle Trackpoints, im aktuellen Zeitabschnitt
        if ( !this.paused ){
            while ( this.nextTrackpoint.compareTimeTo(this.time) < 0 && this.iter.hasNext() ){
                // Lade nächsten Trackpoint
                this.nextTrackpoint = this.iter.next();

                // neuen Marker erstellen und zur Karte hinzufügen
                this.map.addMarker(new FireflyMarker(nextTrackpoint, this.fadeOutTime));
            }
            this.time = this.time.plusSeconds(this.secondsPerFrame);
        }
    }


    /**
    * Verwaltet Mausklicks
    *
    * @param x X-Koordinate der Maus
    * @param y Y-Koordinate der Maus
    */
    void clickEventHandler(int x, int y){
        super.clickEventHandler(x, y);
        if ( play.mouseOver(x, y) )
            paused = !paused;
    }
    /**
    * Verwaltet Tastenaktionen
    *
    * @param e Tastenevent
    */
    public void keyEvent(KeyEvent e){
        super.keyEvent(e);

        if ( e.getKey() == ' ' ) {
            paused = !paused;
        }
    }
}