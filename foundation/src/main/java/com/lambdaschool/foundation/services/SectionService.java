package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Section;

import java.util.List;

public interface SectionService {
    List<Section> findAllSections();

    Section findSectionById(long id);

    Section save(Section section);

    void delete(long id);
}
