public class InsufficientFundExecption extends Exception { // this is a checked exception
    
    private final String message;

    public InsufficientFundExecption(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public void printStackTrace(){
        super.printStackTrace();
    }
}
