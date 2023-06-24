package br.com.alura.med.domain.utils;

public abstract class JsonAbstract {

    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }

}
