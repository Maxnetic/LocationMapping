package locationmapping;

import java.util.*;

import processing.core.PApplet;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

import de.fhpotsdam.unfolding.marker.*;

public class DynamicMapper extends Mapper {
    ArrayList<Marker> markerList = new ArrayList<Marker>();
    Iterator<Marker> iter;

    int speed = 3;

    /**
     * Der Start/Pause-Knopf
     */
    PlayButton play;

    /**
     * Konstruktor für DynamicMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper läuft
     */
    public DynamicMapper(PApplet app){
        super(app);
    }

    public void init(){
        super.init();

        // Play Button erstellen
        this.play = new PlayButton(this, 41);
    }

    public void addMarker(Marker marker) {
        this.markerList.add(marker);
        this.iter = this.markerList.iterator();
    }

    public void draw(){
        super.draw();

        this.play.draw();

        if ( !this.paused && app.frameCount % this.speed == 0 ){
            if ( this.iter.hasNext() ){
                Marker marker = this.iter.next();
                this.map.addMarker(marker);
                // this.map.panTo(marker.getLocation());
            }
        }
    }

    void clickEventHandler(int x, int y){
        super.clickEventHandler(x, y);
        if ( play.mouseOver(x, y) )
            paused = !paused;
    }

    public void keyEvent(KeyEvent e){
        super.keyEvent(e);

        if ( e.getKey() == ' ' ) {
            paused = !paused;
        }
    }
}