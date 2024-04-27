package br.com.alura.med.naousar.domain.utils;

public abstract class JsonAbstract {

    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }

}
