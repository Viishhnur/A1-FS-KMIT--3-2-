
// this is your abstract factory which creates objects of linux and windows
public interface OSFactory {
    Button createButton();
    Checkbox createCheckBox();
}
