
// import java.awt.Checkbox;

public class LinuxFactory implements OSFactory {
    public Button createButton() {
        return new LinuxButton();
    }

    public Checkbox createCheckBox(){
        return new LinuxCheckBox();
    }
}
