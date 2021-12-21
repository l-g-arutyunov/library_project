package ru.diasoft.library_project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private long id;
    private final String name;
    private final Author author;
}
