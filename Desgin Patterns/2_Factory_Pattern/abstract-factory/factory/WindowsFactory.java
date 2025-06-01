public class WindowsFactory implements OSFactory{
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckBox(){
        return new WindowsCheckBox();
    }
}
