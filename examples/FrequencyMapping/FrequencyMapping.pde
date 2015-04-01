import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.setResizableAndDisableOverview(true);
    mapper.setStartLocation(Const.GERMANY);
    mapper.setStartZoomLevel(7);
    mapper.init(900, 1300);
    mapper.setStyle("dark");
    
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    for ( Trackpoint trackpoint : data ) {
        
        PointMarker marker = new PointMarker(trackpoint).setColor(75, 100, 90, 50);
        
        int frequency = data.getFrequency(trackpoint);
        marker.setArea(frequency*10);
        
        int saturation = frequency<400 ? 20+frequency/5 : 100;
        marker.setSaturation(saturation);
        
        mapper.addMarker(marker);

    }
}


void draw() {}
