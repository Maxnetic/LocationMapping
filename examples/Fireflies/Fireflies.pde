import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

void setup() {
    FireflyMapper mapper = new FireflyMapper(this);
    mapper.init(825, 985);
    
    // TrackpointList data = mapper.importData("cellloc_germany.csv");
    TrackpointList data = mapper.importData("fireflies.csv");
    // mapper.exportData(data, "fireflies");
    
    mapper.load(data);
}

void draw(){}
