package locationmapping;

import java.util.*;

import org.joda.time.DateTime;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.marker.*;

/**
 * Die Klasse DynamicMapper erzeugt ein Mapper-Objekt, das Marker dynamisch nacheinander auf die Karte zeichnet.
 * DynamicMapper erweitert Mapper.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class DynamicMapper extends Mapper {
    ArrayList<Marker> markerList = new ArrayList<Marker>();
    Iterator<Marker> iter;
    /**
    * Geschwindigkeit mit der gezeichnet wird
    */
    int speed = 1;
    /**
     * Der Start/Pause-Knopf
     */
    PlayButton play;
    /**
     * Der aktuelle Zeitpunkt, der gezeichnet wird
     */
    String timeString = "";

    /**
     * Konstruktor für DynamicMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper läuft
     */
    public DynamicMapper(PApplet app){
        super(app);
    }
    /**
    * Initmethode
    */
    public void init(){
        super.init();

        // Play Button erstellen
        this.play = new PlayButton(this, 41);
    }
    /**
    * Fuegt Marker hinzu
    *
    * @param marker Marker der hinzugefuegt werden soll
    */
    public void addMarker(Marker marker) {
        try {
            HashMap<String,Object> properties = marker.getProperties();
            properties.put("font", this.iconFont);
            marker.setProperties(properties);
        } catch(Exception e){;}
        this.markerList.add(marker);
        this.iter = this.markerList.iterator();
    }

    /**
    * Zeichenmethode
    */
    public void draw(){
        super.draw();

        this.play.draw();
        this.drawInfoBox(this.timeString);

        if ( !this.paused && app.frameCount % this.speed == 0 && this.iter.hasNext() ){
            Marker marker = this.iter.next();
            this.map.addMarker(marker);

            // Update timeString mit Zeit Property des Markers
            try {
                DateTime time = (DateTime) marker.getProperty("time");
                this.timeString = time.toString("EE, HH:mm:ss, MMM d, YYYY");
            } catch (Exception e){
                this.timeString = "";
            }
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