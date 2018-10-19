package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.jnj.adf.grid.data.temporal;

//import com.gemstone.gemfire.DataSerializable;
//import com.gemstone.gemfire.DataSerializer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TemporalRKey implements DataSerializable {
    private static final long serialVersionUID = 514429547219598014L;
    private String uuid;
    private String identityKey;

    public TemporalRKey() {
    }

    public void toData(DataOutput out) throws IOException {
        DataSerializer.writeString(this.uuid, out);
        DataSerializer.writeString(this.identityKey, out);
    }

    public void fromData(DataInput in) throws IOException, ClassNotFoundException {
        this.uuid = DataSerializer.readString(in);
        this.identityKey = DataSerializer.readString(in);
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdentityKey() {
        return this.identityKey;
    }

    public void setIdentityKey(String identityKey) {
        this.identityKey = identityKey;
    }

    public int hashCode() {
        int result = true;
        int result = this.identityKey == null ? 0 : this.identityKey.hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            TemporalRKey other = (TemporalRKey)obj;
            if (this.identityKey == null) {
                if (other.identityKey != null) {
                    return false;
                }
            } else if (!this.identityKey.equals(other.identityKey)) {
                return false;
            }

            if (this.uuid == null) {
                if (other.uuid != null) {
                    return false;
                }
            } else if (!this.uuid.equals(other.uuid)) {
                return false;
            }

            return true;
        }
    }

    public String toString() {
        return "TemporalRKey [uuid=" + this.uuid + ", identityKey=" + this.identityKey + "]";
    }
}
