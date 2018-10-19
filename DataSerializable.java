package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.gemstone.gemfire;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

public interface DataSerializable extends Serializable {
    void toData(DataOutput var1) throws IOException;

    void fromData(DataInput var1) throws IOException, ClassNotFoundException;

    public interface Replaceable {
        Object replace() throws IOException;
    }
}
