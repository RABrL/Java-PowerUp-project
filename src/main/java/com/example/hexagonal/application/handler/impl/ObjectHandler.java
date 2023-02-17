package com.example.hexagonal.application.handler.impl;

import com.example.hexagonal.application.dto.request.ObjectRequestDto;
import com.example.hexagonal.application.dto.response.ObjectResponseDto;
import com.example.hexagonal.application.handler.IObjectHandler;
import com.example.hexagonal.application.mapper.IObjectRequestMapper;
import com.example.hexagonal.application.mapper.IObjectResponseMapper;
import com.example.hexagonal.domain.api.IObjectServicePort;
import com.example.hexagonal.domain.model.ObjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ObjectHandler implements IObjectHandler {
    private final IObjectServicePort objectServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveObject(ObjectRequestDto objectRequestDto) {
        ObjectModel objectModel = objectRequestMapper.toObject(objectRequestDto);
        objectServicePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectResponseDto> getAllObjects() {
        return objectResponseMapper.toResponseList((objectServicePort.getAllObjects()));
    }
}
