package Components;

import java.io.File;

public class ImageFileComponent {
    //private FilePath for set of files to choose from?
    private File myFile;
    private int myZIndex;

    public ImageFileComponent(File file, int zIndex) {
        myFile = file;
        myZIndex = zIndex;
    }
}
