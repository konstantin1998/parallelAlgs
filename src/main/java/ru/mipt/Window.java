package ru.mipt;

import lombok.Data;

@Data
public class Window {
    private final Node grandParent;
    private final Node parent;
    private final Node current;
}
