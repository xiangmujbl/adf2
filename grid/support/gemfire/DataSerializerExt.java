/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.gemfire;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.gemstone.gemfire.DataSerializer;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataSerializerExt
/*     */ {
/*     */   static class FieldDefineItem
/*     */     implements DataSerializable
/*     */   {
/*     */     private static final long serialVersionUID = -1529589291704251790L;
/*     */     private String name;
/*     */     private Class type;
/*     */     private PropertyDescriptor field;
/*     */     
/*     */     public FieldDefineItem() {}
/*     */     
/*     */     public FieldDefineItem(PropertyDescriptor f)
/*     */     {
/*  45 */       this.name = f.getName();
/*  46 */       this.type = f.getPropertyType();
/*  47 */       this.field = f;
/*     */     }
/*     */     
/*     */     public String getName()
/*     */     {
/*  52 */       return this.name;
/*     */     }
/*     */     
/*     */     public void setName(String name)
/*     */     {
/*  57 */       this.name = name;
/*     */     }
/*     */     
/*     */     public Class getType()
/*     */     {
/*  62 */       return this.type;
/*     */     }
/*     */     
/*     */     public void setType(Class type)
/*     */     {
/*  67 */       this.type = type;
/*     */     }
/*     */     
/*     */     public PropertyDescriptor getField()
/*     */     {
/*  72 */       return this.field;
/*     */     }
/*     */     
/*     */     public void setField(PropertyDescriptor field)
/*     */     {
/*  77 */       this.field = field;
/*     */     }
/*     */     
/*     */     public void toData(DataOutput out)
/*     */       throws IOException
/*     */     {
/*  83 */       DataSerializer.writeString(this.name, out);
/*  84 */       DataSerializer.writeClass(this.type, out);
/*     */     }
/*     */     
/*     */     public void fromData(DataInput in)
/*     */       throws IOException, ClassNotFoundException
/*     */     {
/*  90 */       this.name = DataSerializer.readString(in);
/*  91 */       this.type = DataSerializer.readClass(in);
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/*  97 */       return "FieldDefineItem [name=" + this.name + ", type=" + this.type + "]";
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 103 */     GridInfo gi = new GridInfo();
/* 104 */     gi.setGridName("dd");
/* 105 */     gi.setConnected(true);
/* 106 */     gi.setDefaultGroup("gddf");
/* 107 */     gi.setJmxAddress("jsaaa");
/* 108 */     gi.setJmxManagerPort(112);
/* 109 */     gi.setJmxPort(123);
/* 110 */     gi.setLocators("locators");
/* 111 */     gi.setMaster(true);
/* 112 */     test(GridInfo.class, gi);
/*     */   }
/*     */   
/*     */   public static final void test(Class<?> classType, Object value)
/*     */   {
/*     */     try
/*     */     {
/* 119 */       ByteArrayOutputStream bo = new ByteArrayOutputStream();
/* 120 */       DataOutput out = new DataOutputStream(bo);
/* 121 */       toData(classType, value, out);
/* 122 */       byte[] sss = bo.toByteArray();
/* 123 */       bo.close();
/* 124 */       ByteArrayInputStream bi = new ByteArrayInputStream(sss);
/* 125 */       DataInput di = new DataInputStream(bi);
/* 126 */       fromData(classType, classType.newInstance(), di);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 131 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static final void toData(Class<?> classType, Object inst, DataOutput out)
/*     */     throws IOException
/*     */   {
/* 138 */     ArrayList<FieldDefineItem> list = new ArrayList();
/*     */     
/* 140 */     PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(classType);
/* 141 */     for (PropertyDescriptor dpt : descriptors)
/*     */     {
/* 143 */       if ((PropertyUtils.isReadable(inst, dpt.getName())) && (PropertyUtils.isWriteable(inst, dpt.getName())))
/*     */       {
/* 145 */         list.add(new FieldDefineItem(dpt));
/*     */       }
/*     */     }
/* 148 */     DataSerializer.writeArrayList(list, out);
/* 149 */     for (??? = list.iterator(); ((Iterator)???).hasNext();) { FieldDefineItem item = (FieldDefineItem)((Iterator)???).next();
/*     */       
/*     */       try
/*     */       {
/* 153 */         writeField(classType, inst, out, item.getField());
/*     */       }
/*     */       catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e)
/*     */       {
/* 157 */         throw new ADFException(e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static final void fromData(Class<?> classType, Object inst, DataInput in) throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 166 */       map = new HashMap();
/* 167 */       PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(classType);
/* 168 */       for (PropertyDescriptor dpt : descriptors)
/*     */       {
/* 170 */         if ((PropertyUtils.isReadable(inst, dpt.getName())) && (PropertyUtils.isWriteable(inst, dpt.getName())))
/*     */         {
/* 172 */           map.put(dpt.getName(), dpt);
/*     */         }
/*     */       }
/*     */       
/* 176 */       Object list = DataSerializer.readArrayList(in);
/* 177 */       for (FieldDefineItem item : (ArrayList)list)
/*     */       {
/* 179 */         PropertyDescriptor f = (PropertyDescriptor)map.get(item.getName());
/* 180 */         Object obj = readField(inst, item.getName(), item.getType(), in);
/* 181 */         if (f != null)
/*     */         {
/* 183 */           PropertyUtils.setProperty(inst, item.getName(), obj);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (ClassNotFoundException|IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
/*     */       Map<String, PropertyDescriptor> map;
/* 189 */       throw new ADFException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final void writeField(Class<?> classType, Object inst, DataOutput out, PropertyDescriptor f)
/*     */     throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
/*     */   {
/* 196 */     Object value = PropertyUtils.getProperty(inst, f.getName());
/* 197 */     String parameterType = f.getPropertyType().getName();
/* 198 */     if (parameterType.equals(String.class.getName())) {
/* 199 */       DataSerializer.writeString((String)value, out);
/* 200 */     } else if (parameterType.equals(Boolean.class.getName())) {
/* 201 */       DataSerializer.writeBoolean((Boolean)value, out);
/* 202 */     } else if (parameterType.equals(Boolean.TYPE.getName())) {
/* 203 */       DataSerializer.writePrimitiveBoolean(((Boolean)value).booleanValue(), out);
/* 204 */     } else if (parameterType.equals(Integer.class.getName())) {
/* 205 */       DataSerializer.writeInteger((Integer)value, out);
/* 206 */     } else if (parameterType.equals(Integer.TYPE.getName())) {
/* 207 */       DataSerializer.writePrimitiveInt(((Integer)value).intValue(), out);
/* 208 */     } else if (parameterType.equals(Double.class.getName())) {
/* 209 */       DataSerializer.writeDouble((Double)value, out);
/* 210 */     } else if (parameterType.equals(Double.TYPE.getName())) {
/* 211 */       DataSerializer.writePrimitiveDouble(((Double)value).doubleValue(), out);
/* 212 */     } else if (parameterType.equals(Long.class.getName())) {
/* 213 */       DataSerializer.writeLong((Long)value, out);
/* 214 */     } else if (parameterType.equals(Long.TYPE.getName())) {
/* 215 */       DataSerializer.writePrimitiveLong(((Long)value).longValue(), out);
/* 216 */     } else if (parameterType.equals(Float.class.getName())) {
/* 217 */       DataSerializer.writeFloat((Float)value, out);
/* 218 */     } else if (parameterType.equals(Float.TYPE.getName())) {
/* 219 */       DataSerializer.writePrimitiveFloat(((Float)value).floatValue(), out);
/* 220 */     } else if (parameterType.equals(Short.class.getName())) {
/* 221 */       DataSerializer.writeShort((Short)value, out);
/* 222 */     } else if (parameterType.equals(Short.TYPE.getName())) {
/* 223 */       DataSerializer.writePrimitiveShort(((Short)value).shortValue(), out);
/* 224 */     } else if (parameterType.equals(Byte.class.getName())) {
/* 225 */       DataSerializer.writeByte((Byte)value, out);
/* 226 */     } else if (parameterType.equals(Byte.TYPE.getName())) {
/* 227 */       DataSerializer.writePrimitiveByte(((Byte)value).byteValue(), out);
/*     */ 
/*     */     }
/* 230 */     else if (f.getPropertyType().isEnum()) {
/* 231 */       DataSerializer.writeEnum((Enum)value, out);
/*     */     } else {
/* 233 */       DataSerializer.writeObject(value, out);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final Object readField(Object inst, String name, Class type, DataInput in)
/*     */     throws IllegalArgumentException, IllegalAccessException, IOException, ClassNotFoundException
/*     */   {
/* 240 */     Object vlaue = null;
/*     */     try
/*     */     {
/* 243 */       String parameterType = type.getName();
/* 244 */       if (parameterType.equals(String.class.getName())) {
/* 245 */         vlaue = DataSerializer.readString(in);
/* 246 */       } else if (parameterType.equals(Boolean.class.getName())) {
/* 247 */         vlaue = DataSerializer.readBoolean(in);
/* 248 */       } else if (parameterType.equals(Boolean.TYPE.getName())) {
/* 249 */         vlaue = Boolean.valueOf(DataSerializer.readPrimitiveBoolean(in));
/* 250 */       } else if (parameterType.equals(Integer.class.getName())) {
/* 251 */         vlaue = DataSerializer.readInteger(in);
/* 252 */       } else if (parameterType.equals(Integer.TYPE.getName())) {
/* 253 */         vlaue = Integer.valueOf(DataSerializer.readPrimitiveInt(in));
/* 254 */       } else if (parameterType.equals(Double.class.getName())) {
/* 255 */         vlaue = DataSerializer.readDouble(in);
/* 256 */       } else if (parameterType.equals(Double.TYPE.getName())) {
/* 257 */         vlaue = Double.valueOf(DataSerializer.readPrimitiveDouble(in));
/* 258 */       } else if (parameterType.equals(Long.class.getName())) {
/* 259 */         vlaue = DataSerializer.readLong(in);
/* 260 */       } else if (parameterType.equals(Long.TYPE.getName())) {
/* 261 */         vlaue = Long.valueOf(DataSerializer.readPrimitiveLong(in));
/* 262 */       } else if (parameterType.equals(Float.class.getName())) {
/* 263 */         vlaue = DataSerializer.readFloat(in);
/* 264 */       } else if (parameterType.equals(Float.TYPE.getName())) {
/* 265 */         vlaue = Float.valueOf(DataSerializer.readPrimitiveFloat(in));
/* 266 */       } else if (parameterType.equals(Short.class.getName())) {
/* 267 */         vlaue = DataSerializer.readShort(in);
/* 268 */       } else if (parameterType.equals(Short.TYPE.getName())) {
/* 269 */         vlaue = Short.valueOf(DataSerializer.readPrimitiveShort(in));
/* 270 */       } else if (parameterType.equals(Byte.class.getName())) {
/* 271 */         vlaue = DataSerializer.readByte(in);
/* 272 */       } else if (parameterType.equals(Byte.TYPE.getName())) {
/* 273 */         vlaue = Byte.valueOf(DataSerializer.readPrimitiveByte(in));
/*     */ 
/*     */       }
/* 276 */       else if (type.isEnum()) {
/* 277 */         vlaue = DataSerializer.readEnum(type, in);
/*     */       }
/*     */       else {
/* 280 */         vlaue = DataSerializer.readObject(in);
/*     */       }
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 285 */       throw new IOException(e);
/*     */     }
/* 287 */     return vlaue;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\gemfire\DataSerializerExt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */