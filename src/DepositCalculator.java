import java.util.Scanner;

public class DepositCalculator {

    public static void main(String[] args) {
        new DepositCalculator().calculateDeposit();
    }

    double yearRate = 0.06; // ставка не меняется, поэтому я вынесла переменную, чтобы не дублировать её в методах.

    double calculateComplexPercent(double amount, int depositPeriod) {
        double pay = amount * Math.pow((1 + yearRate / 12), 12 * depositPeriod);
        return roundTheValue(pay);
    }

    double calculateSimplePercent(double amount, int depositPeriod) {
        return roundTheValue(amount + amount * yearRate * depositPeriod);
    }

    double roundTheValue(double value) { //метод округляет
        int power = 2; // places - это степень. Правильнее назвать ее power. Вынесла в отдельную переменную,
                       // т.к. здесь это константа
        double scale = Math.pow(10, power);
        return Math.round(value * scale) / scale;
    }

    void calculateDeposit() {
        int period;
        int depositType;

        Scanner input = new Scanner(System.in);
        System.out.println("Введите сумму вклада в рублях:");
        int amount = input.nextInt();

        System.out.println("Введите срок вклада в годах:");
        period = input.nextInt();

        System.out.println("Выберите тип вклада, 1 - вклад с обычным процентом, 2 - вклад с капитализацией:");
        depositType = input.nextInt();

        double out = 0;

        if (depositType == 1) {
            out = calculateSimplePercent(amount, period);
        } else if (depositType == 2) {
            out = calculateComplexPercent(amount, period);
        }

        System.out.println("Результат вклада: " + amount + " за " + period + " лет превратятся в " + out);
    }
}
