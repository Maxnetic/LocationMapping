package locationmapping;

import java.util.List;

import processing.core.PConstants;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.MapPosition;


/**
 * Marker representing multiple locations as lines. Use directly to display as simple lines, or extend it for custom styles.
 * 
 * This can be a polyline consisting of multiple locations, or a single line consisting of two locations.
 */
public class ColoredLinesMarker extends SimpleLinesMarker {
	int size = 30;
	int hsb_h = 0;
	int hsb_s = 0;
	int hsb_b = 0;
	int transparency = 100;
	
	
	public ColoredLinesMarker() {
		super();
	}
	
	
	/**
	   * Creates a marker for a single line, with a connection from start to end location. This convenience method adds the given
	   * start and end locations to the list.
	   * 
	   * @param startLocation
	   *            The location of the start of this line.
	   * @param endLocation
	   *            The location of the end of this line.
	   */
	public ColoredLinesMarker(Location start, Location end) {
		super(start, end);
	}
	
	
	/**
	   * Creates a polyline marker.
	   * 
	   * @param locations
	   *            The locations to connect via lines.
	   */
	public ColoredLinesMarker(List<Location> locations){
		super(locations);
	}
	
	public void setTransparency(int trans){
		transparency = trans;
	  }
	  
	  /**
	  *Die Funktion updated die Farbe des Markers
	  *@param color [int]: Farbwert
	  */
	  public void setColor(int hsb_h, int hsb_s, int hsb_b){
	    this.hsb_h =hsb_h;
		this.hsb_s = hsb_s;
		this.hsb_b = hsb_b;
	  }
	  
	  /**
	  * setColor Funktion mit konstanten Strings
	  * @param colorstr [String]: Die Farbe des Strings
	  */
	  
	  public void setColor(String colorstr){
	    colorstr.toLowerCase();
		if (colorstr.equals("rot")){
			hsb_h = 0;
			hsb_s = 99;
			hsb_b = 99;	
		} else	if (colorstr.equals("blau")){
			hsb_h = 240;
			hsb_s = 99;
			hsb_b = 99;
		} else if (colorstr.equals("grün")){
			hsb_h = 100;
			hsb_s = 99;
			hsb_b = 99;
		} else if (colorstr.equals("gelb")){
			hsb_h = 60;
			hsb_s = 99;
			hsb_b = 99;
		} else if (colorstr.equals("grau")){
			hsb_h = 0;
			hsb_s = 1;
			hsb_b = 60;
		}else{
		System.out.println("Die Farbe ist nicht in der Liste! Farbe mit HSB Codierung möglich!");
		}
			
	  }
	  
	/**
	 * 
	 */
	public void draw(PGraphics pg, List<MapPosition> mapPositions){
		if (!this.isHidden()){
			if (mapPositions.isEmpty() || isHidden())
			      return;

			    pg.pushStyle();
			    pg.noFill();
			    if (isSelected()) {
			      pg.stroke(highlightColor);
			    } else {
			    	pg.stroke(hsb_h, hsb_s, hsb_b, transparency);
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
			  }
		}
}


