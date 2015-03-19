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
    map.zoomAndPanTo(new Location(52.5f, 13.4f), 11); // zoom map to Berlin

    // Import Data
    DatenImportMalte importer = new DatenImportMalte();
    TrackpointList trackpointList = importer.ladeStandardCSV("Daten/Daten_Malte_Spitz.csv");
    
    for ( Trackpoint trackpoint : trackpointList ){
        ColoredMarker marker = new ColoredMarker(trackpoint);
        marker.updateColor(0,150,150);
        map.addMarker(marker);
    }

    // Filter Data
    FilterFrequency filter = new FilterFrequency();
    filter.setAccuracy(0.01);
    filter.setMinFrequency(200);
    TrackpointList frequencyList = filter.apply(trackpointList);

    for ( Trackpoint trackpoint : frequencyList ){
        ColoredMarker marker = new ColoredMarker(trackpoint);
        marker.setArea(frequencyList.getFrequency(trackpoint));
        marker.updateColor(150,0,0);
        map.addMarker(marker);
    }
}

void draw() {
    map.draw();
}
