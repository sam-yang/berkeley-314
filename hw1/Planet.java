public class Planet {
	double x;
	double y;
	double xVelocity;
	double yVelocity;
	double mass;
	String img;
	double xNetForce;
	double yNetForce;
	double xAccel;
	double yAccel;

	public Planet(double px, double py, double pxvel, double pyvel, double pmass, String pimg) {
		x = px;
		y = py;
		xVelocity = pxvel;
		yVelocity = pyvel;
		mass = pmass;
		img = pimg;
		xNetForce = 0;
		yNetForce = 0;
		xAccel = 0;
		yAccel = 0;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
	}

	public double calcPairwiseForce(Planet p) {
		return 6.67e-11 * mass * p.mass / (calcDistance(p) * calcDistance(p));
	}

	public double calcPairwiseForceX(Planet p) {
		return calcPairwiseForce(p) * (p.x - x) / calcDistance(p);
	}

	public double calcPairwiseForceY(Planet p) {
		return calcPairwiseForce(p) * (p.y - y) / calcDistance(p);
	}

	public void setNetForce(Planet[] parray) {
		xNetForce = 0;
		yNetForce = 0;
		for (Planet p : parray) {
			if (!this.equals(p)) {
				this.xNetForce += calcPairwiseForceX(p);
				this.yNetForce += calcPairwiseForceY(p);
			}
		}
	}

	public void draw() {
		StdDraw.picture(x, y, "images/" + img);
	}
	public void update(double dt) {
		xAccel = xNetForce / mass;
		yAccel = yNetForce / mass;
		xVelocity += dt * xAccel;
		yVelocity += dt * yAccel;
		x += dt * xVelocity;
		y += dt * yVelocity;
	}
}