import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

void setup() {
    FireflyMapper mapper = new FireflyMapper(this);
    mapper.init(825, 985);
    
    // TrackpointList data = mapper.importData("cellloc_germany.tsv", Mapper.EXPONENT_APPLE);
    // mapper.exportData(data, "fireflies");
    TrackpointList data = mapper.importData("fireflies.csv");
    
    mapper.load(data);
}

void draw(){}
