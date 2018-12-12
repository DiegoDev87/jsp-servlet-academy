package dev.webacademy.generico;

import java.io.Serializable;
import java.util.Date;

public abstract class BeanBase implements Serializable {

    private int id;
    private Date dataCadastro;

    public BeanBase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BeanBase other = (BeanBase) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
