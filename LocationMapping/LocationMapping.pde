import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*;

UnfoldingMap map;
TrackpointList tpl;
Iterator iter;
int speed = 5;
boolean delayedMarker = true;


// ColoredMarker berlinColoredMarker;
// FormedMarker randomFormedMarker;
//List markerList;

// creating map and marker
void setup() {
    size(800, 600);

    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    frame.setResizable(true);
    DatenImportMalte im = new DatenImportMalte();
    tpl = im.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");
    iter = tpl.iterator();
    
    Filter f = new Filter();

    Location berlinLocation = new Location(52.5, 13.4);
    // Location randomLocation = new Location(60.7, 8.2);
    // berlinColoredMarker = new ColoredMarker(berlinLocation);
    // randomFormedMarker = new FormedMarker(randomLocation);

    /*
     * Test für ColoredMarker / UpdateableMarker
     */
    // map.addMarker(berlinColoredMarker);
    // map.addMarker(randomFormedMarker);

    // berlinColoredMarker.updateColor(0,250,0);

    // größe anpassen
    // berlinColoredMarker.updateSize(400);

    // verstecken
    // berlinColoredMarker.updateHidden(true);

    // größe wieder anpassen, obwohl versteckt wird sie angepasst
    // berlinColoredMarker.updateSize(60);

    // sichtbar machen
    // berlinColoredMarker.updateHidden(false);

    map.zoomAndPanTo(new Location(52.5f, 13.4f), 8);
    
    if (delayedMarker == false){
       while (iter.hasNext()){
          Trackpoint curr = (Trackpoint) iter.next();
          SimplePointMarker tmp = new SimplePointMarker(curr.getLocation());
          map.addMarker(tmp);
          map.panTo(curr.getLocation());
       }
    }
    
    /*
     * Test für FormedMarker
     */
    //map.addMarker(berlinFormedMarker);
    
    // ---------- test für Filter
    //tpl = f.filterRadius(tpl,berlinLocation, 50);
    
    //tpl = f.filterTime(tpl, , );
    
    /* Test für filterTime
    Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0);
    Timestamp ts2 = new Timestamp(2009,12,2,22,22,15,0); 
    tpl = f.filterTime(tpl, ts1, ts2);
    */
    
    /* Test für filterTimeOfDay
    tpl = f.filterTimeOfDay(tpl, 0,3);
    */
    
    
    //markerList = (map.getMarkers());

    //Iteration über alle erstellten Marker, um deren Größe zu verändern
    //for (int i=0;i <markerList.size();i++){
    //     UpdateableMarker current;
    //     current = (UpdateableMarker)markerList.get(i);
    //     current.updateSize(4);
    //     current.updateColor(100,0,100);
    // }
}

/*
 * draw wird permanent ausgeführt
 */
void draw() {
    map.draw();
    
    if (iter.hasNext() && delayedMarker == true){
      if (frameCount % speed == 0){
        Trackpoint curr = (Trackpoint) iter.next();
        SimplePointMarker tmp = new SimplePointMarker(curr.getLocation());
        map.addMarker(tmp);
        map.panTo(curr.getLocation());
      }
    }
   
    
    //ScreenPosition berlinPos = berlinColoredMarker.getScreenPosition(map); //not necessary
}

