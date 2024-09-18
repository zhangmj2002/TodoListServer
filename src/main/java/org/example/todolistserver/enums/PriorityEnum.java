package org.example.todolistserver.enums;

public enum PriorityEnum {
    LOW("Low", 1),
    MEDIUM("Medium", 2),
    HIGH("High", 3);

    private String name;
    private int index;

    private PriorityEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static PriorityEnum getEnum(int index){
        for (PriorityEnum  priority: values()) {
            if(priority.getIndex() == index){
                return  priority;
            }
        }

        return null;
    }

    public static PriorityEnum getEnum(String name){
        for (PriorityEnum  priority: values()) {
            if(priority.getName().equalsIgnoreCase(name)) {
                return  priority;
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
