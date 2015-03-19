import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*;

UnfoldingMap map;
// ColoredMarker berlinColoredMarker;
// FormedMarker randomFormedMarker;
//List markerList;

// creating map and marker
void setup() {
    size(800, 600);

    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    map.setTweening(true);
    frame.setResizable(true);
    



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

    /*
     * Test für FormedMarker
     */
    //map.addMarker(berlinFormedMarker);
    
    DatenImportMalte im = new DatenImportMalte();
    TrackpointList tpl = im.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");

    //Test für FilterLocation
    /*
    FilterLocation f = new FilterLocation();
    f.setLocation(berlinLocation);
    f.setRadius(25);
    tpl = f.apply(tpl);
    */
    
    //Test FilterDate
    /*
    FilterDate f = new FilterDate();
    Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0);
    f.setStartDate(ts1);
    Timestamp ts2 = new Timestamp(2009,10,5,22,20,40,0);
    f.setEndDate(ts2);
    tpl = f.apply(tpl);
    */
    
    //Test FilterFrequency
    FilterFrequency f = new FilterFrequency();
    f.setMinFrequency(4000);
    tpl = f.apply(tpl);
    
    
    for ( Trackpoint tp : tpl ){
        ColoredMarker marker = new ColoredMarker(tp.getLocation());
        map.addMarker(marker);
    }
    
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
    //ScreenPosition berlinPos = berlinColoredMarker.getScreenPosition(map); //not necessary
}
