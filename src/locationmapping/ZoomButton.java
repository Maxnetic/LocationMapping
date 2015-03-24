package locationmapping;

import processing.core.PApplet;

public class ZoomButton extends Button {

    boolean in = false;

    public ZoomButton(LocationMap map, float x, float y, float w, float h, boolean in) {
        super(map, x, y, w, h);
        this.in = in;
    }

    void draw() {
        super.draw();
        app.stroke(0);
        app.line(x+3,y+h/2,x+w-3,y+h/2);
        if (in) {
            app.line(x+w/2,y+3,x+w/2,y+h-3);
        }
    }

}
