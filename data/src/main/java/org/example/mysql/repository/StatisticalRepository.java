package org.example.mysql.repository;

import org.example.dto.StatisticalByAuthor;
import org.example.dto.StatisticalByCharacter;
import org.example.dto.StatisticalByType;

import java.util.List;

public interface StatisticalRepository {
    List<StatisticalByAuthor> getAllByAuthor();
    List<StatisticalByType> getAllByType();
    List<StatisticalByCharacter> getAllByFirstCharacter();
}
