import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*; 


//Iterator iter;

UnfoldingMap map; 
int speed = 1;
int currentTrackpoint = 0;
String[] trackdata;
Iterator iter;

void setup() {
  size(1000, 600);
  if(frame != null){
    frame.setResizable(true);
  }
  smooth();
  
  
  map = new UnfoldingMap(this);
  map.setTweening(true); // richtiges smooth Movement
  map.zoomAndPanTo(new Location(52.5f, 13.4f), 5); // Ort und Zoomlevel Init
  MapUtils.createDefaultEventDispatcher(this, map); //für StandardInteraktion

  // lade Daten von MalteSpitz
  TrackpointList tpl;
  DatenImportMalte im= new DatenImportMalte();
  tpl = im.ladeStandardCSV("Daten_Malte_Spitz.csv");
  
  Filter f = new Filter();
  f.setType("Frequency");
  f.setMinFrequency(1);
  TrackpointList filtered = f.apply(tpl);
  iter = filtered.iterator();
  
} 
  
  
void draw() {
  map.draw();
  if(iter.hasNext()){
      if(frameCount % speed == 0){
        Trackpoint curr = (Trackpoint) iter.next();
        MyMarker tmp = new MyMarker(curr);
        tmp.setStyle("Rectangle");
        map.addMarker(tmp);
        //map.panTo(curr.getLocation());
        //System.out.println(curr.getDateTime());
      }
    }
  // ------ Filter ENDE ------
  
}

