import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*;

UnfoldingMap map;

String importPath = "Daten/Daten_Malte_Spitz.csv";
// String importPath = "D:/Files/SP/softwarepraktikum-ws2014_15-visualisierung-von-mobilfunkdaten/LocationMapping/Daten/cellloc_greece.tsv"

void setup() {
    // Processing Window setup
    size(800, 1000);
    frame.setResizable(true);

    // Map Setup
    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    
    String fileEnding = "";
    for (int i = 0; i < importPath.length(); i++){
        if (importPath.charAt(i) == '.'){
            for (int j = i; j < importPath.length(); j++){
                fileEnding = fileEnding + importPath.charAt(j);
            }
            break;
        }
    }
    
    if (fileEnding.equals(".csv")){
        //csv-Import über DatenImportMalte
        DatenImportMalte im = new DatenImportMalte();
         tpl = im.ladeStandardCSV(importPath);
    }
    if (fileEnding.equals(".json")){
        jsonimport js = new jsonimport();
        tpl = js.ladeJSON(importPath, 60000, 10000);
    }
    if (fileEnding.equals(".tsv")){
          tsvimport ti = new tsvimport();
          tpl = ti.import_fireflies_tsv(importPath);
    }

    iter = tpl.iterator();
    
    map.zoomAndPanTo(berlinLocation, 11);

    // Import Data
    DatenImportMalte importer = new DatenImportMalte();
    TrackpointList trackpointList = importer.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");

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
      marker.updateColor(255,0,0);
      map.addMarker(marker);
    }

    // Filter Data
    FrequencyFilter filter = new FrequencyFilter();

}

void draw() {
    map.draw();
}

