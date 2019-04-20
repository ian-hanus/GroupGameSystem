package Engine.src.Components;

public class Aim extends Component{

    double myXAim;
    double myYAim;
    double myRotationRate;

    public Aim( double xAim, double yAim, double rotationRate){
        myXAim = xAim;
        myYAim = yAim;
        myRotationRate = rotationRate;
    }

    double getXAim(){
        return myXAim;
    }

    double getYAim(){
        return myYAim;
    }

}
