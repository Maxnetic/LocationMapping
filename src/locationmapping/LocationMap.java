package locationmapping;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;

public class LocationMap extends UnfoldingMap {
    PApplet app;
    boolean paused = false;

    /**
     * die Breite der Karte
     */
    int stageWidth = 1000;
    /**
     * Die Höhe der Karte
     */
    int stageHeight = 600;
    /**
     * Die Geschwindigkeit der Anzeige von Markern im verzögerten Modus
     */
    int speed = 1;
    /**
     * Der Iterator zum Durchgehen der Trackpointliste
     */
    Iterator iter;
    /**
     * Der Pauseknopf
     */
    PlayButton play = new PlayButton(map, stageWidth, stageHeight);
    /**
     * Der "+"-Knopf
     */
    ZoomButton zoomInto = new ZoomButton(map, 175, 14, 15, 15, true);
    /**
     * Der "-"-Knopf
     */
    ZoomButton zoomFrom = new ZoomButton(map, 0, 14, 15, 15, false);
    /**
     * Der Zoomslider
     */
    SliderButton slider;

    public LocationMap(PApplet app){
        super(app);
        this.setTweening(true); // richtiges smooth Movement
        this.zoomAndPanTo(new Location(52.5f, 13.4f), 5); // Ort und Zoomlevel Init
        MapUtils.createDefaultEventDispatcher(app, this); //für StandardInteraktion
        SliderButton slider = new SliderButton(this, 150, 3, app.width, app.height);

        void draw() {
            //zeichne den Slider
            slider.draw();
            //zeichne den Pauseknopf
            play.draw();
            //zeichne den Plusknopf
            zoomInto.draw();
            //zeichne den Minusknopf
            zoomFrom.draw();
        }

        app.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    paused = !paused;
                }
            }
        } );



    }

}
