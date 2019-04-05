package game_object.physics_components;

import game_object.environment_components.EnvironmentComponent;

public interface PhysicsComponent {
    void move(EnvironmentComponent component);

    void setVelocity();
    void setVelocityDampener(); //unnecessary with environment component in move???
    void setAcceleration();     //unnecessary with environment component in move???
}
