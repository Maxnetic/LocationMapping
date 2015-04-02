import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
    FireflyMapper mapper = new FireflyMapper(this);
    mapper.setResizableAndDisableOverview(true);
    mapper.init();
   
    // TrackpointList data = mapper.importData("cellloc_germany.csv");
    TrackpointList data = mapper.importData("fireflies.csv");
    
    mapper.load(data);
}

void draw(){}
