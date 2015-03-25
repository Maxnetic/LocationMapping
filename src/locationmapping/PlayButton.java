package locationmapping;

import processing.core.PApplet;

public class PlayButton extends Button {
    LocationMapper mapper;
    /**
     * Durchmesser des Buttons
     */
    float d;

    @override
    public PlayButton(LocationMapper mapper, int d) {
        this.mapper = mapper;
        this.app = mapper.app;
        this.map = mapper.map;
        this.d = d;
    }

    boolean mouseOver(int xM, int yM) {
        return (xM > x && xM < x + w && yM > y && yM < y + h);
    }

    void draw() {
        this.x = app.width/2f - this.d/2f;
        this.y = app.height - this.d - 16;

        app.fill( mouseOver(app.mouseX, app.mouseY) ? 255 : 220);
        app.stroke(150);
        app.ellipse(x, y, d, d);

        // Je nach Pausezustand wird Play- oder Stop-Symbol gezeichnet
        app.noStroke();
        app.fill(120);
        if(mapper.paused == false) {
            app.rect(x+d/4, y+d/4, d/8, d/2);
            app.rect(x+3*d/4, y+d/4, d/8, d/2);
        }
        else {
            app.triangle(x+d/4, y+d/4, x+d/4, y+3*d/4, x+3*d/4, y+d/2);
        }
    }


}

