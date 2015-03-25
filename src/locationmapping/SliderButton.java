package locationmapping;

import processing.core.PApplet;

public class SliderButton extends Button {
    public SliderButton(LocationMapper mapper, float x, float y, int w, int h){
        super(mapper, w-980, h-580, x, y);
    }

    /**
     * Zeichne den Schieberegler des Zooms
     */
    void draw(){
         super.draw();
         app.rect(20 + (150 * (map.getZoom() / 262144) ), app.height-581.0f, 5.0f, 5.0f);
    }
}

