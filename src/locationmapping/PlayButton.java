package locationmapping;

import processing.core.PApplet;

public class PlayButton extends Button {
    LocationMapper mapper;

    public PlayButton(LocationMapper mapper, int w, int h) {
        //zeichne Rechteck
        super(mapper, w/2-16 , h-56, 28, 28);
        this.mapper = mapper;
    }

    boolean mouseOver(int xM, int yM) {
        return (xM > x && xM < x + w && yM > y && yM < y + h);
    }

    void draw() {
        super.draw();

        //zeichne einen Kreis über das Rechteck
        app.fill(mouseOver(app.mouseX, app.mouseY) ? 255 : 220);
        app.stroke(150);
        app.ellipse(app.width/2-2, app.height-42, 40, 40);


        app.noStroke();
        app.fill(120);
        /**
         * Je nach Pausezustand wird Play- oder Stop-Symbol gezeichnet
         */
        if(mapper.paused == false) {
            app.rect(app.width/2-9, app.height-49, 5, 15);
            app.rect(app.width/2+1, app.height-49, 5, 15);
        }
        else {
            app.triangle(app.width/2-5, app.height-48, app.width/2-5, app.height-34, app.width/2+5, app.height-42);
        }
    }


}

