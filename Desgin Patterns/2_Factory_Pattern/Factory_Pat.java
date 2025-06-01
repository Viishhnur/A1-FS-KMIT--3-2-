interface Notification {
    void notifyUser();
}

class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a email notification");
    }
}

class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a SMS notification");
    }
}

class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a push notification");
    }
}

class NotificationFactory {
    // it is responsible for creating objects of notifications based on some
    // conditions
    public static Notification createNotification(String notificationType){
        if(notificationType == null || notificationType.isEmpty()){
            return null;
        }

        switch(notificationType.toLowerCase()){
            case "email" -> { return new EmailNotification(); }
            case "sms" -> { return new SMSNotification(); }
            case "push" -> { return new PushNotification(); }
            default -> throw new UnsupportedOperationException("This type of notification is not yet supported");
        }
    }
}



public class Factory_Pat {
    public static void main(String[] args) {
        Notification not1 = NotificationFactory.createNotification("Email");
        not1.notifyUser();

        Notification not2 = NotificationFactory.createNotification("sms");
        not2.notifyUser();


        Notification not3 = NotificationFactory.createNotification("whatsapp");
        not3.notifyUser();

    }
}
