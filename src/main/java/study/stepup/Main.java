package study.stepup;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("Ivan");
        account.setRest(Currency.RUB, 100);
        account.setRest(Currency.USD, 33);
        account.print();

        // undo
        account.undo();
        account.print();

        // save / load
        Loadable quickSave = account.save();
        account.setName("NoName");
        account.setRest(Currency.RUB, 0);
        account.print();
        quickSave.load();
        account.print();
    }
}