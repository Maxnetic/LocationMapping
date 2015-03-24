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
    public int stageWidth = 1000;
    /**
     * Die Höhe der Karte
     */
    public int stageHeight = 600;
    /**
     * Der Pauseknopf
     */
    PlayButton play;
    /**
     * Der "+"-Knopf
     */
    ZoomButton zoomInto;
    /**
     * Der "-"-Knopf
     */
    ZoomButton zoomFrom;
    /**
     * Der Zoomslider
     */
    SliderButton slider;

    public LocationMap(PApplet app){
        super(app);
        this.setTweening(true); // richtiges smooth Movement
        this.zoomAndPanTo(new Location(52.5f, 13.4f), 5); // Ort und Zoomlevel Init
        MapUtils.createDefaultEventDispatcher(app, this); //für StandardInteraktion
        this.slider = new SliderButton(this, 150, 3, app.width, app.height);
		this.play = new PlayButton(this, stageWidth, stageHeight);
		this.zoomInto = new ZoomButton(this, 175, 14, 15, 15, true);
		this.zoomFrom = new ZoomButton(this, 0, 14, 15, 15, false);

        app.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    paused = !paused;
                }
            }
        } );

    }

	public void draw() {
        //zeichne den Slider
        slider.draw();
        //zeichne den Pauseknopf
        play.draw();
        //zeichne den Plusknopf
        zoomInto.draw();
        //zeichne den Minusknopf
        zoomFrom.draw();
    }

}
