import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
    
    Mapper mapper = new StaticMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    DateTimeFilter timeFilter = new DateTimeFilter();
    timeFilter.fromTime("2").toTime("5");
    TrackpointList sleepLocations = timeFilter.apply(data); 
    
    for( Trackpoint tp : sleepLocations ){
        StandardMarker marker = new StandardMarker(tp);
        mapper.addMarker(marker);
    }
}

void draw(){}
