// this is concreae implementation of absrtact factory
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(OSFactory factory){
        this.button = factory.createButton();
        this.checkbox = factory.createCheckBox();
    }

    public void renderUI(){
        button.render();
        checkbox.render();
    }
}
