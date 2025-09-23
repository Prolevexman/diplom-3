package prolevexman.data;

public enum ConstructorTabs {

    BUNS("Булки"),
    SAUCE("Соусы"),
    FILLING("Начинки");

    private final String name;

    ConstructorTabs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
