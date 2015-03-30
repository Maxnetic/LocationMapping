package locationmapping;

import java.util.*;

import org.joda.time.DateTime;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.Location;

/**
 * LifeMapper erweitert Mapper.
 * Erzeugt ein Mapper-Objekt das nur den aktuellen Marker anzeigt.
 * Dadurch lassen sich Bewegungsmuster besser erkennen.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class FireflyMapper extends Mapper implements Const{
    /**
    * Geschwindigkeit mit der gezeichnet wird
    */
    int secondsPerFrame = 60;
    /**
     * Der Start/Pause-Knopf
     */
    PlayButton play;
    /**
     * Der aktuelle Zeitpunkt, der gezeichnet wird
     */
    DateTime time;
    /**
     * TrackpointList fuer die Marker gezeichnet wird
     */
    TrackpointList trackpointList;
    /**
     * Iterator ueber die Liste
     */
    Iterator<Trackpoint> iter;

    /**
     * Konstruktor für DynamicMapper Objekte
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
        this.time = this.trackpointList.getFirst().getTime();
    }

    /**
    * Zeichenmethode
    */
    public void draw(){
        super.draw();
        this.play.draw();
        this.drawInfoBox(this.time.toString("EE, HH:mm:ss, MMM d, YYYY"));

        if ( !this.paused ){
            Trackpoint trackpoint = this.iter.next();
            while ( trackpoint.compareTimeTo(this.time) < 0 ){

                SimplePointMarker marker = new SimplePointMarker(trackpoint.getLocation());
                marker.setColor(this.LIGHT_RED);
                marker.setStrokeWeight(0);
                marker.setRadius(4);
                this.map.addMarker(marker);

                trackpoint = this.iter.next();
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

    /**
     * Zeichnet ein Feld mit Informationen ueber Ort und Zeit
     *
     * @param text zu setzender Informationstext
     */
    void drawInfoBox(String text){
        // Zeichne weisses Rechteck
        this.app.fill(this.backgroundColor);
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
}