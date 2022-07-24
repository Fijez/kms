package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.entity.Tag;
import com.rtkit.fifth.element.kms.repository.TagRepo;
import com.rtkit.fifth.element.kms.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
class TagServiceImplementation implements TagService {

    private final TagRepo tagRepo;

    @Autowired
    public TagServiceImplementation(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    @Override
    @Transactional
    public String addNewTag(String name) {
        Tag tag = new Tag();
        tag.setTitle(name);
        tagRepo.save(tag);
        return tag.getTitle();
    }
}
