package Exercise1_Design_Patterns.Structural.Composite_Pattern;

public class Song extends Component {
    private String artist;

    public Song(String name, String artist) {
        super(name);
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println("Playing song: " + getName() + " by " + artist);
    }

    @Override
    public String toString() {
        return getName() + " by " + artist;
    }
}
