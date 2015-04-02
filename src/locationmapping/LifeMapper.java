package locationmapping;

import java.util.HashMap;
import java.util.Iterator;

import org.joda.time.DateTime;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.geo.Location;

/**
 * Die Klasse LiveMapper erzeugt ein Mapper-Objekt, das immer nur den aktuellen Marker auf die Karte zeichnet.
 * LifeMapper erweitert Mapper.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

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
    /**
     * Der aktuelle Ort, der gezeichnet wird
     */
    Location location;
    /**
     * Der Marker, der gezeichnet wird
     */
    ServiceMarker marker;
    /**
     * Der aktuelle Trackpoint
     */
    Trackpoint currTrackpoint;
    /**
     * Der naechste Trackpoint
     */
    Trackpoint nextTrackpoint;
    /**
     * Speichert horizontale, vertikale und absolute Geschwindigkeit
     */
    float[] currSpeeds = new float[3];
    /**
     * TrackpointList fuer die Marker gezeichnet wird
     */
    TrackpointList trackpointList;
    /**
     * Iterator ueber die Liste
     */
    Iterator<Trackpoint> iter;


    /**
    * Fuegt Marker hinzu
	*
    * @param marker Marker der hinzugefuegt werden soll
    */
    public void addMarker(Marker marker) {
        // Schriftart für Marker setzen
        try {
            HashMap<String,Object> properties = marker.getProperties();
            properties.put("font", this.iconFont);
            marker.setProperties(properties);
        } catch(Exception e){;}

        // Marker zur Liste hinzufügen
        map.addMarker(marker);
    }


    /**
     * Konstruktor für LifeMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper läuft
     */
    public LifeMapper(PApplet app){
        super(app);
    }

    /**
     * Initialisiert Fenster, Karte und Buttons
     * Methode muss in setup des Processing Sketches aufgerufen werden
     */
    public void init(){
        this.setStartZoomLevel(8);
        super.init();
        this.setStyle("terrain");

        // Play Button erstellen
        this.play = new PlayButton(this, 41);
    }

    /**
    * Laedt Liste ein und erstellt Marker
    * Methode muss in setup des Processing Sketches aufgerufen werden
    *
    * @param trackpointList TrackpointList die dargestellt werden soll
    */
    public void load(TrackpointList trackpointList){

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
        this.marker = new ServiceMarker(this.currTrackpoint);
        this.marker.setFont(this.iconFont);
        this.marker.setFontsize(24);
        this.marker.setColor(this.highlightColor);
        this.map.addMarker(marker);
    }

    /**
    * Zeichenmethode
    */
    public void draw(){
        super.draw();
        this.play.draw();
        this.drawInfoBox(this.time.toString("EE, HH:mm:ss, MMM d, YYYY") + "  —  speed: " + (int)this.currSpeeds[2] + "km/h" + activityDescription(this.currTrackpoint));

        if ( !this.paused && this.iter.hasNext() ){
            this.time = this.time.plusSeconds(this.secondsPerFrame);

            if ( this.nextTrackpoint.compareTimeTo(this.time) < 0 ){
                this.currTrackpoint = this.nextTrackpoint;
                this.nextTrackpoint = this.iter.next();
                this.updateSpeeds();
                int timeExtra = (int)(this.currTrackpoint.getSeconds() - this.time.getMillis()/1000L);
                this.location.setLon(this.currTrackpoint.getLongitude() +  timeExtra * this.currSpeeds[0]);
                this.location.setLat(this.currTrackpoint.getLatitude() +  timeExtra * this.currSpeeds[1]);
            } else {
                this.location.setLon(this.location.getLon() +  this.secondsPerFrame * this.currSpeeds[0]);
                this.location.setLat(this.location.getLat() +  this.secondsPerFrame * this.currSpeeds[1]);
            }

            this.marker.setLocation(this.location);
            this.marker.setService(this.currTrackpoint.getService());
            this.marker.setSize((int) Math.pow(this.map.getZoomLevel(),2)/4);
            this.marker.draw(this.map);

            if ( marker.getDistanceTo(this.map.getCenter()) > 1000000/(Math.pow((this.map.getZoomLevel()-4)*5,3)) )
                this.map.panTo(marker.getLocation());
        }
    }

    public String activityDescription(Trackpoint trackpoint){
        String service = trackpoint.getService().toLowerCase();
        if ( service.contains("telephonie") || service.contains("telefonie") || service.contains("phone") || service.contains("call") )
            return "  —  on the phone";
        else if ( service.contains("sms") || service.contains("text") || service.contains("chat") )
            return "  —  texting someone";
        else if ( service.contains("email") || service.contains("mail") )
            return "  —  writing an email";
        else if ( service.contains("internet") || service.contains("browser") )
            return "  —  surfing the web";
        else
            return "";
    }

    /**
     * Aktualisiert die horizontale und vertikale Geschwindigkeit
     */
    void updateSpeeds(){
        float lonDist = this.currTrackpoint.getLongitude() - this.nextTrackpoint.getLongitude();
        float latDist = this.currTrackpoint.getLatitude() - this.nextTrackpoint.getLatitude();
        long timeDist =  this.currTrackpoint.getSeconds() - this.nextTrackpoint.getSeconds();

        this.currSpeeds[0] = lonDist/timeDist;
        this.currSpeeds[1] = latDist/timeDist;
        this.currSpeeds[2] = (float) Math.abs(this.currTrackpoint.locationDistanceTo(this.nextTrackpoint)/(timeDist/3600d));
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