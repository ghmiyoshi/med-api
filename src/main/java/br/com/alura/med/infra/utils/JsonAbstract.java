package br.com.alura.med.infra.utils;

public abstract class JsonAbstract {

    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }

}
