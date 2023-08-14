import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class main {

    HashMap<String,Integer> UserPassMap = new HashMap<>();
    HashMap<String,Integer> NamePassGroupMap = new HashMap<>();
    static Person currentUser;
    public ArrayList<Person> allPersons = new ArrayList<Person>();
    public ArrayList<Post> allPosts = new ArrayList<>();
    // public ArrayList<group> allGroups = new ArrayList<>();
    public static int helpId = 0;

    public static void main(String[] args) {
        main myInsta = new main();
        myInsta.beginPerson();
    }
    private void beginPerson(){
        Scanner myscanner = new Scanner(System.in);
        while (true){
            System.out.println("Welcome to Instagram\nEnter 1 if you already have an account \nEnter 2 to SignUp\n");
            int loginOrSignUp = myscanner.nextInt();

            if (loginOrSignUp == 1){
                loginPerson();
                break;
            }
            else if (loginOrSignUp == 2){
                signUpPerson();
                break;
            }
            else {
                System.out.println("you should enter a number from 1 to 2");
                continue;
            }
        }
    }
    private void loginPerson(){
        Scanner myscanner = new Scanner(System.in);
        while (true) {
            System.out.println("enter your username.\nEnter <logout> to logout");
            String userName = myscanner.nextLine();
            if(userName.equals("logout")){
                beginPerson();
                break;
            }
            if (UserPassMap.containsKey(userName)){
                System.out.println("Enter your password.");
                int password = myscanner.nextInt();
                if(password == UserPassMap.get(userName)){
                    System.out.println("you loggedIn successfully.");
                    for (Person p:allPersons) {
                        if(p.name.equals(userName)){
                            currentUser = p;
                        }

                    }
                    mainMenu();
                    break;
                }
                else {
                    System.out.println("this password is incorrect. try again.");
                    continue;
                }
            }
            else {
                System.out.println("this UserName is incorrect. try again.");
                continue;
            }
        }
    }
    private void signUpPerson(){
        Scanner myscanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter your username.\nEnter <back> to back.");
            String fullName = myscanner.nextLine();
            if (fullName.equals("back")){
                beginPerson();
                break;
            }
            if (!UserPassMap.containsKey(fullName)){
                System.out.println("Enter a password (contains just numbers)");
                int password = myscanner.nextInt();
                myscanner.nextLine();
                UserPassMap.put(fullName,password);

                System.out.println("Enter a biography you want to show others who see your page.");
                String setBio = myscanner.nextLine();

                Person newPerson = new Person(fullName,setBio);
                allPersons.add(newPerson);
                System.out.println("you signedUp successfully.now you can login.");
                loginPerson();
                break;
            }
            else{
                System.out.println("This name is taken before. choose another name.");
                continue;
            }
        }
    }
    private void mainMenu(){
        while (true){
            System.out.println("Enter the number of what you want. enter 0 to back.\n" +
                    "1- Home page\n" +
                    "2- My page\n" +
                    "3- My chats\n");
            Scanner myscanner = new Scanner(System.in);
            int menoInt = myscanner.nextInt();
            if (menoInt == 0){
                loginPerson();
                break;
            }
            else if (menoInt == 1){
                homePage();
                break;
            }
            else if (menoInt == 2){
                userPage();
                break;
            }
            else if (menoInt == 3){
                userChatsPage();
                break;
            }
            else{
                System.out.println("You should choose a number from 0 to 3");
                continue;
            }
        }
    }
    private void homePage(){
        while (true){
            System.out.println("1- Search for a person page\n" +
                    "2- Like a post\n" +
                    "3- Comment on a post\n" +
                    "0- back");
            showLastPosts();
            Scanner myscanner = new Scanner(System.in);
            int homePageInt = myscanner.nextInt();
            if(homePageInt == 0){
                mainMenu();
                break;
            }
            else if (homePageInt == 1){
                searchForAPersonPage();
                break;
            }
            else if (homePageInt == 2){
                likeAPost();
                break;
            }
            else if (homePageInt == 3){
                commentOnAPost();
                break;
            }
        }
    }
    private void showLastPosts(){
        for (Person p:currentUser.getFollowings()) {
            System.out.println(p.getPeronPosts().get(p.getPeronPosts().size()-1));
        }
    }
    private void searchForAPersonPage(){
        Scanner myscanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter the name of the person you want.\n Enter <back> to back.");
            String nameToSearch = myscanner.nextLine();
            if (nameToSearch.equals("back")){
                homePage();
                break;
            }
            else {
                if(UserPassMap.containsKey(nameToSearch)){
                    System.out.println("user found.");
                    for (Person p:allPersons) {
                        if (p.name.equals(nameToSearch)){
                            System.out.println(p);
                            if(this.isFollowed(p)){
                                System.out.println("----------you already followed this user.----------");
                            }
                            else{
                                System.out.println("Do you want to follow this user?\n" +
                                        "1- Yes    2- No");
                                int wantToFollow = myscanner.nextInt();
                                if(wantToFollow == 1){
                                    followPerson(p);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                else {
                    System.out.println("this name doesn't exist. try again.");
                }
            }
        }
        homePage();
    }
    private boolean isFollowed(Person personToFind){
        for (Person personToSearchInCurrentUserFollowings:currentUser.getFollowings()) {
            if(personToFind.equals(personToSearchInCurrentUserFollowings)){
                return true;
            }
        }
        return false;
    }
    private void followPerson(Person personToFollow){
        if(currentUser.addPersonToFollowings(personToFollow)){
            System.out.println("This person added to your followings successfully.");
            System.out.println(currentUser.getFollowings());
            homePage();
        }
        else {
            System.out.println("something is wrong. person is not added to your followings.");
        }
        homePage();
    }
    private void unfollowPerson(Person personToUnfollow){
        if(currentUser.removePersonFromFollowings(personToUnfollow)){
            System.out.println("This person removed from your followings successfully.");
            System.out.println(currentUser.getFollowings());
            homePage();
        }
        else{
            System.out.println("something is wrong. person is not removed from your followings.");
        }
        homePage();
    }
    private void likeAPost(){
        Scanner myscanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter the id of the post you want to like.");
            int idToLike = myscanner.nextInt();
            Post p = getPostById(idToLike);
            p.addLikeToPost();
            homePage();
            break;
        }

    }
    private void commentOnAPost(){
        Scanner myscanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter the id of the post you want to leave comment on.");
            int idToComment = myscanner.nextInt();
            myscanner.nextLine();
            for (Post p:allPosts) {
                if(Objects.equals(getPostById(idToComment), p)){
                    System.out.println("Enter the text of your comment.");
                    String commentBody = myscanner.nextLine();
                    p.addCommentToPost(p,commentBody,currentUser.getName());
                    System.out.println("comment successfully added.");
                    break;
                }
            }
            homePage();
            break;
        }

    }
    private Post getPostById(int id){
        for (Post p:allPosts) {
            if(id == p.getPostId()){
                return p;
            }
        }
        return null;
    }
    private void userPage(){
        showUserPostsInUserPage(currentUser);
        while (true){
            System.out.println("1- Creat new post\n" +
                    "0- back\n" );
            Scanner myscanner = new Scanner(System.in);
            int userPageInt = myscanner.nextInt();
            if(userPageInt == 0){
                mainMenu();
                break;
            }
            else if(userPageInt == 1){
                creatNewPost();
                showUserPostsInUserPage(currentUser);
                break;
            }
        }
    }
    private void creatNewPost(){
        Scanner myscanner = new Scanner(System.in);
        System.out.println("Enter the name of the post you want to creat.");
        String newPostName = myscanner.nextLine();
        System.out.println("Enter the description of the post that you want to creat.");
        String newPostDescription = myscanner.nextLine();
        Post newPost = new Post(newPostName,newPostDescription);
        currentUser.addPostToUserPosts(newPost);
        allPosts.add(newPost);
        userPage();
    }
    private void showUserPostsInUserPage(Person person){
        person.showAllPostsOfUser();
    }
    private void userChatsPage(){
        showAllUserChatList(currentUser);
        while(true){
            System.out.println("1- Start chat with a person\n" +
                    "2- Creat a group\n" +
                    "3- Join a group\n" +
                    "4- Select a chat\n" +
                    "0- back");
            Scanner myscanner = new Scanner(System.in);
            int userChatsInt = myscanner.nextInt();
            if(userChatsInt == 0){
                mainMenu();
                break;
            }
            else if (userChatsInt == 1){
                startChatWithAPerson();
                break;
            }
            else if (userChatsInt == 2){
                creatGroup();
                break;
            }
            else if (userChatsInt == 3){
                joinGroup();
                break;
            }
            else if (userChatsInt == 4){
                selectChat();
                break;
            }
        }
    }

    private void startChatWithAPerson(){
        Scanner myscanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter the name of the person you want to start chat with. Enter <back> to back.");
            String personNameStartChat = myscanner.nextLine();
            if(personNameStartChat.equals("back")){
                userChatsPage();
                break;
            }
            else {
                if(UserPassMap.containsKey(personNameStartChat)){
                    System.out.println("user found.");
                    for (Person p:allPersons) {
                        if (p.name.equals(personNameStartChat)){
                            System.out.println(p);
                            privateChat newPV = new privateChat(p);
                            showChatText(newPV);
                            writeMessageInChat(newPV);
                        }
                    }

                    break;
                }
                else {
                    System.out.println("this name doesn't exist. try again.");
                }
            }
        }


    }
    private void showChatText(Chat chat){
        chat.showThisChat();
    }
    private void creatGroup(){
        while (true){
            System.out.println("Enter the name of the group you want to creat. Enter <back> to back.\n");
            Scanner myscanner = new Scanner(System.in);
            String groupNameToCreat = myscanner.nextLine();
            if(groupNameToCreat.equals("back")){
                userChatsPage();
                break;
            }
            if(!NamePassGroupMap.containsKey(groupNameToCreat)){
                System.out.println("Enter a password for your group (contains just numbers)");
                int groupPasswordToCreat = myscanner.nextInt();
                myscanner.nextLine();
                UserPassMap.put(groupNameToCreat,groupPasswordToCreat);
                group newGroup = new group(groupNameToCreat);
                newGroup.setAdmin(currentUser);
                newGroup.setId(helpId++);
                allGroups.add(newGroup);
                System.out.println("your group created successfully\n" +
                        ".now you can share the name of group and it's password to others, then they can join.");
                userChatsPage();
                break;
            }
            else{
                System.out.println("This name is taken before. choose another name");
                continue;
            }
        }
    }
    private void joinGroup(){
        while (true){
            Scanner myscanner = new Scanner(System.in);
            System.out.println("Enter the name of the group you want to join. Enter <back> to back.\n");
            String groupNameToJoin = myscanner.nextLine();
            if(groupNameToJoin.equals("back")){
                userChatsPage();
                break;
            }
            if(NamePassGroupMap.containsKey(groupNameToJoin)) {
                System.out.println("Enter the group password.");
                int groupPasswordToJoin = myscanner.nextInt();
                myscanner.nextLine();
                if (groupPasswordToJoin == UserPassMap.get(groupNameToJoin)) {
                    group groupToJoin = getGroupByName(groupNameToJoin);
                    groupToJoin.addMemberToGroup(currentUser);
                    System.out.println("you are joined to the group successfully.");
                    userChatsPage();
                    break;
                }
            }
            else{
                System.out.println("This group name dosn't exist. try again.");
                continue;
            }
        }
    }
    /*private group getGroupByName(String name){
        for (group g:allGroups) {
            if(g.getName().equals(name)){
                return g;
            }
        }
        return null;
    }*/
    private void selectChat(){
        while (true) {
            showAllUserChatList(currentUser);
            System.out.println("Enter the id of the chat you want to see.\n0- back\n");
            Scanner myscanner = new Scanner(System.in);
            int idChatInt = myscanner.nextInt();
            if ( idChatInt == 0){
                userChatsPage();
                break;
            }
            else {
                Chat selectedChat = currentUser.getChatById(idChatInt);
                System.out.println(selectedChat);
                break;
            }
        }
    }
    private void showAllUserChatList(Person person){
        person.showAllUserChats();
    }
    private void writeMessageInChat(Chat chat){
        Scanner myscanner = new Scanner(System.in);
        System.out.println("Write the text you want to send. if you want to stop chating, enter <end>.");
        while (true){
            String chatText = myscanner.nextLine();
            if(chatText.equals("end")){
                userChatsPage();
                break;
            }
            chat.addMessageToChat(chatText);
            showChatText(chat);
        }
    }

}
