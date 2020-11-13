public class Task3 {
    public static int findMaxAverageStudentsCount(CashRegister[] cashRegisters) {
        int cashRegisterIntervalsCount = CashRegister.INTERVALS_COUNT;
        double maxAverageStudentsCount = cashRegisters[0].getAverageQueueLength()[0];
        int maxInterval = 1;
        for (int i = 1; i <= cashRegisterIntervalsCount; i++) {
            double averageStudentsCount = 0;
            for (CashRegister cashRegister : cashRegisters) {
                averageStudentsCount += cashRegister.getAverageQueueLength()[i - 1];
            }
            if (maxAverageStudentsCount < averageStudentsCount) {
                maxAverageStudentsCount = averageStudentsCount;
                maxInterval = i;
            }
        }
        return maxInterval;
    }

    public static void main(String[] args) {
        int cashRegistersCount = 5;
        CashRegister[] cashRegisters = new CashRegister[cashRegistersCount];
        for (int i = 0; i < cashRegistersCount; i++) {
            cashRegisters[i] = new CashRegister(args[0], i + 1);
        }

        int maxAverageStudentsCount = findMaxAverageStudentsCount(cashRegisters);
        System.out.println(maxAverageStudentsCount);
    }
}
