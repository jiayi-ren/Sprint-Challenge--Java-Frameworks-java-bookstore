package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.Section;
import com.lambdaschool.foundation.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "sectionService")
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> findAllSections() {
        List<Section> sectionList = new ArrayList<>();
        sectionRepository.findAll().iterator().forEachRemaining(sectionList::add);
        return sectionList;
    }

    @Override
    public Section findSectionById(long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section id " + id + " Not Found"));
    }

    @Transactional
    @Override
    public Section save(Section section) {
        Section newSection = new Section();

        newSection.setSectionname(section.getSectionname());

        newSection.getBooks().clear();
        for (Book book : section.getBooks()) {
            newSection.getBooks().add(new Book(book.getBooktitle(), book.getISBN(), book.getCopy(), newSection));
        }

        return sectionRepository.save(newSection);
    }

    @Transactional
    @Override
    public void delete(long id) {
        sectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section id " + id + " Not Found"));
        sectionRepository.deleteById(id);
    }
}
