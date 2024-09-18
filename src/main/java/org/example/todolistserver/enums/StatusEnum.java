package org.example.todolistserver.enums;

public enum StatusEnum {
    NOT_STARTED("Not started", 1),
    IN_PROGRESS("In progress", 2),
    FINISHED("Finished", 3);

    private String name;
    private int index;

    private StatusEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static StatusEnum getEnum(int index){
        for (StatusEnum  status: values()) {
            if(status.getIndex() == index){
                return  status;
            }
        }

        return null;
    }

    public static StatusEnum getEnum(String name){
        for (StatusEnum  status: values()) {
            if(status.getName().equalsIgnoreCase(name)) {
                return  status;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
