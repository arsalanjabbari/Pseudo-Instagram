import java.util.ArrayList;

public class Person {
    public String name;
    public String bio;
    public ArrayList<Post> person_posts = new ArrayList<>();
    public ArrayList<Person> followers = new ArrayList<>();
    public ArrayList<Person> followings = new ArrayList<>();
    public ArrayList<Chat> all_person_Chats = new ArrayList<>();
    public ArrayList<Person> blocked_users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<Person> getBlockedUsers() {
        return blocked_users;
    }

    public ArrayList<Post> getPeronPosts() {
        return person_posts;
    }

    public Person(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
    public void showAllUserChats(){
        for (Chat c: all_person_Chats) {
            System.out.println("        "+c.getId()+ "-    "+c.getReceiver().name);
        }
    }

    public Chat getChatById(int id_to_get_Chat){
        for (Chat c: all_person_Chats) {
            if(c.getId() == id_to_get_Chat){
                return c;
            }
        }
        return null;
    }

    public boolean addChatToUserChats(Chat Chat_to_add){
        all_person_Chats.add(Chat_to_add);
        return true;
    }
    public boolean addPersonToBlockList(Person person_to_block){
        this.blocked_users.add(person_to_block);
        return true;
    }
    public boolean removePersonFromBlockList(Person person_to_unblock){
        this.blocked_users.remove(person_to_unblock);
        return true;
    }
    public ArrayList<Person> getFollowings() {
        return followings;
    }

    public boolean addPersonToFollowings(Person person_to_add_followings){
        this.followings.add(person_to_add_followings);
        return true;
    }
    public boolean addPersonToFollowers(Person person_to_add_followers){
        this.followers.add(person_to_add_followers);
        return true;
    }
    public boolean removePersonFromFollowers(Person person_to_remove_from_followers){
        this.followers.remove(person_to_remove_from_followers);
        return true;
    }
    public boolean removePersonFromFollowings(Person person_to_remove_from_followings){
        this.followings.remove(person_to_remove_from_followings);
        return true;
    }
    public void addPostToUserPosts(Post newPost){
        person_posts.add(newPost);
    }

    public void showAllPostsOfUser(){
        for (Post p:person_posts) {
            System.out.println(p);
        }
    }


    @Override
    public String toString() {
        return "Person: " +
                "\n     name=  " + name +
                "\n         bio=  " + bio +
                ' ';
    }
}

