import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*;

UnfoldingMap map;

void setup() {
    // Processing Window setup
    size(800, 1000);
    frame.setResizable(true);

    // Map Setup
    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    Location berlinLocation = new Location(52.5, 13.4);
    map.zoomAndPanTo(berlinLocation, 6);

    // Import Data
    DatenImportMalte importer = new DatenImportMalte();
    TrackpointList tpl = importer.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");

//    if (delayedMarker == false){
//       while (iter.hasNext()){
//          Trackpoint curr = (Trackpoint) iter.next();
//          SimplePointMarker tmp = new SimplePointMarker(curr.getLocation());
//          map.addMarker(tmp);
//          map.panTo(curr.getLocation());
//       }
//    }

    /* Test für filterTime
    Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0);
    Timestamp ts2 = new Timestamp(2009,12,2,22,22,15,0);
    tpl = f.filterTime(tpl, ts1, ts2);
    */

    /* Test für filterTimeOfDay
    tpl = f.filterTimeOfDay(tpl, 0,3);
    */

    FilterFrequency f = new FilterFrequency();
    f.setMinFrequency(250);
    f.setAccuracy(0.05);
    tpl = f.apply(tpl);


    for ( Trackpoint tp : tpl ){
      ColoredMarker marker = new ColoredMarker(tp);
      marker.updateSize((int)Math.sqrt(tpl.getFrequency(tp)));
      marker.updateColor(255,0,0);
      map.addMarker(marker);
    }

}

void draw() {
    map.draw();
}

