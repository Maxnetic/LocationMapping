import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import java.sql.Timestamp;


UnfoldingMap map;

void setup() {
    // Processing Window setup
    size(800, 1000);
    frame.setResizable(true);

    // Map Setup
    map = new UnfoldingMap(this);
    MapUtils.createDefaultEventDispatcher(this, map);
    map.zoomAndPanTo(new Location(52.5f, 13.4f), 12); // zoom map to Berlin

    // Import Data
    DataImporter importer = new DataImporter(this);
    TrackpointList trackpointList = importer.load("malte_spitz.csv");
    
    for ( Trackpoint trackpoint : trackpointList ){
        StandardMarker marker = new StandardMarker(trackpoint);
        map.addMarker(marker);
    }
}

void draw() {
  map.draw();
}
