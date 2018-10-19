//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gemstone.gemfire;

import com.gemstone.gemfire.admin.RegionNotFoundException;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.internal.HeapDataOutputStream;
import com.gemstone.gemfire.internal.InternalDataSerializer;
import com.gemstone.gemfire.internal.ObjToByteArraySerializer;
import com.gemstone.gemfire.internal.Version;
import com.gemstone.gemfire.internal.cache.CachedDeserializable;
import com.gemstone.gemfire.internal.cache.EventID;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.internal.cache.tier.sockets.ClientProxyMembershipID;
import com.gemstone.gemfire.internal.i18n.LocalizedStrings;
import com.gemstone.gemfire.internal.logging.LogService;
import com.gemstone.gemfire.internal.logging.log4j.LogMarker;
import com.gemstone.gemfire.pdx.PdxInstance;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.Logger;

public abstract class                     DataSerializer {
    private static final Logger logger = LogService.getLogger();
    private EventID eventId;
    private ClientProxyMembershipID context;
    protected static final boolean TRACE_SERIALIZABLE = Boolean.getBoolean("DataSerializer.TRACE_SERIALIZABLE");
    protected static final ThreadLocal<Boolean> DISALLOW_JAVA_SERIALIZATION = new ThreadLocal();
    private static final ConcurrentMap knownEnums = new ConcurrentHashMap();

    public static void writeClass(Class<?> c, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Class {}", new Object[]{c});
        }

        if (c != null && !c.isPrimitive()) {
            out.writeByte(43);
            writeString(c.getName(), out);
        } else {
            InternalDataSerializer.writePrimitiveClass(c, out);
        }

    }

    public static void writeNonPrimitiveClassName(String className, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Class name {}", new Object[]{className});
        }

        writeString(className, out);
    }

    public static Class<?> readClass(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        byte typeCode = in.readByte();
        if (typeCode == 43) {
            String className = readString(in);
            Class<?> c = InternalDataSerializer.getCachedClass(className);
            return c;
        } else {
            return InternalDataSerializer.decodePrimitiveClass(typeCode);
        }
    }

    public static String readNonPrimitiveClassName(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        return readString(in);
    }

    public static void writeRegion(Region<?, ?> rgn, DataOutput out) throws IOException {
        writeString(rgn != null ? rgn.getFullPath() : null, out);
    }

    public static <K, V> Region<K, V> readRegion(DataInput in) throws IOException, ClassNotFoundException {
        String fullPath = readString(in);
        Region<K, V> rgn = null;
        if (fullPath != null) {
            rgn = GemFireCacheImpl.getExisting("Needed cache to find region.").getRegion(fullPath);
            if (rgn == null) {
                throw new RegionNotFoundException(LocalizedStrings.DataSerializer_REGION_0_COULD_NOT_BE_FOUND_WHILE_READING_A_DATASERIALIZER_STREAM.toLocalizedString(new Object[]{fullPath}));
            }
        }

        return rgn;
    }

    public static void writeDate(Date date, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Date {}", new Object[]{date});
        }

        long v;
        if (date == null) {
            v = -1L;
        } else {
            v = date.getTime();
            if (v == -1L) {
                throw new IllegalArgumentException("Dates whose getTime returns -1 can not be DataSerialized.");
            }
        }

        out.writeLong(v);
    }

    public static Date readDate(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        long time = in.readLong();
        Date date = null;
        if (time != -1L) {
            date = new Date(time);
        }

        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Date {}", new Object[]{date});
        }

        return date;
    }

    public static void writeFile(File file, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing File {}", new Object[]{file});
        }

        writeString(file != null ? file.getCanonicalPath() : null, out);
    }

    public static File readFile(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        String s = readString(in);
        File file = null;
        if (s != null) {
            file = new File(s);
        }

        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read File {}", new Object[]{file});
        }

        return file;
    }

    public static void writeInetAddress(InetAddress address, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing InetAddress {}", new Object[]{address});
        }

        writeByteArray(address != null ? address.getAddress() : null, out);
    }

    public static InetAddress readInetAddress(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        byte[] address = readByteArray(in);
        if (address == null) {
            return null;
        } else {
            try {
                InetAddress addr = InetAddress.getByAddress(address);
                if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                    logger.trace(LogMarker.SERIALIZER, "Read InetAddress {}", new Object[]{addr});
                }

                return addr;
            } catch (UnknownHostException var4) {
                IOException ex2 = new IOException(LocalizedStrings.DataSerializer_WHILE_READING_AN_INETADDRESS.toLocalizedString());
                ex2.initCause(var4);
                throw ex2;
            }
        }
    }

    public static void writeString(String value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        boolean isDebugEnabled = logger.isTraceEnabled(LogMarker.SERIALIZER);
        if (isDebugEnabled) {
            logger.trace(LogMarker.SERIALIZER, "Writing String \"{}\"", new Object[]{value});
        }

        if (value == null) {
            if (isDebugEnabled) {
                logger.trace(LogMarker.SERIALIZER, "Writing NULL_STRING");
            }

            out.writeByte(69);
        } else {
            int len = value.length();
            int utfLen = len;

            for(int i = 0; i < len; ++i) {
                char c = value.charAt(i);
                if (c > 127 || c < 1) {
                    if (c > 2047) {
                        utfLen += 2;
                    } else {
                        ++utfLen;
                    }
                }
            }

            boolean writeUTF = utfLen > len;
            if (writeUTF) {
                if (utfLen > 65535) {
                    if (isDebugEnabled) {
                        logger.trace(LogMarker.SERIALIZER, "Writing utf HUGE_STRING of len={}", new Object[]{len});
                    }

                    out.writeByte(89);
                    out.writeInt(len);
                    out.writeChars(value);
                } else {
                    if (isDebugEnabled) {
                        logger.trace(LogMarker.SERIALIZER, "Writing utf STRING of len={}", new Object[]{len});
                    }

                    out.writeByte(42);
                    out.writeUTF(value);
                }
            } else if (len > 65535) {
                if (isDebugEnabled) {
                    logger.trace(LogMarker.SERIALIZER, "Writing HUGE_STRING_BYTES of len={}", new Object[]{len});
                }

                out.writeByte(88);
                out.writeInt(len);
                out.writeBytes(value);
            } else {
                if (isDebugEnabled) {
                    logger.trace(LogMarker.SERIALIZER, "Writing STRING_BYTES of len={}", new Object[]{len});
                }

                out.writeByte(87);
                out.writeShort(len);
                out.writeBytes(value);
            }
        }

    }

    public static String readString(DataInput in) throws IOException {
        return InternalDataSerializer.readString(in, in.readByte());
    }

    public static void writeBoolean(Boolean value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Boolean {}", new Object[]{value});
        }

        out.writeBoolean(value);
    }

    public static Boolean readBoolean(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Boolean value = in.readBoolean();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Boolean {}", new Object[]{value});
        }

        return value;
    }

    public static void writeCharacter(Character value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Character {}", new Object[]{value});
        }

        out.writeChar(value);
    }

    public static Character readCharacter(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Character value = in.readChar();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Character {}", new Object[]{value});
        }

        return value;
    }

    public static void writeByte(Byte value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Byte {}", new Object[]{value});
        }

        out.writeByte(value);
    }

    public static Byte readByte(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Byte value = in.readByte();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Byte {}", new Object[]{value});
        }

        return value;
    }

    public static void writeShort(Short value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Short {}", new Object[]{value});
        }

        out.writeShort(value);
    }

    public static Short readShort(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Short value = in.readShort();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Short {}", new Object[]{value});
        }

        return value;
    }

    public static void writeInteger(Integer value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Integer {}", new Object[]{value});
        }

        out.writeInt(value);
    }

    public static Integer readInteger(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Integer value = in.readInt();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Integer {}", new Object[]{value});
        }

        return value;
    }

    public static void writeLong(Long value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Long {}", new Object[]{value});
        }

        out.writeLong(value);
    }

    public static Long readLong(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Long value = in.readLong();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Long {}", new Object[]{value});
        }

        return value;
    }

    public static void writeFloat(Float value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Float {}", new Object[]{value});
        }

        out.writeFloat(value);
    }

    public static Float readFloat(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Float value = in.readFloat();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Float {}", new Object[]{value});
        }

        return value;
    }

    public static void writeDouble(Double value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Double {}", new Object[]{value});
        }

        out.writeDouble(value);
    }

    public static Double readDouble(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        Double value = in.readDouble();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Double {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveBoolean(boolean value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Boolean {}", new Object[]{value});
        }

        out.writeBoolean(value);
    }

    public static boolean readPrimitiveBoolean(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        boolean value = in.readBoolean();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Boolean {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveByte(byte value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Byte {}", new Object[]{value});
        }

        out.writeByte(value);
    }

    public static byte readPrimitiveByte(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        byte value = in.readByte();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Byte {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveChar(char value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Char {}", new Object[]{value});
        }

        out.writeChar(value);
    }

    public static char readPrimitiveChar(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        char value = in.readChar();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Char {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveShort(short value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Short {}", new Object[]{value});
        }

        out.writeShort(value);
    }

    public static short readPrimitiveShort(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        short value = in.readShort();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Short {}", new Object[]{value});
        }

        return value;
    }

    public static void writeUnsignedByte(int value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Unsigned Byte {}", new Object[]{value});
        }

        out.writeByte(value);
    }

    public static int readUnsignedByte(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int value = in.readUnsignedByte();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Unsigned Byte {}", new Object[]{value});
        }

        return value;
    }

    public static void writeUnsignedShort(int value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Unsigned Short {}", new Object[]{value});
        }

        out.writeShort(value);
    }

    public static int readUnsignedShort(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int value = in.readUnsignedShort();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Unsigned Short {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveInt(int value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Integer {}", new Object[]{value});
        }

        out.writeInt(value);
    }

    public static int readPrimitiveInt(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int value = in.readInt();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Integer {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveLong(long value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Long {}", new Object[]{value});
        }

        out.writeLong(value);
    }

    public static long readPrimitiveLong(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        long value = in.readLong();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Long {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveFloat(float value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Float {}", new Object[]{value});
        }

        out.writeFloat(value);
    }

    public static float readPrimitiveFloat(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        float value = in.readFloat();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Float {}", new Object[]{value});
        }

        return value;
    }

    public static void writePrimitiveDouble(double value, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Double {}", new Object[]{value});
        }

        out.writeDouble(value);
    }

    public static double readPrimitiveDouble(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        double value = in.readDouble();
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Read Double {}", new Object[]{value});
        }

        return value;
    }

    public static void writeByteArray(byte[] array, DataOutput out) throws IOException {
        int len = 0;
        if (array != null) {
            len = array.length;
        }

        writeByteArray(array, len, out);
    }

    public static void writeByteArray(byte[] array, int len, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length = len;
        if (array == null) {
            length = -1;
        } else if (len > array.length) {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing byte array of length {}", new Object[]{length});
        }

        if (length > 0) {
            out.write(array, 0, length);
        }

    }

    public static void writeObjectAsByteArray(Object obj, DataOutput out) throws IOException {
        Object object = obj;
        if (obj instanceof CachedDeserializable) {
            object = ((CachedDeserializable)obj).getSerializedValue();
        }

        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            if (object == null) {
                logger.trace(LogMarker.SERIALIZER, "writeObjectAsByteArray null");
            } else {
                logger.trace(LogMarker.SERIALIZER, "writeObjectAsByteArray obj.getClass={}", new Object[]{object.getClass()});
            }
        }

        if (!(object instanceof byte[]) && object != null) {
            if (out instanceof ObjToByteArraySerializer) {
                ((ObjToByteArraySerializer)out).writeAsSerializedByteArray(object);
            } else {
                HeapDataOutputStream hdos;
                if (object instanceof HeapDataOutputStream) {
                    hdos = (HeapDataOutputStream)object;
                } else {
                    Version v = InternalDataSerializer.getVersionForDataStreamOrNull(out);
                    if (v == null) {
                        v = Version.CURRENT;
                    }

                    hdos = new HeapDataOutputStream(v);

                    try {
                        writeObject(object, hdos);
                    } catch (IOException var7) {
                        RuntimeException e2 = new IllegalArgumentException(LocalizedStrings.DataSerializer_PROBELM_WHILE_SERIALIZING.toLocalizedString());
                        e2.initCause(var7);
                        throw e2;
                    }
                }

                InternalDataSerializer.writeArrayLength(hdos.size(), out);
                hdos.sendTo(out);
            }
        } else {
            writeByteArray((byte[])((byte[])object), out);
        }

    }

    public static byte[] readByteArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            byte[] array = new byte[length];
            in.readFully(array, 0, length);
            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read byte array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeStringArray(String[] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing String array of length {}", new Object[]{length});
        }

        if (length > 0) {
            for(int i = 0; i < length; ++i) {
                writeString(array[i], out);
            }
        }

    }

    public static String[] readStringArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            String[] array = new String[length];

            for(int i = 0; i < length; ++i) {
                array[i] = readString(in);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read String array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeShortArray(short[] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing short array of length {}", new Object[]{length});
        }

        if (length > 0) {
            for(int i = 0; i < length; ++i) {
                out.writeShort(array[i]);
            }
        }

    }

    public static short[] readShortArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            short[] array = new short[length];

            for(int i = 0; i < length; ++i) {
                array[i] = in.readShort();
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read short array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeCharArray(char[] array, DataOutput out) throws IOException {
        InternalDataSerializer.writeCharArray(array, array != null ? array.length : -1, out);
    }

    public static char[] readCharArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            char[] array = new char[length];

            for(int i = 0; i < length; ++i) {
                array[i] = in.readChar();
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read char array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeBooleanArray(boolean[] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing boolean array of length {}", new Object[]{length});
        }

        if (length > 0) {
            for(int i = 0; i < length; ++i) {
                out.writeBoolean(array[i]);
            }
        }

    }

    public static boolean[] readBooleanArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            boolean[] array = new boolean[length];

            for(int i = 0; i < length; ++i) {
                array[i] = in.readBoolean();
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read boolean array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeIntArray(int[] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing int array of length {}", new Object[]{length});
        }

        if (length > 0) {
            for(int i = 0; i < length; ++i) {
                out.writeInt(array[i]);
            }
        }

    }

    public static int[] readIntArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            int[] array = new int[length];

            for(int i = 0; i < length; ++i) {
                array[i] = in.readInt();
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read int array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeLongArray(long[] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing long array of length {}", new Object[]{length});
        }

        if (length > 0) {
            for(int i = 0; i < length; ++i) {
                out.writeLong(array[i]);
            }
        }

    }

    public static long[] readLongArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            long[] array = new long[length];

            for(int i = 0; i < length; ++i) {
                array[i] = in.readLong();
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read long array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeFloatArray(float[] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing float array of length {}", new Object[]{length});
        }

        if (length > 0) {
            for(int i = 0; i < length; ++i) {
                out.writeFloat(array[i]);
            }
        }

    }

    public static float[] readFloatArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            float[] array = new float[length];

            for(int i = 0; i < length; ++i) {
                array[i] = in.readFloat();
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read float array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeDoubleArray(double[] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing double array of length {}", new Object[]{length});
        }

        if (length > 0) {
            for(int i = 0; i < length; ++i) {
                out.writeDouble(array[i]);
            }
        }

    }

    public static double[] readDoubleArray(DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            double[] array = new double[length];

            for(int i = 0; i < length; ++i) {
                array[i] = in.readDouble();
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read double array of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeObjectArray(Object[] array, DataOutput out) throws IOException {
        InternalDataSerializer.writeObjectArray(array, out, false);
    }

    public static Object[] readObjectArray(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return null;
        } else {
            Class<?> c = null;
            byte typeCode = in.readByte();
            String typeString = null;
            if (typeCode == 43) {
                typeString = readString(in);
            }

            GemFireCacheImpl cache = GemFireCacheImpl.getInstance();
            boolean lookForPdxInstance = false;
            ClassNotFoundException cnfEx = null;
            if (typeCode == 43 && cache != null && cache.getPdxReadSerializedByAnyGemFireServices()) {
                try {
                    c = InternalDataSerializer.getCachedClass(typeString);
                    lookForPdxInstance = true;
                } catch (ClassNotFoundException var12) {
                    c = Object.class;
                    cnfEx = var12;
                }
            } else if (typeCode == 43) {
                c = InternalDataSerializer.getCachedClass(typeString);
            } else {
                c = InternalDataSerializer.decodePrimitiveClass(typeCode);
            }

            Object o = null;
            if (length > 0) {
                o = readObject(in);
                if (lookForPdxInstance && o instanceof PdxInstance) {
                    lookForPdxInstance = false;
                    c = Object.class;
                }
            }

            Object[] array = (Object[])((Object[])Array.newInstance(c, length));
            if (length > 0) {
                array[0] = o;
            }

            for(int i = 1; i < length; ++i) {
                o = readObject(in);
                if (lookForPdxInstance && o instanceof PdxInstance) {
                    lookForPdxInstance = false;
                    c = Object.class;
                    Object[] newArray = (Object[])((Object[])Array.newInstance(c, length));
                    System.arraycopy(array, 0, newArray, 0, i);
                    array = newArray;
                }

                array[i] = o;
            }

            if (lookForPdxInstance && cnfEx != null && length > 0) {
                throw cnfEx;
            } else {
                if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                    logger.trace(LogMarker.SERIALIZER, "Read Object array of length {}", new Object[]{length});
                }

                return array;
            }
        }
    }

    public static void writeArrayOfByteArrays(byte[][] array, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int length;
        if (array == null) {
            length = -1;
        } else {
            length = array.length;
        }

        InternalDataSerializer.writeArrayLength(length, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing byte[][] of length {}", new Object[]{length});
        }

        if (length >= 0) {
            for(int i = 0; i < length; ++i) {
                writeByteArray(array[i], out);
            }
        }

    }

    public static byte[][] readArrayOfByteArrays(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int length = InternalDataSerializer.readArrayLength(in);
        if (length == -1) {
            return (byte[][])null;
        } else {
            byte[][] array = new byte[length][];

            for(int i = 0; i < length; ++i) {
                array[i] = readByteArray(in);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read byte[][] of length {}", new Object[]{length});
            }

            return array;
        }
    }

    public static void writeArrayList(ArrayList<?> list, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (list == null) {
            size = -1;
        } else {
            size = list.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing ArrayList with {} elements: {}", new Object[]{size, list});
        }

        if (size > 0) {
            for(int i = 0; i < size; ++i) {
                writeObject(list.get(i), out);
            }
        }

    }

    public static <E> ArrayList<E> readArrayList(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            ArrayList<E> list = new ArrayList(size);

            for(int i = 0; i < size; ++i) {
                E element = readObject(in);
                list.add(element);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read ArrayList with {} elements: {}", new Object[]{size, list});
            }

            return list;
        }
    }

    public static void writeVector(Vector<?> list, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (list == null) {
            size = -1;
        } else {
            size = list.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Vector with {} elements: {}", new Object[]{size, list});
        }

        if (size > 0) {
            for(int i = 0; i < size; ++i) {
                writeObject(list.get(i), out);
            }
        }

    }

    public static <E> Vector<E> readVector(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            Vector<E> list = new Vector(size);

            for(int i = 0; i < size; ++i) {
                E element = readObject(in);
                list.add(element);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read Vector with {} elements: {}", new Object[]{size, list});
            }

            return list;
        }
    }

    public static void writeStack(Stack<?> list, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (list == null) {
            size = -1;
        } else {
            size = list.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Stack with {} elements: {}", new Object[]{size, list});
        }

        if (size > 0) {
            for(int i = 0; i < size; ++i) {
                writeObject(list.get(i), out);
            }
        }

    }

    public static <E> Stack<E> readStack(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            Stack<E> list = new Stack();

            for(int i = 0; i < size; ++i) {
                E element = readObject(in);
                list.add(element);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read Stack with {} elements: {}", new Object[]{size, list});
            }

            return list;
        }
    }

    public static void writeLinkedList(LinkedList<?> list, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (list == null) {
            size = -1;
        } else {
            size = list.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing LinkedList with {} elements: {}", new Object[]{size, list});
        }

        if (size > 0) {
            Iterator i$ = list.iterator();

            while(i$.hasNext()) {
                Object e = i$.next();
                writeObject(e, out);
            }
        }

    }

    public static <E> LinkedList<E> readLinkedList(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            LinkedList<E> list = new LinkedList();

            for(int i = 0; i < size; ++i) {
                E element = readObject(in);
                list.add(element);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read LinkedList with {} elements: {}", new Object[]{size, list});
            }

            return list;
        }
    }

    public static void writeHashSet(HashSet<?> set, DataOutput out) throws IOException {
        InternalDataSerializer.writeSet(set, out);
    }

    public static <E> HashSet<E> readHashSet(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            HashSet<E> set = new HashSet(size);

            for(int i = 0; i < size; ++i) {
                E element = readObject(in);
                set.add(element);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read HashSet with {} elements: {}", new Object[]{size, set});
            }

            return set;
        }
    }

    public static void writeLinkedHashSet(LinkedHashSet<?> set, DataOutput out) throws IOException {
        InternalDataSerializer.writeSet(set, out);
    }

    public static <E> LinkedHashSet<E> readLinkedHashSet(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            LinkedHashSet<E> set = new LinkedHashSet(size);

            for(int i = 0; i < size; ++i) {
                E element = readObject(in);
                set.add(element);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read LinkedHashSet with {} elements: {}", new Object[]{size, set});
            }

            return set;
        }
    }

    public static void writeHashMap(HashMap<?, ?> map, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (map == null) {
            size = -1;
        } else {
            size = map.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing HashMap with {} elements: {}", new Object[]{size, map});
        }

        if (size > 0) {
            Iterator i$ = map.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<?, ?> entry = (Entry)i$.next();
                writeObject(entry.getKey(), out);
                writeObject(entry.getValue(), out);
            }
        }

    }

    public static <K, V> HashMap<K, V> readHashMap(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            HashMap<K, V> map = new HashMap(size);

            for(int i = 0; i < size; ++i) {
                K key = readObject(in);
                V value = readObject(in);
                map.put(key, value);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read HashMap with {} elements: {}", new Object[]{size, map});
            }

            return map;
        }
    }

    public static void writeIdentityHashMap(IdentityHashMap<?, ?> map, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (map == null) {
            size = -1;
        } else {
            size = map.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing IdentityHashMap with {} elements: {}", new Object[]{size, map});
        }

        if (size > 0) {
            Iterator i$ = map.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<?, ?> entry = (Entry)i$.next();
                writeObject(entry.getKey(), out);
                writeObject(entry.getValue(), out);
            }
        }

    }

    public static <K, V> IdentityHashMap<K, V> readIdentityHashMap(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            IdentityHashMap<K, V> map = new IdentityHashMap(size);

            for(int i = 0; i < size; ++i) {
                K key = readObject(in);
                V value = readObject(in);
                map.put(key, value);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read IdentityHashMap with {} elements: {}", new Object[]{size, map});
            }

            return map;
        }
    }

    public static void writeConcurrentHashMap(ConcurrentHashMap<?, ?> map, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        Collection<Entry<?, ?>> entrySnapshot = null;
        int size;
        if (map == null) {
            size = -1;
        } else {
            entrySnapshot = new ArrayList(map.entrySet());
            size = entrySnapshot.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing ConcurrentHashMap with {} elements: {}", new Object[]{size, entrySnapshot});
        }

        if (size > 0) {
            Iterator i$ = entrySnapshot.iterator();

            while(i$.hasNext()) {
                Entry<?, ?> entry = (Entry)i$.next();
                writeObject(entry.getKey(), out);
                writeObject(entry.getValue(), out);
            }
        }

    }

    public static <K, V> ConcurrentHashMap<K, V> readConcurrentHashMap(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            ConcurrentHashMap<K, V> map = new ConcurrentHashMap(size);

            for(int i = 0; i < size; ++i) {
                K key = readObject(in);
                V value = readObject(in);
                map.put(key, value);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read ConcurrentHashMap with {} elements: {}", new Object[]{size, map});
            }

            return map;
        }
    }

    public static void writeHashtable(Hashtable<?, ?> map, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (map == null) {
            size = -1;
        } else {
            size = map.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Hashtable with {} elements: {}", new Object[]{size, map});
        }

        if (size > 0) {
            Iterator i$ = map.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<?, ?> entry = (Entry)i$.next();
                writeObject(entry.getKey(), out);
                writeObject(entry.getValue(), out);
            }
        }

    }

    public static <K, V> Hashtable<K, V> readHashtable(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            Hashtable<K, V> map = new Hashtable(size);

            for(int i = 0; i < size; ++i) {
                K key = readObject(in);
                V value = readObject(in);
                map.put(key, value);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read Hashtable with {} elements: {}", new Object[]{size, map});
            }

            return map;
        }
    }

    public static void writeTreeMap(TreeMap<?, ?> map, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (map == null) {
            size = -1;
        } else {
            size = map.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing TreeMap with {} elements: {}", new Object[]{size, map});
        }

        if (size >= 0) {
            writeObject(map.comparator(), out);
            Iterator i$ = map.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<?, ?> entry = (Entry)i$.next();
                writeObject(entry.getKey(), out);
                writeObject(entry.getValue(), out);
            }
        }

    }

    public static <K, V> TreeMap<K, V> readTreeMap(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            Comparator<? super K> c = (Comparator)InternalDataSerializer.readNonPdxInstanceObject(in);
            TreeMap<K, V> map = new TreeMap(c);

            for(int i = 0; i < size; ++i) {
                K key = readObject(in);
                V value = readObject(in);
                map.put(key, value);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read TreeMap with {} elements: {}", new Object[]{size, map});
            }

            return map;
        }
    }

    public static void writeTreeSet(TreeSet<?> set, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        int size;
        if (set == null) {
            size = -1;
        } else {
            size = set.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing TreeSet with {} elements: {}", new Object[]{size, set});
        }

        if (size >= 0) {
            writeObject(set.comparator(), out);
            Iterator i$ = set.iterator();

            while(i$.hasNext()) {
                Object e = i$.next();
                writeObject(e, out);
            }
        }

    }

    public static <E> TreeSet<E> readTreeSet(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            Comparator<? super E> c = (Comparator)InternalDataSerializer.readNonPdxInstanceObject(in);
            TreeSet<E> set = new TreeSet(c);

            for(int i = 0; i < size; ++i) {
                E element = readObject(in);
                set.add(element);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read TreeSet with {} elements: {}", new Object[]{size, set});
            }

            return set;
        }
    }

    public static void writeProperties(Properties props, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        Set s;
        int size;
        if (props == null) {
            s = null;
            size = -1;
        } else {
            s = props.entrySet();
            size = s.size();
        }

        InternalDataSerializer.writeArrayLength(size, out);
        if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
            logger.trace(LogMarker.SERIALIZER, "Writing Properties with {} elements: {}", new Object[]{size, props});
        }

        if (size > 0) {
            Iterator i$ = s.iterator();

            while(i$.hasNext()) {
                Entry<Object, Object> entry = (Entry)i$.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                writeObject(key, out);
                writeObject(val, out);
            }
        }

    }

    public static Properties readProperties(DataInput in) throws IOException, ClassNotFoundException {
        InternalDataSerializer.checkIn(in);
        int size = InternalDataSerializer.readArrayLength(in);
        if (size == -1) {
            return null;
        } else {
            Properties props = new Properties();

            for(int index = 0; index < size; ++index) {
                Object key = readObject(in);
                Object value = readObject(in);
                props.put(key, value);
            }

            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Read Properties with {} elements: {}", new Object[]{size, props});
            }

            return props;
        }
    }

    public static final void writeObject(Object o, DataOutput out, boolean allowJavaSerialization) throws IOException {
        if (allowJavaSerialization) {
            writeObject(o, out);
        } else {
            DISALLOW_JAVA_SERIALIZATION.set(Boolean.TRUE);

            try {
                writeObject(o, out);
            } finally {
                DISALLOW_JAVA_SERIALIZATION.set(Boolean.FALSE);
            }

        }
    }

    public static final void writeObject(Object o, DataOutput out) throws IOException {
        InternalDataSerializer.basicWriteObject(o, out, false);
    }

    public static final <T> T readObject(DataInput in) throws IOException, ClassNotFoundException {
        return InternalDataSerializer.basicReadObject(in);
    }

    public static final DataSerializer register(Class<?> c) {
        return InternalDataSerializer.register(c, true);
    }

    /** @deprecated */
    @Deprecated
    public static final DataSerializer register(Class<?> c, byte b) {
        return register(c);
    }

    public DataSerializer() {
    }

    public abstract Class<?>[] getSupportedClasses();

    public abstract boolean toData(Object var1, DataOutput var2) throws IOException;

    public abstract Object fromData(DataInput var1) throws IOException, ClassNotFoundException;

    public abstract int getId();

    public boolean equals(Object o) {
        if (!(o instanceof DataSerializer)) {
            return false;
        } else {
            DataSerializer oDS = (DataSerializer)o;
            return oDS.getId() == this.getId() && this.getClass().equals(oDS.getClass());
        }
    }

    public int hashCode() {
        return this.getId();
    }

    public final void setEventId(Object eventId) {
        this.eventId = (EventID)eventId;
    }

    public final Object getEventId() {
        return this.eventId;
    }

    public final void setContext(Object context) {
        this.context = (ClientProxyMembershipID)context;
    }

    public final Object getContext() {
        return this.context;
    }

    private static <E extends Enum> E[] getEnumConstantsForClass(Class<E> clazz) {
        E[] returnVal = (Enum[])((Enum[])knownEnums.get(clazz));
        if (returnVal == null) {
            returnVal = (Enum[])clazz.getEnumConstants();
            knownEnums.put(clazz, returnVal);
        }

        return returnVal;
    }

    public static void writeEnum(Enum e, DataOutput out) throws IOException {
        InternalDataSerializer.checkOut(out);
        if (e == null) {
            throw new NullPointerException(LocalizedStrings.DataSerializer_ENUM_TO_SERIALIZE_IS_NULL.toLocalizedString());
        } else {
            if (logger.isTraceEnabled(LogMarker.SERIALIZER)) {
                logger.trace(LogMarker.SERIALIZER, "Writing enum {}", new Object[]{e});
            }

            InternalDataSerializer.writeArrayLength(e.ordinal(), out);
        }
    }

    public static <E extends Enum<E>> E readEnum(Class<E> clazz, DataInput in) throws IOException {
        InternalDataSerializer.checkIn(in);
        if (clazz == null) {
            throw new NullPointerException(LocalizedStrings.DataSerializer_ENUM_CLASS_TO_DESERIALIZE_IS_NULL.toLocalizedString());
        } else if (!clazz.isEnum()) {
            throw new IllegalArgumentException(LocalizedStrings.DataSerializer_CLASS_0_NOT_ENUM.toLocalizedString(new Object[]{clazz.getName()}));
        } else {
            int ordinal = InternalDataSerializer.readArrayLength(in);
            return getEnumConstantsForClass(clazz)[ordinal];
        }
    }
}
