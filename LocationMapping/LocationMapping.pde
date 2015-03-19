import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*;

UnfoldingMap map;
Iterator iter;
int speed = 5;
boolean delayedMarker = true;


// ColoredMarker berlinColoredMarker;
// FormedMarker randomFormedMarker;
//List markerList;

// creating map and marker
void setup() {
    size(800, 600);
    frame.setResizable(true);

    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    map.zoomAndPanTo(new Location(52.5f, 13.4f), 8);
    
    DatenImportMalte im = new DatenImportMalte();
    TrackpointList tpl = im.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");
    iter = tpl.iterator();
    
//    Filter f = new Filter();

    Location berlinLocation = new Location(52.5, 13.4);
    
    if (delayedMarker == false){
       while (iter.hasNext()){
          Trackpoint curr = (Trackpoint) iter.next();
          SimplePointMarker tmp = new SimplePointMarker(curr.getLocation());
          map.addMarker(tmp);
          map.panTo(curr.getLocation());
       }
    }
    
    /* Test für filterTime
    Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0);
    Timestamp ts2 = new Timestamp(2009,12,2,22,22,15,0); 
    tpl = f.filterTime(tpl, ts1, ts2);
    */
    
    /* Test für filterTimeOfDay
    tpl = f.filterTimeOfDay(tpl, 0,3);
    */
    
    FilterFrequency f = new FilterFrequency();
    f.setMinFrequency(100);
    tpl = f.apply(tpl);
    
    
    for ( Trackpoint tp : tpl ){
      ColoredMarker marker = new ColoredMarker(tp);
      marker.updateSize((int)Math.sqrt(tpl.getFrequency(tp)));
      map.addMarker(marker);
    }
}

/*
 * draw wird permanent ausgeführt
 */
void draw() {
    map.draw();
    
//    if (iter.hasNext() && delayedMarker == true){
//      if (frameCount % speed == 0){
//        Trackpoint curr = (Trackpoint) iter.next();
//        SimplePointMarker tmp = new SimplePointMarker(curr.getLocation());
//        map.addMarker(tmp);
//        map.panTo(curr.getLocation());
//      }
//    }
   
    
    //ScreenPosition berlinPos = berlinColoredMarker.getScreenPosition(map); //not necessary
}

