import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*;

UnfoldingMap map;
//List markerList;

void setup() {
    // Processing Window setup
    size(800, 1000);
    frame.setResizable(true);

    // Map Setup
    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    map.zoomAndPanTo(new Location(52.5f, 13.4f), 11); // zoom map to Berlin

    // Import Data
    DatenImportMalte importer = new DatenImportMalte();
    TrackpointList trackpointList = importer.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");

    // Filter Data
    FrequencyFilter filter = new FrequencyFilter();

    for ( Trackpoint trackpoint : filter.apply(trackpointList) ){
        ColoredMarker marker = new ColoredMarker(trackpoint);
        marker.updateSize(trackpointList.getFrequency(trackpoint));
        marker.updateColor(255,0,0);
        map.addMarker(marker);
    }

//    markerList = (map.getMarkers());
    // Iteration über alle erstellten Marker, um deren Größe zu verändern
    // for ( int i=0; i<markerList.size(); i++ ){
    //     UpdateableMarker current;
    //     current = (UpdateableMarker)markerList.get(i);
    //     current.updateSize(trackpointList.getFrequency(trackpointList.get(i)));
    //     current.updateColor(255,0,0);
    // }

}

void draw() {
    map.draw();
}
