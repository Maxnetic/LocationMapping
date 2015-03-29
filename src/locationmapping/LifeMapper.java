package locationmapping;

import java.util.*;

import org.joda.time.DateTime;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.Location;

public class LifeMapper extends Mapper {
    /**
    * Geschwindigkeit mit der gezeichnet wird
    */
    int secondsPerFrame = 10;
    /**
     * Der Start/Pause-Knopf
     */
    PlayButton play;
    /**
     * Der aktuelle Zeitpunkt, der gezeichnet wird
     */
    DateTime time;
    Location location;

    SimplePointMarker marker;

    Trackpoint currTrackpoint;
    Trackpoint nextTrackpoint;

    float[] currSpeeds = new float[2];

    TrackpointList trackpointList;

    Iterator<Trackpoint> iter;


    /**
    * Fuegt Marker hinzu
    * @param marker Marker der hinzugefuegt werden soll
    */
    public void addMarker(Marker marker) {
        map.addMarker(marker);
    }


    /**
     * Konstruktor für DynamicMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper läuft
     */
    public LifeMapper(PApplet app){
        super(app);
    }

    /**
    * Initmethode
    */
    public void load(TrackpointList trackpointList){
        // Play Button erstellen
        this.play = new PlayButton(this, 41);

        // Setze Trackpointliste und initialisiere Iterator
        this.trackpointList = trackpointList;
        this.iter = this.trackpointList.iterator();
        this.currTrackpoint = this.iter.next();
        this.nextTrackpoint = this.iter.next();

        // Setze Zeit und Ort auf Werte des ersten Trackpoint, berechne Geschwindigkeiten
        this.time = this.currTrackpoint.getTime();
        this.location = this.currTrackpoint.getLocation();
        this.updateSpeeds();

        // Initialisiere Marker
        this.marker = new SimplePointMarker(location);
        // HashMap<String, Object> properties = new HashMap<String, Object>();
        // properties.put("time", trackpoint.getTime());
        // properties.put("service", trackpoint.getService());
        // marker.setProperties(properties);
        this.marker.setColor(this.app.color(359, 31, 84, 30));
        this.marker.setStrokeColor(this.app.color(359, 31, 84, 100));
        this.marker.setStrokeWeight(3);
        this.marker.setRadius(30);
        this.map.addMarker(marker);
    }

    /**
    * Zeichenmethode
    */
    public void draw(){
        super.draw();
        this.play.draw();
        this.drawInfoBox(this.time.toString("EE, HH:mm:ss, MMM d, YYYY"));

        if ( !this.paused && this.iter.hasNext() ){
            this.time = this.time.plusSeconds(this.secondsPerFrame);

            if ( this.nextTrackpoint.compareTimeTo(this.time) < 0 ){
                this.currTrackpoint = this.nextTrackpoint;
                this.nextTrackpoint = this.iter.next();
                this.updateSpeeds();
                int timeExtra = (int)(this.currTrackpoint.getSeconds() - this.time.getMillis()/1000L);
                this.location.setLon(this.currTrackpoint.getLongitude() +  timeExtra * this.currSpeeds[0]);
                this.location.setLat(this.currTrackpoint.getLatitude() +  timeExtra * this.currSpeeds[1]);
                // this.location.setLon(this.currTrackpoint.getLongitude());
                // this.location.setLat(this.currTrackpoint.getLatitude());
            } else {
                this.location.setLon(this.location.getLon() +  this.secondsPerFrame * this.currSpeeds[0]);
                this.location.setLat(this.location.getLat() +  this.secondsPerFrame * this.currSpeeds[1]);
            }

            this.marker.setLocation(location);

            if ( marker.getDistanceTo(this.map.getCenter()) > 4f/(Math.pow(this.map.getZoomLevel(),2))*100 )
                this.map.panTo(marker.getLocation());
        }
    }

    void updateSpeeds(){
        float lonDist = this.currTrackpoint.getLongitude() - this.nextTrackpoint.getLongitude();
        float latDist = this.currTrackpoint.getLatitude() - this.nextTrackpoint.getLatitude();
        long timeDist =  this.currTrackpoint.getSeconds() - this.nextTrackpoint.getSeconds();

        this.currSpeeds[0] = lonDist/timeDist;
        this.currSpeeds[1] = latDist/timeDist;
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
        this.app.textFont(font, 16);
        // this.app.textSize(16);
        this.app.text(text , 32, this.app.height-20);
    }
}