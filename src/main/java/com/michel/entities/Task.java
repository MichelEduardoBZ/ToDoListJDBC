package com.michel.entities;

import java.util.Objects;

public class Task {

    private Long id;
    private String name;
    private String desc;

    public Task() {
    }

    public Task(String name, String desc, Long id) {
        this.name = name;
        this.desc = desc;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(getId(), task.getId()) && Objects.equals(getName(), task.getName()) && Objects.equals(getDesc(), task.getDesc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDesc());
    }

    @Override
    public String toString() {
        return "Task: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\n';
    }
}
