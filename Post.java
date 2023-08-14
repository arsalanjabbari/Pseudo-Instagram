import java.util.ArrayList;
import java.util.HashMap;

public class Post {
    private final TypeOfMedia type;
    private static int help_id_post = 0;
    private final String name;
    private final String description;
    private int likes_num;
    public ArrayList <Comment> post_comments = new ArrayList<>();
    private final int postId;

    public Post(String name, String description, TypeOfMedia media_type) {
        this.name = name;
        this.description = description;
        this.type = media_type;
        this.postId = help_id_post++;

    }

    public String getName() {
        return name;
    }

    public int getPostId() {
        return postId;
    }

    public void addLikeToPost(){
        likes_num+=1;
        System.out.println("\tPost liked");
    }
    public void addCommentToPost( String body,Person comment_sender){
        Comment new_comment = new Comment(comment_sender,body);
        Media.all_comments.add(new_comment);
        post_comments.add(new_comment);
    }
    @Override
    public String toString() {
        return "Post " +
                "\nid= "+ postId+
                "\n     name= " + name +
                "\n         type= " + type +
                "\n             description= " + description +
                "\n                 like number= "+ likes_num +
                "\n                     comments= "+ post_comments+
                ' ';
    }
}
