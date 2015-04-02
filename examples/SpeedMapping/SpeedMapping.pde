import de.fhpotsdam.unfolding.*;
import locationmapping.*;
import java.util.Iterator;

void setup() {

    Mapper mapper = new StaticMapper(this);
    mapper.setResizableAndDisableOverview(true);
    mapper.setStartLocation(Const.Berlin);
    mapper.setStartZoomLevel(12);
    mapper.init(1700, 1300);
    mapper.setStyle("dark");

    TrackpointList data = mapper.importData("malte_spitz.csv");

    Iterator<Trackpoint> iter = data.iterator();
    Trackpoint curr = iter.next();
    while ( iter.hasNext() ){
        Trackpoint next = iter.next();

        LineMarker marker = new LineMarker(curr, next).setColor(0, 50, 90);

        float speed = marker.getSpeed();
        int hue = speed<300 ? (int)(speed/2) : 200;
        marker.setHue(hue);

        if( marker.getDuration()/3600 < 24 )
            mapper.addMarker(marker);

        curr = next;
    }
}


void draw() {}
