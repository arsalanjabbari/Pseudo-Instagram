import java.util.ArrayList;

public  class Chat {

    private int id;
    private ArrayList <String> chatMessages = new ArrayList<>();
    private Person reciever;

    public Chat(Person reciever) {
        this.reciever = reciever;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void addMessageToChat(String messageToAdd){
        chatMessages.add(messageToAdd);
    }
    public void showThisChat(){
        for (String s:chatMessages) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "Chat {" +
                "chat id= "+ id +
                ", chat messages= " + chatMessages +
                '}';
    }
}
