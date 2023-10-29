package app.main;
import java.security.SecureRandom;
import java.util.Scanner;

public class ProgramaEstudiante {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom secureRandom = new SecureRandom();
        
        boolean continueProgram = true;
        while (continueProgram) {
            int correctAnswers = 0;
            int totalQuestions = 0;

            System.out.println("¡Bienvenido al programa de aprendizaje de matemáticas!");

            int difficultyLevel = getDifficultyLevel(scanner);
            int problemType = getProblemType(scanner);

            while (totalQuestions < 10) {
                int num1 = generateRandomNumber(secureRandom, difficultyLevel);
                int num2 = generateRandomNumber(secureRandom, difficultyLevel);
                char operator = getOperator(problemType, secureRandom);

                totalQuestions++;
                System.out.print("¿Cuánto es " + num1 + " " + operator + " " + num2 + "? ");
                int answer = scanner.nextInt();

                while (!checkAnswer(num1, num2, operator, answer)) {
                    System.out.println(getRandomNegativeFeedback(secureRandom));
                    System.out.print("Intenta de nuevo: ");
                    answer = scanner.nextInt();
                    totalQuestions++;
                }

                correctAnswers++;
                System.out.println(getRandomPositiveFeedback(secureRandom));
            }

            double percentageCorrect = ((double) correctAnswers / totalQuestions) * 100;
            if (percentageCorrect < 75) {
                System.out.println("Por favor pide ayuda adicional a tu instructor.");
            } else {
                System.out.println("¡Felicidades, estás listo para pasar al siguiente nivel!");
            }
            System.out.println("Has respondido correctamente " + correctAnswers + " preguntas de " + totalQuestions);

            System.out.println("¿Desea permitir que otro estudiante pruebe el programa? (s/n): ");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("s")) {
                continueProgram = false;
            }
        }
        System.out.println("¡Gracias por usar el programa de aprendizaje de matemáticas!");
    }

    public static int getDifficultyLevel(Scanner scanner) {
        System.out.println("Elige un nivel de dificultad (1, 2, 3...): ");
        return scanner.nextInt();
    }

    public static int getProblemType(Scanner scanner) {
        System.out.println("Elige el tipo de problema (1: Suma, 2: Resta, 3: Multiplicación, 4: División, 5: Mezcla aleatoria): ");
        return scanner.nextInt();
    }

    public static int generateRandomNumber(SecureRandom secureRandom, int difficultyLevel) {
        return secureRandom.nextInt((int) Math.pow(10, difficultyLevel));
    }

    public static char getOperator(int problemType, SecureRandom secureRandom) {
        switch (problemType) {
            case 1:
                return '+';
            case 2:
                return '-';
            case 3:
                return '*';
            case 4:
                return '/';
            case 5:
                char[] operators = {'+', '-', '*', '/'};
                return operators[secureRandom.nextInt(4)];
            default:
                throw new IllegalArgumentException("Tipo de problema no válido.");
        }
    }

    public static boolean checkAnswer(int num1, int num2, char operator, int answer) {
        switch (operator) {
            case '+':
                return answer == num1 + num2;
            case '-':
                return answer == num1 - num2;
            case '*':
                return answer == num1 * num2;
            case '/':
                if (num2 == 0) {
                    return false;
                }
                return answer == num1 / num2;
            default:
                return false;
        }
    }

    public static String getRandomPositiveFeedback(SecureRandom secureRandom) {
        String[] positiveFeedback = {"¡Muy bien!", "¡Excelente!", "¡Buen trabajo!", "¡Sigue así!"};
        return positiveFeedback[secureRandom.nextInt(positiveFeedback.length)];
    }

    public static String getRandomNegativeFeedback(SecureRandom secureRandom) {
        String[] negativeFeedback = {"No. Por favor intenta de nuevo.", "Incorrecto. Intenta una vez más.",
                "No te rindas.", "No. Sigue trabajando."};
        return negativeFeedback[secureRandom.nextInt(negativeFeedback.length)];
    }
}	