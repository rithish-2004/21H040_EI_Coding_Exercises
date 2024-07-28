package Exercise1_Design_Patterns.Structural.Composite_Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Playlist extends Component {
    private List<Component> children = new ArrayList<>();
    private int currentIndex = -1;
    private static final Logger logger = Logger.getLogger(Playlist.class.getName());

    public Playlist(String name) {
        super(name);
    }

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    public Component getChild(int index) {
        return children.get(index);
    }

    @Override
    public void play() {
        if (currentIndex >= 0 && currentIndex < children.size()) {
            Component component = children.get(currentIndex);
            component.play();
            logger.info("Currently playing: " + component.getName());
        } else {
            System.out.println("No song to play.");
        }
    }

    public void next() {
        if (children.isEmpty()) {
            System.out.println("No songs in the playlist.");
            return;
        }

        currentIndex = (currentIndex + 1) % children.size();
        play();
    }

    public Playlist findPlaylist(String name) {
        if (this.name.equalsIgnoreCase(name)) {
            return this;
        }

        for (Component component : children) {
            if (component instanceof Playlist) {
                Playlist found = ((Playlist) component).findPlaylist(name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(name).append("\n");
        for (Component component : children) {
            sb.append("  - ").append(component).append("\n");
        }
        return sb.toString();
    }
}
