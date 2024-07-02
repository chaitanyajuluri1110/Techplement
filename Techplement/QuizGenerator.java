import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class QuizGenerator {
    private static List<Quiz> quiz = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Quiz Generator...");
            System.out.println("1 Create Quiz");
            System.out.println("2 Add question to a Quiz");
            System.out.println("3 Participate in the Quiz");
            System.out.println("4 Exit");
            System.out.println("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    createQuiz();
                    break;
                case 2:
                    addQuestion();
                    break;
                case 3:
                    takeQuiz();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!! Try Again...");
            }
        }
    }

    private static void createQuiz() {
        System.out.println("Enter Quiz Topic");
        String topic = sc.nextLine();
        quiz.add(new Quiz(topic));
        System.out.println("Quiz on topic " + topic + " created.");
    }

    private static void addQuestion() {
        System.out.println("Select a quiz");
        for (int i = 0; i < quiz.size(); i++) {
            System.out.println((i + 1) + ", " + quiz.get(i).getTopic());
        }
        int quizIndex = sc.nextInt() - 1;
        sc.nextLine();
        if (quizIndex < 0 || quizIndex >= quiz.size()) {
            System.out.println("Invalid selection");
            return;
        }
        Quiz selectedQuiz = quiz.get(quizIndex);
        System.out.println("Enter Question: ");
        String questionText = sc.nextLine();
        List<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options.add(sc.nextLine());
        }
        System.out.print("Enter correct answer (1-4): ");
        int correctAnswer = sc.nextInt();
        sc.nextLine();

        Question question = new Question(questionText, options, correctAnswer - 1);
        selectedQuiz.addQuestion(question);
        System.out.println("Question added to quiz on " + selectedQuiz.getTopic());
    }

    private static void takeQuiz() {
        System.out.println("Select a topic to take quiz");
        for (int i = 0; i < quiz.size(); i++) {
            System.out.println((i + 1) + ", " + quiz.get(i).getTopic());
        }
        int quizIndex = sc.nextInt() - 1;
        sc.nextLine();
        if (quizIndex < 0 || quizIndex >= quiz.size()) {
            System.out.println("Invalid selection");
            return;
        }
        Quiz selectedQuiz = quiz.get(quizIndex);
        List<Question> questions = selectedQuiz.getQuestions();
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ", " + options.get(i));
            }
            System.out.println("Your answer: ");
            int userAnswer = sc.nextInt() - 1;
            if (userAnswer == question.getCorrectAnswer()) {
                score++;
            }
        }
        System.out.println("You scored " + score + " out of " + questions.size());
    }
}

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private String topic;
    private List<Question> questions;

    public Quiz(String topic) {
        this.topic = topic;
        this.questions = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
