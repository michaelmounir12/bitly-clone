package com.example.bitly_clone.domain.service.mappers;

import com.example.bitly_clone.domain.entities.Urls;
import com.example.bitly_clone.web.models.UrlResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UrlMapper {
    UrlResponse urlToUrlResponse(Urls url);
}

