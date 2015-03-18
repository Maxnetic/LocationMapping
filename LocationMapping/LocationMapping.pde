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
// List markerList;

// creating map and marker
void setup() {
    size(800, 600);

    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    frame.setResizable(true);


    // Location berlinLocation = new Location(52.5, 13.4);
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

    // markerList = (map.getMarkers());

    //Iteration über alle erstellten Marker, um deren Größe zu verändern
    // for (int i=0;i <markerList.size();i++){
    //     UpdateableMarker current;
    //     current = (UpdateableMarker)markerList.get(i);
    //     current.updateSize(20);
    //     current.updateColor(100,0,100);
    // }

    /*
     * Test für FormedMarker
     */
    //map.addMarker(berlinFormedMarker);
    DatenImportMalte im = new DatenImportMalte();
    TrackpointList tpl = im.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");
    for ( Trackpoint tp : tpl ){
        ColoredMarker marker = new ColoredMarker(tp.getLocation());
        map.addMarker(marker);
    }
}

/*
 * draw wird permanent ausgeführt
 */
void draw() {
    map.draw();
    //ScreenPosition berlinPos = berlinColoredMarker.getScreenPosition(map); //not necessary
}