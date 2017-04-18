package com.supergeek.pollup.models;

/**
 * Created by Junejas on 4/19/2017.
 */

public class NewPostModel {
    String ques;
    String option1;

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getOp1count() {
        return op1count;
    }

    public void setOp1count(int op1count) {
        this.op1count = op1count;
    }

    public int getOp2count() {
        return op2count;
    }

    public void setOp2count(int op2count) {
        this.op2count = op2count;
    }

    public int getOp3count() {
        return op3count;
    }

    public void setOp3count(int op3count) {
        this.op3count = op3count;
    }

    public int getOp4count() {
        return op4count;
    }

    public void setOp4count(int op4count) {
        this.op4count = op4count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    String option2;
    String option3;
    String option4;
    String author;
    int op1count;
    int op2count;
    int op3count;
    int op4count;
    int id;

    public NewPostModel(String ques, String option1, String option2, String option3, String option4, String author, int op1count, int op2count, int op3count, int op4count, int id, int male, int female) {
        this.ques = ques;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.author = author;
        this.op1count = op1count;
        this.op2count = op2count;
        this.op3count = op3count;
        this.op4count = op4count;
        this.id = id;
        this.male = male;
        this.female = female;
    }

    int male;
    int female;
}
