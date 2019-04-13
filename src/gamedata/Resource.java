package gamedata;

public class Resource {
    public enum ResourceType { IMAGE_RESOURCE, AUDIO_RESOURCE, COLOR_RESOURCE }
    String id, src; // For colour resources, src will just be a hexcode
    ResourceType resourceType;
}
