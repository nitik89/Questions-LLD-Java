package stackoverflow;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String username;
    private final String email;
    private int reputation;
    private final List<Question>questions;
    private final List<Comment>comments;
    private final List<Answer>answers;
    private static final int QUESTIONS_REPUATION=5;
    private static final int ANSWER_REPUATION=10;
    private static final int COMMENT_REPUATION=2;

    public User(int id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.answers = new ArrayList<>();
    }
    public Question askQuestion(String title ,String content, List<String>tags){
        Question question= new Question(this,title,content,tags);
        questions.add(question);
        updateReputation(QUESTIONS_REPUATION);
        return question;
    }

    public Answer answerQuestion(Question question, String content){
        Answer answer= new Answer(this,question,content);
        answers.add(answer);
        updateReputation(ANSWER_REPUATION);
        return answer;
    }

    public Comment addComment(Commentable commentable,String content){
        Comment comment=new Comment(this,content);
        comments.add(comment);
        commentable.addComment(comment);
        updateReputation(COMMENT_REPUATION);
        return comment;
    }

    public synchronized void updateReputation(int value){
        this.reputation+=value;
        if(this.reputation<0){
            this.reputation=0;
        }
    }

     // Getters
     public int getId() { return id; }
     public String getUsername() { return username; }
     public int getReputation() { return reputation; }
     public List<Question> getQuestions() { return new ArrayList<>(questions); }
     public List<Answer> getAnswers() { return new ArrayList<>(answers); }
     public List<Comment> getComments() { return new ArrayList<>(comments); }

}
