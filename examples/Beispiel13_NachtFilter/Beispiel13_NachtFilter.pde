import de.fhpotsdam.unfolding.*;
import locationmapping.*;

/**
 * In diesem Beispiel werden alle Orte eingezeichnet, 
 * an den die getrackte Person übernachtet hat.
 */

void setup() {
    StaticMapper mapper = new StaticMapper(this);
    mapper.init();
    mapper.setStyle("dark");
    
    TrackpointList data = mapper.importData("../../data/personX.csv");
    
    DateTimeFilter timeFilter = new DateTimeFilter();
    timeFilter.fromTime("2").toTime("5");
    TrackpointList sleepLocations = timeFilter.apply(data); 
    
    for( Trackpoint tp : sleepLocations ){
        PointMarker marker = new PointMarker(tp);
        marker.setColor(mapper.yellow);
        mapper.addMarker(marker);
    }
}

void draw(){}
