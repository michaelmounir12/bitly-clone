package com.example.bitly_clone.domain.service.mappers;

import com.example.bitly_clone.domain.entities.Clicks;
import com.example.bitly_clone.web.models.ClickResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UrlMapper.class})
public interface ClickMapper {

    @Mapping(target = "url", source = "url")
    ClickResponse toClickResponse(Clicks click);

    List<ClickResponse> toClickResponseList(List<Clicks> clicks);
}