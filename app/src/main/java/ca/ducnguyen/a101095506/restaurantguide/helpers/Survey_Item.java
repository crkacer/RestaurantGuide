package ca.ducnguyen.a101095506.restaurantguide.helpers;
public class Survey_Item {

    private String question, answer;

    public Survey_Item(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
