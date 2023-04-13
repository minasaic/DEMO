package com.example.backend.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class GenericDomain implements Serializable{
    protected abstract Object keyObject();
    @Override
    public boolean equals(Object object){
        if(object == null || !getClass().equals(object.getClass())){
            return false;
        }
        GenericDomain other = (GenericDomain) object;
        return new EqualsBuilder().append(keyObject(),other.keyObject()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(keyObject()).toHashCode();
    }
}
