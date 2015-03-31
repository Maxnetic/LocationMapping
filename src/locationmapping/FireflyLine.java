package locationmapping;

import java.util.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.marker.*;
import processing.core.PConstants;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.utils.MapPosition;

public class FireflyLine extends ColoredLinesMarker {
    int lifetime = 50;
    
    
    
    public FireflyLine() {
        super();
    }


    /**
    * Konstruktor fuer ColoredLineMarker Objekte
    *
    * @param start Startpunkt
    * @param end Endpunkt
    */
    public FireflyLine(Location start, Location end) {
        super(start, end);
    }


    /**
    * Konstruktor fuer ColoredLineMarker Objekte
    *
    * @param locations Liste aus der Line gezeichnet werden soll
    */
    public FireflyLine(List<Location> locations){
        super(locations);
    }
  
    public int getLifetime(){
      return this.lifetime;
    }
    
    public void setLifetime(int lt){
      this.lifetime = lt;
    }
    
    
   @Override
   public void draw(PGraphics pg, List<MapPosition> mapPositions){
        if ((!this.isHidden()) && (this.lifetime != 0)){
            if ( mapPositions.isEmpty() || isHidden() ){
                return;
            }
            pg.pushStyle();
            pg.noFill();
            if ( isSelected() ){
              pg.stroke(highlightColor);
            } else {
                pg.stroke(59, 100, 100, lifetime);
            }
            pg.strokeWeight(strokeWeight);
            pg.smooth();
            
            pg.beginShape(PConstants.LINES);
            MapPosition last = mapPositions.get(0);
            for (int i = 1; i < mapPositions.size(); ++i) {
                MapPosition mp = mapPositions.get(i);
                pg.vertex(last.x, last.y);
                pg.vertex(mp.x, mp.y);
                last = mp;
            }
            pg.endShape();
            pg.popStyle();
            this.lifetime--;
            System.out.println(this.lifetime);
        }
    else {
      this.setHidden(true);
    }
  }  
}    

