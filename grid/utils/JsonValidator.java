/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.CharacterIterator;
/*     */ import java.text.StringCharacterIterator;
/*     */ 
/*     */ 
/*     */ public class JsonValidator
/*     */ {
/*     */   private CharacterIterator it;
/*     */   private char c;
/*     */   private int col;
/*     */   
/*     */   public boolean validate(String input)
/*     */   {
/*  16 */     input = input.trim();
/*  17 */     boolean ret = valid(input);
/*  18 */     return ret;
/*     */   }
/*     */   
/*     */   private boolean valid(String input) {
/*  22 */     if ("".equals(input)) { return true;
/*     */     }
/*  24 */     boolean ret = true;
/*  25 */     this.it = new StringCharacterIterator(input);
/*  26 */     this.c = this.it.first();
/*  27 */     this.col = 1;
/*  28 */     if (!value()) {
/*  29 */       ret = error("value", 1);
/*     */     } else {
/*  31 */       skipWhiteSpace();
/*  32 */       if (this.c != 65535) {
/*  33 */         ret = error("end", this.col);
/*     */       }
/*     */     }
/*     */     
/*  37 */     return ret;
/*     */   }
/*     */   
/*     */   private boolean value() {
/*  41 */     return (literal("true")) || (literal("false")) || (literal("null")) || (string()) || (number()) || (object()) || (array());
/*     */   }
/*     */   
/*     */   private boolean literal(String text) {
/*  45 */     CharacterIterator ci = new StringCharacterIterator(text);
/*  46 */     char t = ci.first();
/*  47 */     if (this.c != t) { return false;
/*     */     }
/*  49 */     int start = this.col;
/*  50 */     boolean ret = true;
/*  51 */     for (t = ci.next(); t != 65535; t = ci.next()) {
/*  52 */       if (t != nextCharacter()) {
/*  53 */         ret = false;
/*  54 */         break;
/*     */       }
/*     */     }
/*  57 */     nextCharacter();
/*  58 */     if (!ret) error("literal " + text, start);
/*  59 */     return ret;
/*     */   }
/*     */   
/*     */   private boolean array() {
/*  63 */     return aggregate('[', ']', false);
/*     */   }
/*     */   
/*     */   private boolean object() {
/*  67 */     return aggregate('{', '}', true);
/*     */   }
/*     */   
/*     */   private boolean aggregate(char entryCharacter, char exitCharacter, boolean prefix) {
/*  71 */     if (this.c != entryCharacter) return false;
/*  72 */     nextCharacter();
/*  73 */     skipWhiteSpace();
/*  74 */     if (this.c == exitCharacter) {
/*  75 */       nextCharacter();
/*  76 */       return true;
/*     */     }
/*     */     for (;;)
/*     */     {
/*  80 */       if (prefix) {
/*  81 */         int start = this.col;
/*  82 */         if (!string()) return error("string", start);
/*  83 */         skipWhiteSpace();
/*  84 */         if (this.c != ':') return error("colon", this.col);
/*  85 */         nextCharacter();
/*  86 */         skipWhiteSpace();
/*     */       }
/*  88 */       if (value()) {
/*  89 */         skipWhiteSpace();
/*  90 */         if (this.c == ',') {
/*  91 */           nextCharacter();
/*  92 */         } else { if (this.c == exitCharacter) {
/*     */             break;
/*     */           }
/*  95 */           return error("comma or " + exitCharacter, this.col);
/*     */         }
/*     */       } else {
/*  98 */         return error("value", this.col);
/*     */       }
/* 100 */       skipWhiteSpace();
/*     */     }
/*     */     
/* 103 */     nextCharacter();
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   private boolean number() {
/* 108 */     if ((!Character.isDigit(this.c)) && (this.c != '-')) return false;
/* 109 */     int start = this.col;
/* 110 */     if (this.c == '-') nextCharacter();
/* 111 */     if (this.c == '0') {
/* 112 */       nextCharacter();
/* 113 */     } else { if (Character.isDigit(this.c)) {
/* 114 */         while (Character.isDigit(this.c))
/* 115 */           nextCharacter();
/*     */       }
/* 117 */       return error("number", start);
/*     */     }
/* 119 */     if (this.c == '.') {
/* 120 */       nextCharacter();
/* 121 */       if (Character.isDigit(this.c)) {
/* 122 */         while (Character.isDigit(this.c))
/* 123 */           nextCharacter();
/*     */       }
/* 125 */       return error("number", start);
/*     */     }
/*     */     
/* 128 */     if ((this.c == 'e') || (this.c == 'E')) {
/* 129 */       nextCharacter();
/* 130 */       if ((this.c == '+') || (this.c == '-')) {
/* 131 */         nextCharacter();
/*     */       }
/* 133 */       if (Character.isDigit(this.c)) {
/* 134 */         while (Character.isDigit(this.c))
/* 135 */           nextCharacter();
/*     */       }
/* 137 */       return error("number", start);
/*     */     }
/*     */     
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   private boolean string() {
/* 144 */     if (this.c != '"') { return false;
/*     */     }
/* 146 */     int start = this.col;
/* 147 */     boolean escaped = false;
/* 148 */     for (nextCharacter(); this.c != 65535; nextCharacter()) {
/* 149 */       if ((!escaped) && (this.c == '\\')) {
/* 150 */         escaped = true;
/* 151 */       } else if (escaped) {
/* 152 */         if (!escape()) {
/* 153 */           return false;
/*     */         }
/* 155 */         escaped = false;
/* 156 */       } else if (this.c == '"') {
/* 157 */         nextCharacter();
/* 158 */         return true;
/*     */       }
/*     */     }
/* 161 */     return error("quoted string", start);
/*     */   }
/*     */   
/*     */   private boolean escape() {
/* 165 */     int start = this.col - 1;
/* 166 */     if (" \\\"/bfnrtu".indexOf(this.c) < 0) {
/* 167 */       return error("escape sequence  \\\",\\\\,\\/,\\b,\\f,\\n,\\r,\\t  or  \\uxxxx ", start);
/*     */     }
/* 169 */     if ((this.c == 'u') && (
/* 170 */       (!ishex(nextCharacter())) || (!ishex(nextCharacter())) || (!ishex(nextCharacter())) || 
/* 171 */       (!ishex(nextCharacter())))) {
/* 172 */       return error("unicode escape sequence  \\uxxxx ", start);
/*     */     }
/*     */     
/* 175 */     return true;
/*     */   }
/*     */   
/*     */   private boolean ishex(char d) {
/* 179 */     return "0123456789abcdefABCDEF".indexOf(this.c) >= 0;
/*     */   }
/*     */   
/*     */   private char nextCharacter() {
/* 183 */     this.c = this.it.next();
/* 184 */     this.col += 1;
/* 185 */     return this.c;
/*     */   }
/*     */   
/*     */   private void skipWhiteSpace() {
/* 189 */     while (Character.isWhitespace(this.c)) {
/* 190 */       nextCharacter();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 196 */   private boolean error(String type, int col) { return false; }
/*     */   
/*     */   public static void main(String[] args) {
/* 199 */     String jsonStr = "fgfgf";
/* 200 */     System.out.println(jsonStr + ":" + new JsonValidator().validate(jsonStr));
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\JsonValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */