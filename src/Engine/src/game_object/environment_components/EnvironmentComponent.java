package game_object.environment_components;

import game_object.physics_components.PhysicsComponent;

public interface EnvironmentComponent {
    double getModifiedVelocity(PhysicsComponent component);
    double getModifiedAcceleration(); //????
}
