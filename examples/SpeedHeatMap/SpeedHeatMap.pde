import de.fhpotsdam.unfolding.*;
import locationmapping.*;
import java.util.Iterator;
import org.joda.time.*;

void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.setResizableAndDisableOverview(true);
    mapper.setStartLocation(Const.GERMANY);
    mapper.setStartZoomLevel(7);
    mapper.init(900, 1300);
    mapper.setStyle("dark");
    
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    Iterator<Trackpoint> iter = data.iterator();
    Trackpoint curr = iter.next();
    while ( iter.hasNext() ){
        Trackpoint next = iter.next();
        LineMarker marker = new LineMarker(curr, next);
        
        float speed = marker.getSpeed();
        int hue = speed<300 ? (int)(speed/2) : 200;
        marker.setHue(hue).setSaturation(50).setBrightness(80).setTransparency(100);
        
        if( marker.getDuration()/3600 < 24 )
            mapper.addMarker(marker);
        
        curr = next;
    }
}


void draw() {}
