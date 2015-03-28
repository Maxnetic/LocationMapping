package locationmapping;

import java.util.*;

import org.joda.time.DateTime;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.marker.*;

public class LifeMapper extends Mapper {
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
    DateTime currentTime = new DateTime(0);

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
        this.markerList.add(marker);
        this.iter = this.markerList.iterator();
    }

    /**
    * Zeichenmethode
    */
    public void draw(){
        super.draw();

        this.play.draw();

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
        this.app.text(this.currentTime.toString("E YYYY MMM dd HH:MM:SS") , 32, this.app.height-20);

        if ( !this.paused && app.frameCount % this.speed == 0 && this.iter.hasNext()){
            StandardMarker marker = (StandardMarker) this.iter.next();
            this.map.addMarker(marker);
            this.currentTime = marker.getTime();
            if ( marker.getDistanceTo(this.map.getCenter()) > this.map.getZoomLevel()*5 )
                this.map.panTo(marker.getLocation());
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