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


//Iterator iter;

UnfoldingMap map; 
int stageWidth = 1000;
int stageHeight = 600;
int speed = 1;
int currentTrackpoint = 0;
String[] trackdata;
Iterator iter;
PlayButton play = new PlayButton(stageWidth, stageHeight);
ZoomButton zoomInto = new ZoomButton(175, 14, 15, 15, true);
ZoomButton zoomFrom = new ZoomButton(0, 14, 15, 15, false);
SliderButton slider;
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
  map.draw();
  slider.draw();
  play.draw();
  zoomInto.draw();
  zoomFrom.draw();
  
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
  if (play.mouseOver()) {
    pause = !pause;
  }
  if (zoomInto.mouseOver()) {
    map.zoomLevelIn();
  }
  if (zoomFrom.mouseOver()) {
    map.zoomLevelOut();
  }
  if (slider.mouseOver()) {
    if (mouseEvent.getX() < (width-830.0-(150.0/map.getZoom()))) {
        map.zoomLevelOut();
    } else {
       map.zoomLevelIn();
    };
  }
  
}
