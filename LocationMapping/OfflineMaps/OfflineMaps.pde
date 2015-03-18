
/**
 * YOU NEED TO download the sqlitejdbc driver from https://bitbucket.org/xerial/sqlite-jdbc/
 * and put the jar file into the 'code' directory of this sketch.
 *
 *
 * This example uses a local MBTiles file. Thus, it does not need an Internet connection to load tiles.
 *
 * For testing purposes and to keep the file size small, this example supports only three zoom levels.
 */

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.core.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import java.util.List;

String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.atom";

UnfoldingMap map;

void setup() {
  size(800, 600, P2D);

  String mbTilesString = sketchPath("data/blank-1-3.mbtiles");

  map = new UnfoldingMap(this, new MBTilesMapProvider(mbTilesString));
  MapUtils.createDefaultEventDispatcher(this, map);
  map.setZoomRange(1, 3);
  
  List<Feature> features = GeoRSSReader.loadDataGeoRSS(this, earthquakesURL);
  List<Marker> markers = MapUtils.createSimpleMarkers(features);
  map.addMarkers(markers);
}

void draw() {
  background(240);
  map.draw();
}
