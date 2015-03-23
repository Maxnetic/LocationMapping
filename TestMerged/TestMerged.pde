import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*; 
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Der Name der Weltkarte
 */
UnfoldingMap map;
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
PlayButton play = new PlayButton(stageWidth, stageHeight);
/**
 * Der "+"-Knopf
 */
ZoomButton zoomInto = new ZoomButton(175, 14, 15, 15, true);
/**
 * Der "-"-Knopf
 */
ZoomButton zoomFrom = new ZoomButton(0, 14, 15, 15, false);
/**
 * Der Zoomslider
 */
SliderButton slider;
/**
 * Der Zustand des Pauseknopfs
 */
boolean pause = true;

void setup() {
  size(stageWidth, stageHeight);
  if(frame != null){
    frame.setResizable(false);
  }
  smooth();
  
  
  map = new UnfoldingMap(this);
  map.setTweening(true); // richtiges smooth Movement
  map.zoomAndPanTo(new Location(52.5f, 13.4f), 5); // Ort und Zoomlevel Init
  MapUtils.createDefaultEventDispatcher(this, map); //für StandardInteraktion
  slider = new SliderButton(150, 3, stageWidth, stageHeight);

  // lade Daten von MalteSpitz
  TrackpointList tpl;
  DatenImporter im= new DatenImporter();
  tpl = im.load("malte_spitz.csv");
  
  Filter f = new Filter();
  f.setType("Frequency");
  f.setMinFrequency(1);
  TrackpointList filtered = f.apply(tpl);
  iter = filtered.iterator();
  
  addKeyListener(new KeyAdapter() {
  public void keyPressed(KeyEvent e){
     if (e.getKeyCode() == KeyEvent.VK_SPACE){
       pause = !pause;
     }
  }
} );
  
} 
  
  
void draw() {
  //zeichne die Karte
  map.draw();
  //zeichne den Slider
  slider.draw();
  //zeichne den Pauseknopf
  play.draw();
  //zeichne den Plusknopf
  zoomInto.draw();
  //zeichne den Minusknopf
  zoomFrom.draw();
  
  //zeige Marker verzögert an
  if(iter.hasNext()){
      if(frameCount % speed == 0 && pause == false){
        Trackpoint curr = (Trackpoint) iter.next();
        MyMarker tmp = new MyMarker(curr);
        System.out.println(curr.getDateTime());
        tmp.setStyle(curr.getService());
        map.addMarker(tmp);
        //map.panTo(curr.getLocation());
        //System.out.println(curr.getDateTime());
      }
    }
  // ------ Filter ENDE ------
  
}

void mouseClicked() {
  //wenn auf Playbutton gedrückt, kehre Pausenstatus um
  if (play.mouseOver()) {
    pause = !pause;
  }
  // wenn auf "+" gedrückt, zoome hinein
  if (zoomInto.mouseOver()) {
    map.zoomLevelIn();
  }
  // wenn auf "-" gedrückt, zoome hinaus
  if (zoomFrom.mouseOver()) {
    map.zoomLevelOut();
  }
  // wenn auf den Slider gedrückt, zoome hinaein oder hinaus
  if (slider.mouseOver()) {
    if (mouseEvent.getX() < (width-830.0-(150.0/map.getZoom()))) {
        map.zoomLevelOut();
    } else {
       map.zoomLevelIn();
    };
  }
  
}
