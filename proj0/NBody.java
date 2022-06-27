public class NBody {
    public static String img_name = "./images/starfield.jpg";

    public static double readRadius(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double r = in.readDouble();
        Body[] b = new Body[num];
        for(int i = 0; i < num; i++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            b[i] = new Body(xp, yp, vx, vy, m, img);
        }
        return b;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Body[] bodies = readBodies(filename);

        // set the universe scale
        StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
		StdDraw.enableDoubleBuffering();

        double t = 0;
		int num = bodies.length;
		while(t <= T){
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for(int i = 0; i < num; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for(int i = 0; i < num; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}

			// draw the background picture
			StdDraw.picture(0, 0, "images/starfield.jpg");

			// draw all the bodies
			for (Body b : bodies) {
				StdDraw.picture(b.xxPos, b.yyPos, b.imgFileName);	
			}

			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
}
