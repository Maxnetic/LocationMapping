package locationmapping;

import processing.core.PApplet;

public class SliderButton extends Button {
    float zoomLevel;
    int startZoomLevel;

    public SliderButton(LocationMapper mapper, float x, float y, float w, float h, float zoomLevel, int startZoomLevel){
        super(mapper, x, y, w, h);
        this.zoomLevel = zoomLevel;
        this.startZoomLevel = startZoomLevel;
    }

    /**
     * Zeichne den Schieberegler des Zooms
     */
    void draw(){
         super.draw();
         app.rect(x + ((w-4) * ((map.getZoomLevel()-this.startZoomLevel) / this.zoomLevel) ), 16, 4, 16);
    }

    boolean mouseOver(int xM, int yM) {
        return (xM > x && xM < x + w && yM > y-6 && yM < y+h+6);
    }

    void zoomHandler(int xM) {
        int clickedZoom = (int) ( (xM-x)/(w-4f) * this.zoomLevel ) + this.startZoomLevel;
        map.zoomToLevel( clickedZoom );
    }
}

