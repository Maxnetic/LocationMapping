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
  
  
  map = new MyMap(this);


  // lade Daten von MalteSpitz
  TrackpointList tpl;
  DatenImporter im= new DatenImporter();
  tpl = im.load("malte_spitz.csv");
  
  Filter f = new Filter();
  f.setStarttime("08:30");
  f.setEndtime("10:30");
  f.setStartDate("2009:09:01");
  f.setEndDate("2009:09:30");
  f.setWeekday("Montag-Mittwoch,Freitag");
  f.setLocation(new Location(52.3f , 13.4f));
  //f.setRadius(20);
  //f.setService("SMS");
  //f.setMinFrequency(5);
  f.apply(tpl);
  iter = tpl.iterator();
  

  
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
  
      
      
    if(frameCount % speed == 0 && pause == false){
      while (iter.hasNext() ){
        Trackpoint curr = (Trackpoint) iter.next();
        if (curr.getVisible()){
             MyMarker tmp = new MyMarker(curr);
              //System.out.println(curr.getDateTime());
              tmp.setStyle(curr.getService());
              //System.out.println(curr.getVisible());
              //System.out.println(curr.getVisible());
              map.addMarker(tmp);
              System.out.println(curr.getDateTime());
              System.out.println(curr.getDayOfTheWeek());
              //map.panTo(curr.getLocation());
              //System.out.println(curr.getDateTime());
              break;
         }
        
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
