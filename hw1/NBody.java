public class NBody {
	public static Planet getPlanet(In in) {
		return new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
	}
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In in = new In(filename);
		int numplanets = in.readInt();
		double radius = in.readDouble();

		Planet[] planets = new Planet[numplanets];
		for (int i = 0; i < numplanets; i++) {
			planets[i] = getPlanet(in);
		}

		StdDraw.setXscale(radius * -1, radius);
		StdDraw.setYscale(radius * -1, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Planet p : planets) {
			p.draw();
		}

		double time = 0.0;
		for (; time < T; time += dt) {
			for (Planet p : planets) {
				p.setNetForce(planets);
			}
			for (Planet p: planets) {
				p.update(dt);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet planet : planets) {
				planet.draw();
			}
			StdDraw.show(10);
			time += dt;
		}
	}
}