package br.com.alura.med.domain;

import br.com.alura.med.domain.utils.ObjectMapperUtils;

public abstract class JsonAbstract {

    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }

}
