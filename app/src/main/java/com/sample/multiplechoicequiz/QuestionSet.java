package com.sample.multiplechoicequiz;

/**
 * Created by xcode on 2017-04-06.
 */

public class QuestionSet {

    public int id;
    public String question;
    public String op1;
    public String op2;
    public String op3;
    public String op4;
    public String answer;

    public QuestionSet(int id, String question, String op1 ,String op2 ,String op3 ,String op4 ,String answer ) {
        // TODO Auto-generated constructor stub
        this.id = id;
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.answer = answer;

}
    public QuestionSet(){

    }
}
