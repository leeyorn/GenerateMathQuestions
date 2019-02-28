package com.liyao.math;

import org.apache.commons.collections4.map.LRUMap;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class MathQuestionMain {


    private static int numMax = 20;

    private static int questionNum = 50;

    //The generated word will have 2 pages for one question set, and should print in both side per paper.
    private static int paperNum = 10;

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        String result;
        int subtrahend;
        int minuend;
        int minuend01;
        String operator01;
        String operator02;

        StringBuilder sb01 = new StringBuilder();
        StringBuilder sb02 = new StringBuilder();


        String output = "Math_Questions_one_operator.docx";
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();

        String output1 = "Math_Questions_two_operator.docx";
        XWPFDocument document1 = new XWPFDocument();
        XWPFParagraph paragraph1 = document1.createParagraph();

        for (int j = 0; j < paperNum; j++) {
            //1 operator questions
            for (int i = 1; i <= questionNum; i++) {
                Generate1OperatorQuestion generate1OperatorQuestion = new Generate1OperatorQuestion(random).invoke();
                subtrahend = generate1OperatorQuestion.getSubtrahend();
                minuend = generate1OperatorQuestion.getMinuend();
                operator01 = generate1OperatorQuestion.getOperator01();
                Map<String, String> a = new LRUMap<>();
                result = String.valueOf(subtrahend) + " " + operator01 + " " + String.valueOf(minuend) + " = ";

                XWPFRun run = paragraph.createRun();
                paragraph.setPageBreak(true);
                run.setText(result);
                run.addBreak(BreakClear.NONE);
                run.setFontSize(21);
                if (i % 25 == 0) {
                    run.addBreak(BreakType.COLUMN);
                }
            }

            //2 operator questions


            for (int i = 1; i <= questionNum; i++) {
                Generate2OperatorQuestion generate2OperatorQuestion = new Generate2OperatorQuestion(random).invoke();
                subtrahend = generate2OperatorQuestion.getSubtrahend();
                minuend = generate2OperatorQuestion.getMinuend();
                minuend01 = generate2OperatorQuestion.getMinuend01();
                operator01 = generate2OperatorQuestion.getOperator01();
                operator02 = generate2OperatorQuestion.getOperator02();

                result = String.valueOf(subtrahend) + " " + operator01 + " " + String.valueOf(minuend)
                        + " " + operator02 + " " + String.valueOf(minuend01) + " = ";

                XWPFRun run = paragraph1.createRun();
                paragraph1.setPageBreak(true);
                run.setText(result);
                run.addBreak(BreakClear.NONE);
                run.setFontSize(21);
                if (i % 25 == 0) {
                    run.addBreak(BreakType.COLUMN);
                }

//                System.out.println(result);
            }

        }

        FileOutputStream out = new FileOutputStream(output);
        document.write(out);
        out.close();
        document.close();
        System.out.println(output + " written successfully");

        FileOutputStream out1 = new FileOutputStream(output1);
        document1.write(out1);
        out1.close();
        document1.close();
        System.out.println(output1 + " written successfully");


    }

    private static void createWordLine(String result, XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setText(result);
        run.addBreak(BreakClear.RIGHT);
        run.setFontSize(22);
    }

    private static void createWordFile(String input) throws IOException {


    }


    private static class Generate1OperatorQuestion {
        private Random random;
        private int subtrahend;
        private int minuend;
        private String operator01;

        public Generate1OperatorQuestion(Random random) {
            this.random = random;
        }

        public int getSubtrahend() {
            return subtrahend;
        }

        public int getMinuend() {
            return minuend;
        }

        public String getOperator01() {
            return operator01;
        }

        public Generate1OperatorQuestion invoke() {
            boolean operatorBoolean;
            int num01;
            int num02;
            int answer;
            operatorBoolean = random.nextBoolean();
            operator01 = operatorBoolean ? "+" : "-";
            num01 = random.nextInt(numMax);
            num02 = random.nextInt(numMax);

            if (!operatorBoolean && num02 >= num01) {
                subtrahend = num02;
                minuend = num01;
            } else {
                subtrahend = num01;
                minuend = num02;
            }

            answer = subtrahend + minuend * (operatorBoolean ? 1 : -1);

            if (answer > numMax || answer < 0 || 0 == subtrahend || minuend == 0) {
                return this.invoke();
            } else {
                return this;
            }

        }
    }

    private static class Generate2OperatorQuestion {
        private Random random;
        private int subtrahend;
        private int minuend;
        private int minuend01;
        private String operator01;
        private String operator02;

        public Generate2OperatorQuestion(Random random) {
            this.random = random;
        }

        public int getSubtrahend() {
            return subtrahend;
        }

        public int getMinuend() {
            return minuend;
        }

        public int getMinuend01() {
            return minuend01;
        }

        public String getOperator01() {
            return operator01;
        }

        public String getOperator02() {
            return operator02;
        }

        public Generate2OperatorQuestion invoke() {
            boolean operator01Boolean;
            boolean operator02Boolean;
            int num01;
            int num02;
            int num03;
            int answer;
            int answerPart1;
            operator01Boolean = random.nextBoolean();
            operator02Boolean = random.nextBoolean();
            operator01 = operator01Boolean ? "+" : "-";
            operator02 = operator02Boolean ? "+" : "-";

            num01 = random.nextInt(numMax);
            num02 = random.nextInt(numMax);
            num03 = random.nextInt(numMax);

            if (!operator01Boolean && num02 >= num01) {
                subtrahend = num02;
                minuend = num01;
            } else {
                subtrahend = num01;
                minuend = num02;
            }

            answerPart1 = subtrahend + minuend * (operator01Boolean ? 1 : -1);

            if (!operator02Boolean && answerPart1 >= num03) {
                return this.invoke();
            } else {
                minuend01 = num03;
            }

            answer = answerPart1 + minuend01 * (operator02Boolean ? 1 : -1);

            if (answer > numMax || answer < 0) {
                return this.invoke();
            } else {
                return this;
            }

        }
    }
}
