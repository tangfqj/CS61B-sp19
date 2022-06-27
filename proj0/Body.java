import java.sql.Array;

public class Body{
    public double xxPos;   // current x position
    public double yyPos;   // current y position
    public double xxVel;   // current velocity in the x direction
    public double yyVel;   // current velocity in the y direction
    public double mass;    // its mass
    public String imgFileName;     // the name of the file that corresponds to the image that dipicits the body
    static final double G = 6.67e-11;
    // constructors
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    
    public double calcDistance(Body b){
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b){
        double F =  G * mass *(b.mass) / Math.pow(calcDistance(b), 2.0);
        return F;
    }

    public double calcForceExertedByX(Body b){
        double Fx = (b.xxPos - xxPos) * this.calcForceExertedBy(b) / calcDistance(b);
        return Fx;
    }
    public double calcForceExertedByY(Body b){
        double Fy = (b.yyPos - yyPos) * this.calcForceExertedBy(b) / calcDistance(b);
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] body_arr){
        double FxNet = 0.0;
        for(int i = 0; i < body_arr.length; i++){
            if(!this.equals(body_arr[i])){
                FxNet += this.calcForceExertedByX(body_arr[i]);
            }
        }
        return FxNet;
    }
    public double calcNetForceExertedByY(Body[] body_arr){
        double FyNet = 0.0;
        for(int i = 0; i < body_arr.length; i++){
            if(!this.equals(body_arr[i])){
                FyNet += this.calcForceExertedByY(body_arr[i]);
            }
        }
        return FyNet;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
}