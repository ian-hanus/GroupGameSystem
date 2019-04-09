package components;

public class JumpComponent extends Component {
    public double myJumpVelocity;

    public JumpComponent(double jumpVelocity) {
        myJumpVelocity = jumpVelocity;
    }

    public double getJumpVelocity() {
        return myJumpVelocity;
    }
}
