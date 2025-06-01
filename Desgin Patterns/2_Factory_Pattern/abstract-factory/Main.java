public class Main{
    public static void main(String[] args) {
        Application app = new Application(new LinuxFactory());
        app.renderUI();
    }
}