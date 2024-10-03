package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.TagResponse;
import com.example.back_end.entity.Tag;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagImpl implements TagService{
    private final TagRepository tagRepository;
    @Autowired
    public TagImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagResponse> getAlL() {
        List<Tag> tags = tagRepository.findAll();
        if(tags.isEmpty()){
            throw new AppException("tags is not found");
        }
        return tags.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private TagResponse mapToResponse(Tag tag){
        TagResponse response = new TagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());
        return response;
    }
}
