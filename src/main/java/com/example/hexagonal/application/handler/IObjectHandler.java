package com.example.hexagonal.application.handler;

import com.example.hexagonal.application.dto.request.ObjectRequestDto;
import com.example.hexagonal.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {
    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();

}
