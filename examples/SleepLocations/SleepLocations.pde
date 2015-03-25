import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
    
    LocationMapper mapper = new StaticMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.import("malte_spitz.csv");
    
    Filter timeFilter = new TimeFilter();
    timeFilter.from(2).to(5);
    TrackpointList sleepLocations = timeFilter.apply(data); 
    
    for( Trackpoint tp : sleepLocations ){
        StandardMarker marker = new StandardMarker();
        marker.setArea(sleepLocations.getFrequency(tp));
        mapper.addMarker(marker);
    }
}

void draw(){}
