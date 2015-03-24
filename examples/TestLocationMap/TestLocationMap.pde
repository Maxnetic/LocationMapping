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
import locationmapping.*;


LocationMap map = new LocationMap(this);

    
void setup() {
  size(stageWidth, stageHeight);
  if(frame != null){
    frame.setResizable(false);
  }
  smooth();


  // lade Daten von MalteSpitz
  TrackpointList tpl;
  DataImporter im = new DataImporter(this);
  tpl = im.load("malte_spitz.csv");
  
  Filter f = new Filter();
  f.setMinFrequency(1);
  TrackpointList filtered = f.apply(tpl);
  iter = filtered.iterator();
  

  
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
    if (mouseEvent.getX() < (20 + (150 * (map.getZoom() / 262144)))) {
        map.zoomLevelOut();
    } else {
       map.zoomLevelIn();
    };
  }
  
}
