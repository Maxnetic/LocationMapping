import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

  

void setup() {
  StaticMapper mapper = new StaticMapper(this);
  hint(ENABLE_RETINA_PIXELS);
  colorMode(HSB,360,100,100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("personX.csv");
    
  
  
    
      
}

void draw() {
      
}

