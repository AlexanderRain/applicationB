package com.example.b.activity.domain.repository;

import com.example.b.activity.domain.model.SampleModel;

public interface Repository {

    boolean insert(SampleModel model);

    boolean update(SampleModel model);

    SampleModel get(Object id);

    boolean delete(SampleModel model);
}