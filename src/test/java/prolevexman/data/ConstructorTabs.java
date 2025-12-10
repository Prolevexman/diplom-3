package prolevexman.data;

public enum ConstructorTabs {

    SAUCE("Соусы"),
    FILLING("Начинки"),
    BUNS("Булки");

    private final String name;

    ConstructorTabs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
