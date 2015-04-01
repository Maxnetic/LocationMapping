import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
    FireflyMapper mapper = new FireflyMapper(this);
    mapper.init(825, 700);
    
    TrackpointList data = mapper.importData("../../data/CellLocation_germany.tsv", Const.EXPONENT_APPLE);
    // TrackpointList data = mapper.importData("../../data/fireflies.csv");
    // mapper.exportData(data, "fireflies");
    
    
    
    mapper.load(data);
}

void draw(){}
